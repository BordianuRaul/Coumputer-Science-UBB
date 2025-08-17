package com.example.a7fx;

import controller.Controller;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Pair;
import model.Exceptions.MyException;
import model.ProgramState;
import model.Statements.InterfaceStatement;
import model.Values.InterfaceValue;
import utils.InterfaceMyDictionary;
import utils.InterfaceMyDictionaryHeap;
import utils.InterfaceSemaphoreTable;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class MainWindowController implements Initializable {

    private Controller controller;
    private ProgramState selectedProgram;

    public MainWindowController(){};

    @FXML
    private TableView<HashMap.Entry<Integer, String>> heapTableView = new TableView<>();
    @FXML
    private TableColumn<HashMap.Entry<Integer, String>, Integer> heapAddressColumn = new TableColumn<>();
    @FXML
    private TableColumn<HashMap.Entry<Integer, String>, String> heapValueColumn = new TableColumn<>();

    @FXML
    private ListView<String> outputListView = new ListView<>();

    @FXML
    private ListView<String> fileListView = new ListView<>();

    @FXML
    private ListView<Integer> programStateListView = new ListView<>();

    @FXML
    private TableView<Map.Entry<String, String>> symbolTableView = new TableView<>();
    @FXML
    private TableColumn<Map.Entry<String, String>, String> symbolTableVariableColumn = new TableColumn<>();
    @FXML
    private TableColumn<Map.Entry<String, String>, String> symbolTableValueColumn = new TableColumn<>();

    @FXML
    private ListView<String> executionStackListView = new ListView<>();

    @FXML
    private TextField numberOfProgramStatesTextField = new TextField("");
    @FXML
    private TableView<Map.Entry<Integer, Pair<Integer, List<Integer>>>> semaphoreTableView;

    @FXML
    private TableColumn<Map.Entry<Integer, Pair<Integer, List<Integer>>>, Integer> indexSemaphoreTableColumn;

    @FXML
    private TableColumn<Map.Entry<Integer, Pair<Integer, List<Integer>>>, Integer> valueSemaphoreTableColumn;

    @FXML
    private TableColumn<Map.Entry<Integer, Pair<Integer, List<Integer>>>, List<Integer>> listSemaphoreTableColumn;

    public void setController(Controller controller) {
        this.controller = controller;

        selectedProgram = controller.getRepo().getProgramList().get(0);

        loadData();
    }

    private void loadData(){

        this.programStateListView.getItems().setAll( controller.getRepo().getProgramList().stream().map(ProgramState::getCurrentID).collect(Collectors.toList()) );

        if(selectedProgram != null){

            outputListView.getItems().setAll( selectedProgram.getOut().getList().stream().map(Object::toString).collect(Collectors.toList()));

            fileListView.getItems().setAll(String.valueOf(selectedProgram.getFileTable().getContent().keySet()));

            List<String> executionStackList = selectedProgram.getExecutionStack().getStack().stream().map(InterfaceStatement::toString).collect(Collectors.toList());
            Collections.reverse(executionStackList);
            executionStackListView.getItems().setAll(executionStackList);
            executionStackListView.refresh();

            InterfaceMyDictionaryHeap<InterfaceValue> heapTable = selectedProgram.getHeap();
            List<Map.Entry<Integer, String>> heapTableList = new ArrayList<>();

            for(Map.Entry<Integer, InterfaceValue> element:heapTable.getContent().entrySet())
            {

                Map.Entry<Integer, String> el=new AbstractMap.SimpleEntry<Integer, String>(element.getKey(),element.getValue().toString());
                heapTableList.add(el);
            }

            heapTableView.setItems(FXCollections.observableList(heapTableList));
            heapTableView.refresh();

            heapAddressColumn.setCellValueFactory(p->new SimpleIntegerProperty(p.getValue().getKey()).asObject());
            heapValueColumn.setCellValueFactory(p->new SimpleStringProperty(p.getValue().getValue()));

            InterfaceMyDictionary<String, InterfaceValue> symbolTable = this.selectedProgram.getSymbolTable();
            List<Map.Entry<String, String>> symbolTableList=new ArrayList<>();
            for(Map.Entry<String, InterfaceValue> element:symbolTable.getContent().entrySet()){
                Map.Entry<String, String> el = new AbstractMap.SimpleEntry<String, String>(element.getKey(),element.getValue().toString());
                symbolTableList.add(el);
            }
            symbolTableView.setItems(FXCollections.observableList(symbolTableList));
            symbolTableView.refresh();

            symbolTableVariableColumn.setCellValueFactory(p->new SimpleStringProperty(p.getValue().getKey()));
            symbolTableValueColumn.setCellValueFactory(p->new SimpleStringProperty(p.getValue().getValue()));

            numberOfProgramStatesTextField.setText(Integer.toString(controller.getRepo().getSize()));

            InterfaceSemaphoreTable semaphoreTable = selectedProgram.getSemaphoreTable();
            List<Map.Entry<Integer, Pair<Integer, List<Integer>>>> semaphoreList = new ArrayList<>();

            for (Map.Entry<Integer, Pair<Integer, List<Integer>>> entry : semaphoreTable.getMap().entrySet())
                semaphoreList.add(entry);
            semaphoreTableView.setItems(FXCollections.observableList(semaphoreList));
            semaphoreTableView.refresh();

            indexSemaphoreTableColumn.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getKey()).asObject());
            valueSemaphoreTableColumn.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getValue().getKey()).asObject());
            listSemaphoreTableColumn.setCellValueFactory(p->new SimpleObjectProperty<>(p.getValue().getValue().getValue()));

        }
    }

    private void runOneStep() {
        if (controller != null) {
            try {
                List<ProgramState> programStates = Objects.requireNonNull(controller.getProgramStates());
                if (programStates.size() > 0) {
                    controller.oneStep();
                    loadData();
                    programStates = controller.removeCompletedPrograms(controller.getProgramStates());
                    controller.setProgramStates(programStates);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error!");
                    alert.setHeaderText("An error has occured!");
                    alert.setContentText("There is nothing left to execute!");
                    alert.showAndWait();
                }
            } catch (MyException | InterruptedException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Execution error!");
                alert.setHeaderText("An execution error has occured!");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("An error has occured!");
            alert.setContentText("No program selected!");
            alert.showAndWait();
        }
    }


    @FXML
    public void onRunOneStepButtonPressed() throws MyException, InterruptedException {
        if (controller != null) {
            try {
                List<ProgramState> programStates = Objects.requireNonNull(controller.getProgramStates());
                if (programStates.size() > 0) {
                    controller.oneStep();
                    loadData();
                    programStates = controller.removeCompletedPrograms(controller.getProgramStates());
                    controller.setProgramStates(programStates);
                    loadData();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error!");
                    alert.setHeaderText("An error has occured!");
                    alert.setContentText("There is nothing left to execute!");
                    alert.showAndWait();
                }
            } catch (MyException | InterruptedException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Execution error!");
                alert.setHeaderText("An execution error has occured!");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("An error has occured!");
            alert.setContentText("No program selected!");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}