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
        InterfaceLatchTable latchTable = new MyLatchTable();
        try{
            example.typeCheck(new MyDictionary<String, InterfaceType>());

        }catch (MyException exception){
            throw exception;
        }
        ProgramState program = new ProgramState(exeStack, symTable, output, example, fileTable, heap, latchTable);

        InterfaceRepository repository = new Repository("log1");
        repository.add(program);

        return new Controller(repository);
    }

    public void buildProgramStateExamples(){

        InterfaceStatement ex8 = new CompoundStatement(
                new VariableDeclarationStatement("v1", new ReferenceType(new IntType())),
                new CompoundStatement(
                        new VariableDeclarationStatement("v2", new ReferenceType(new IntType())),
                        new CompoundStatement(
                                new VariableDeclarationStatement("v3", new ReferenceType(new IntType())),
                                new CompoundStatement(
                                        new VariableDeclarationStatement("cnt", new IntType()),
                                        new CompoundStatement(
                                                new New("v1", new ValueExpression(new IntValue(2))),
                                                new CompoundStatement(
                                                        new New("v2", new ValueExpression(new IntValue(3))),
                                                        new CompoundStatement(
                                                                new New("v3", new ValueExpression(new IntValue(4))),
                                                                new CompoundStatement(
                                                                        new NewLatchStatement("cnt", new HeapReadingExpression(new VariableExpression("v2"))),
                                                                        new CompoundStatement(
                                                                                new ForkStatement(
                                                                                        new CompoundStatement(
                                                                                                new HeapWriteStatement("v1", new ArithmeticExpression("*", new HeapReadingExpression(new VariableExpression("v1")), new ValueExpression(new IntValue(10)))),
                                                                                                new CompoundStatement(
                                                                                                        new PrintStatement(new HeapReadingExpression(new VariableExpression("v1"))),
                                                                                                        new CompoundStatement(
                                                                                                                new CountDownStatement("cnt"),
                                                                                                                new ForkStatement(
                                                                                                                        new CompoundStatement(
                                                                                                                                new HeapWriteStatement("v2", new ArithmeticExpression("*", new HeapReadingExpression(new VariableExpression("v2")), new ValueExpression(new IntValue(10)))),
                                                                                                                                new CompoundStatement(
                                                                                                                                        new PrintStatement(new HeapReadingExpression(new VariableExpression("v2"))),
                                                                                                                                        new CompoundStatement(
                                                                                                                                                new CountDownStatement("cnt"),
                                                                                                                                                new ForkStatement(
                                                                                                                                                        new CompoundStatement(
                                                                                                                                                                new HeapWriteStatement("v3", new ArithmeticExpression("*", new HeapReadingExpression(new VariableExpression("v3")), new ValueExpression(new IntValue(10)))),
                                                                                                                                                                new CompoundStatement(
                                                                                                                                                                        new PrintStatement(new HeapReadingExpression(new VariableExpression("v3"))),
                                                                                                                                                                        new CountDownStatement("cnt")
                                                                                                                                                                )
                                                                                                                                                        )
                                                                                                                                                )
                                                                                                                                        )
                                                                                                                                )
                                                                                                                        )
                                                                                                                )
                                                                                                        )
                                                                                                )
                                                                                        )
                                                                                ),
                                                                                new CompoundStatement(
                                                                                        new AwaitStatement("cnt"),
                                                                                        new CompoundStatement(
                                                                                                new PrintStatement(new ValueExpression(new IntValue(100))),
                                                                                                new CompoundStatement(
                                                                                                        new CountDownStatement("cnt"),
                                                                                                        new PrintStatement(new ValueExpression(new IntValue(100)))
                                                                                                )
                                                                                        )
                                                                                )
                                                                        )
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );

        this.programStatesExamples = new ArrayList<>(Arrays.asList(ex8));
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
