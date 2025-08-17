package model;

import model.Exceptions.MyException;
import model.Statements.InterfaceStatement;
import model.Values.InterfaceValue;
import utils.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;

public class ProgramState {
    InterfaceMyStack<InterfaceStatement> executionStack;
    InterfaceMyDictionary<String, InterfaceValue> symbolTable;
    InterfaceMyList<InterfaceValue> outputList;
    InterfaceMyDictionary<InterfaceValue, BufferedReader> fileTable;
    InterfaceStatement originalProgram;
    InterfaceMyDictionaryHeap<InterfaceValue> heap;

    InterfaceSemaphoreTable semaphoreTable;

    ExecutorService executor;
    private int id;
    private static int idCounter = 0;

    public ProgramState(InterfaceMyStack<InterfaceStatement> stk, InterfaceMyDictionary<String, InterfaceValue> symtbl, InterfaceMyList<InterfaceValue> ot, InterfaceStatement prg, InterfaceMyDictionary<InterfaceValue, BufferedReader>fileTable, InterfaceMyDictionaryHeap<InterfaceValue> heap, InterfaceSemaphoreTable semaphoreTable){
        this.executionStack = stk;
        this.symbolTable = symtbl;
        this.outputList = ot;
        this.executionStack.push(prg);
        this.originalProgram = prg;
        this.fileTable = fileTable;
        this.heap = heap;
        this.semaphoreTable = semaphoreTable;
        this.id = setFreeAddress();
    }
    public InterfaceMyDictionary<InterfaceValue, BufferedReader> getFileTable() {return this.fileTable;}

    public InterfaceMyDictionaryHeap<InterfaceValue> getHeap(){return this.heap;}

    public void setHeap(InterfaceMyDictionaryHeap<InterfaceValue> heap){this.heap = heap;}
    public void setFileTable(InterfaceMyDictionary<InterfaceValue, BufferedReader> fileTable) {this.fileTable = fileTable;}
    public InterfaceMyStack<InterfaceStatement> getExecutionStack() {
        return executionStack;
    }
    public void setExecutionStack(InterfaceMyStack<InterfaceStatement> exeStack) {
        this.executionStack = exeStack;
    }

    public InterfaceMyDictionary<String, InterfaceValue> getSymbolTable() {
        return symbolTable;
    }

    public void setSymTable(InterfaceMyDictionary<String, InterfaceValue> symTable) {
        this.symbolTable = symTable;
    }
    public InterfaceMyList<InterfaceValue> getOut() {
        return outputList;
    }

    public void setOut(InterfaceMyList<InterfaceValue> out) {
        this.outputList = out;
    }

    @Override
    public String toString() {
        return  "-------ID = "+ id +"-------: \n" + executionStack.toString() + "\n\n" +
                "-------Execution Stack-------: \n" + executionStack.toString() + "\n\n" +
                "-------Symbol Table-------: \n" + symbolTable.toString() + "\n\n" +
                "-------Output List-------: \n" + outputList.toString() + "\n\n"+
                "-------FileTable List-------: \n" + fileTable.toString() + "\n\n"+
                "-------Heap-------: \n" + heap.toString() + "\n\n"+
                "-------SemaphoreTable-------\n" + semaphoreTable.toString() +
                "########################################################################\n\n";
    }

    public boolean isNotCompleted(){
        return !executionStack.isEmpty();
    }

    public ProgramState oneStep() throws MyException, IOException {

        if(executionStack.isEmpty()) return null;
        try {
            InterfaceStatement crtStmt = executionStack.pop();
            return crtStmt.execute(this);
        }catch(MyException e){
            System.out.println(e.getMessage());
        }
        return this;

    }

    public void addOut(InterfaceValue value){
        this.outputList.add(value);
    }

    public int setFreeAddress(){
        idCounter++;
        return idCounter;
    }

    public int getCurrentID(){
        return this.id;
    }

    public InterfaceSemaphoreTable getSemaphoreTable(){
        return this.semaphoreTable;
    }


}
