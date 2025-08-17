package model;

import model.Statements.InterfaceStatement;
import model.Values.InterfaceValue;
import utils.InterfaceMyDictionary;
import utils.InterfaceMyDictionaryHeap;
import utils.InterfaceMyList;
import utils.InterfaceMyStack;

import java.io.BufferedReader;

public class ProgramState {
    InterfaceMyStack<InterfaceStatement> executionStack;
    InterfaceMyDictionary<String, InterfaceValue> symbolTable;
    InterfaceMyList<InterfaceValue> outputList;
    InterfaceMyDictionary<InterfaceValue, BufferedReader> fileTable;
    InterfaceStatement originalProgram;
    InterfaceMyDictionaryHeap<InterfaceValue> heap;

    public ProgramState(InterfaceMyStack<InterfaceStatement> stk, InterfaceMyDictionary<String, InterfaceValue> symtbl, InterfaceMyList<InterfaceValue> ot, InterfaceStatement prg, InterfaceMyDictionary<InterfaceValue, BufferedReader>fileTable, InterfaceMyDictionaryHeap<InterfaceValue> heap){
        this.executionStack = stk;
        this.symbolTable = symtbl;
        this.outputList = ot;
        this.executionStack.push(prg);
        this.originalProgram = prg;
        this.fileTable = fileTable;
        this.heap = heap;
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
        return "-------Execution Stack-------: \n" + executionStack.toString() + "\n\n" +
                "-------Symbol Table-------: \n" + symbolTable.toString() + "\n\n" +
                "-------Output List-------: \n" + outputList.toString() + "\n\n"+
                "-------FileTable List-------: \n" + fileTable.toString() + "\n\n"+
                "-------Heap-------: \n" + heap.toString() + "\n\n"+
                "########################################################################\n\n";
    }
}
