package com.example.a7fx;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.Exceptions.MyException;
import model.Expressions.*;
import model.ProgramState;
import model.Statements.*;
import model.Types.BoolType;
import model.Types.IntType;
import model.Types.InterfaceType;
import model.Types.ReferenceType;
import model.Values.BoolValue;
import model.Values.IntValue;
import model.Values.InterfaceValue;
import model.Values.StringValue;
import repository.Repository;
import repository.InterfaceRepository;
import utils.*;

import java.io.BufferedReader;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class MenuWindowController implements Initializable {

    private MainWindowController mainWindowController;
    public void setMainWindowController(MainWindowController mainWindowController){
        this.mainWindowController = mainWindowController;
    }

    private List<InterfaceStatement> programStatesExamples;

    private List<Controller> programControllerList = new ArrayList<>();
    @FXML
    private ListView<String> programListView = new ListView<>();

    private Controller createControllerForExample(InterfaceStatement example) throws MyException {
        InterfaceMyStack<InterfaceStatement> exeStack = new MyStack<>();
        InterfaceMyDictionary<String, InterfaceValue> symTable = new MyDictionary<>();
        InterfaceMyList<InterfaceValue> output = new MyList<>();
        InterfaceMyDictionary<InterfaceValue, BufferedReader> fileTable = new MyDictionary<>();
        InterfaceMyDictionaryHeap<InterfaceValue> heap = new MyDictionaryHeap<>();
        try{
            example.typeCheck(new MyDictionary<String, InterfaceType>());

        }catch (MyException exception){
            throw exception;
        }
        ProgramState program = new ProgramState(exeStack, symTable, output, example, fileTable, heap);

        InterfaceRepository repository = new Repository("log1");
        repository.add(program);

        return new Controller(repository);
    }

    public void buildProgramStateExamples(){
        InterfaceStatement ex1 = new CompoundStatement(
                new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(
                        new AssignStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))
                )
        );

        InterfaceStatement ex2 = new CompoundStatement(
                new VariableDeclarationStatement("a", new IntType()),
                new CompoundStatement(
                        new VariableDeclarationStatement("b", new IntType()),
                        new CompoundStatement(
                                new AssignStatement("a",
                                        new ArithmeticExpression("+",
                                                new ValueExpression(new IntValue(2)),
                                                new ArithmeticExpression("*",
                                                        new ValueExpression(new IntValue(3)),
                                                        new ValueExpression(new IntValue(5))
                                                )
                                        )
                                ),
                                new CompoundStatement(
                                        new AssignStatement("b",
                                                new ArithmeticExpression(
                                                        "+",
                                                        new VariableExpression("a"),
                                                        new ValueExpression(new IntValue(1))
                                                )),
                                        new PrintStatement(new VariableExpression("b"))
                                )
                        )
                )
        );


        InterfaceStatement ex3 = new CompoundStatement
                (
                        new VariableDeclarationStatement("a", new BoolType()),
                        new CompoundStatement
                                (
                                        new VariableDeclarationStatement("v", new IntType()),
                                        new CompoundStatement
                                                (
                                                        new AssignStatement("a", new ValueExpression(new BoolValue(true))),
                                                        new CompoundStatement
                                                                (
                                                                        new IfStatement
                                                                                (
                                                                                        new VariableExpression("a"),
                                                                                        new AssignStatement("v", new ValueExpression(new IntValue(2))),
                                                                                        new AssignStatement("v", new ValueExpression(new IntValue(3)))
                                                                                ),
                                                                        new PrintStatement(new VariableExpression("v"))
                                                                )
                                                )
                                )
                );

        InterfaceStatement ex4 = new CompoundStatement(
                new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(
                        new New("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(
                                new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                                new CompoundStatement(
                                        new New("a",new VariableExpression("v")),
                                        new CompoundStatement(
                                                new PrintStatement(new VariableExpression("v")),
                                                new PrintStatement(new VariableExpression("a"))
                                        )
                                )
                        )
                )
        );


        InterfaceStatement ex5 =  new CompoundStatement(
                new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(
                        new AssignStatement("v", new ValueExpression(new IntValue(4))),
                        new WhileStatement
                                (
                                        new BooleanExpression
                                                (
                                                        new VariableExpression("v"),
                                                        new ValueExpression(new IntValue(0)),
                                                        ">"
                                                ),
                                        new CompoundStatement
                                                (
                                                        new PrintStatement(new VariableExpression("v")),
                                                        new AssignStatement
                                                                (
                                                                        "v",
                                                                        new ArithmeticExpression
                                                                                (
                                                                                        "-",
                                                                                        new VariableExpression("v"),
                                                                                        new ValueExpression(new IntValue(1))

                                                                                )
                                                                )
                                                )
                                )
                ));



        InterfaceStatement ex6 = new CompoundStatement(
                new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(
                        new VariableDeclarationStatement("a", new ReferenceType(new IntType())),
                        new CompoundStatement(
                                new AssignStatement("v", new ValueExpression(new IntValue(10))),
                                new CompoundStatement(
                                        new New("a", new ValueExpression(new IntValue(22))),
                                        new CompoundStatement(
                                                new ForkStatement(
                                                        new CompoundStatement(
                                                                new HeapWriteStatement("a", new ValueExpression(new IntValue(30))),
                                                                new CompoundStatement(
                                                                        new AssignStatement("v", new ValueExpression(new IntValue(32))),
                                                                        new CompoundStatement(
                                                                                new PrintStatement(new VariableExpression("v")),
                                                                                new PrintStatement(new HeapReadingExpression(new VariableExpression("a")))
                                                                        )
                                                                )
                                                        )
                                                ),
                                                new CompoundStatement(
                                                        new PrintStatement(new VariableExpression("v")),
                                                        new PrintStatement(new HeapReadingExpression(new VariableExpression("a")))
                                                )
                                        )
                                )
                        )
                )
        );

        InterfaceExpression filename7 = new ValueExpression(new StringValue("ex7.in"));
        InterfaceStatement ex7 = new CompoundStatement(new OpenReadFileStatement(filename7),
                new CompoundStatement(new VariableDeclarationStatement("v",new IntType()),
                        new CompoundStatement(new ReadFileStatement(filename7, "v"),
                                new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                        new CompoundStatement(new IfStatement(new RelationalExpression(new VariableExpression("v"), new ValueExpression(new IntValue(2)), ">="),
                                                new CompoundStatement(new ReadFileStatement(filename7, "v"), new PrintStatement(new VariableExpression("v"))),
                                                new PrintStatement(new ValueExpression(new IntValue(-1)))) {
                                        }, new CloseFileStatement(filename7))))));
        this.programStatesExamples = new ArrayList<>(Arrays.asList(ex1, ex2, ex3, ex4, ex5, ex6, ex7));
        for(InterfaceStatement example : programStatesExamples) {
            try {
                this.programControllerList.add(this.createControllerForExample(example));
            } catch (MyException exception) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Type check error");
                alert.setContentText("Example 2 did not pass the typecheck: " + exception.getMessage());

                alert.showAndWait();
            }

        }

        this.programListView.setItems(FXCollections.observableArrayList(
                this.programStatesExamples.stream().map(InterfaceStatement::toString).collect(Collectors.toList())));

    }

    @FXML
    private void onRunButtonPressed() {
        if (programListView.getSelectionModel().getSelectedItem() != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
                Parent interpreterView = loader.load();
                MainWindowController controller = loader.getController();
                controller.setController(programControllerList.get(programListView.getSelectionModel().getSelectedIndex()));
                Stage stage = new Stage();
                stage.setTitle("Main Window");
                stage.setScene(new Scene(interpreterView));
                stage.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

    }

}
