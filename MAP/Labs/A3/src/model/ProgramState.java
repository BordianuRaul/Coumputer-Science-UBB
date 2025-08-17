package model;

import model.Statements.InterfaceStatement;
import model.Values.InterfaceValue;
import utils.InterfaceMyDictionary;
import utils.InterfaceMyList;
import utils.InterfaceMyStack;

import java.io.BufferedReader;

public class ProgramState {
    InterfaceMyStack<InterfaceStatement> executionStack;
    InterfaceMyDictionary<String, InterfaceValue> symbolTable;
    InterfaceMyList<InterfaceValue> outputList;
    InterfaceMyDictionary<InterfaceValue, BufferedReader> fileTable;
    InterfaceStatement originalProgram;

    public ProgramState(InterfaceMyStack<InterfaceStatement> stk, InterfaceMyDictionary<String, InterfaceValue> symtbl, InterfaceMyList<InterfaceValue> ot, InterfaceStatement prg, InterfaceMyDictionary<InterfaceValue, BufferedReader>fileTable){
        this.executionStack = stk;
        this.symbolTable = symtbl;
        this.outputList = ot;
        this.executionStack.push(prg);
        this.originalProgram = prg;
        this.fileTable = fileTable;
    }
    public InterfaceMyDictionary<InterfaceValue, BufferedReader> getFileTable() {return this.fileTable;}

    public void setFileTable(InterfaceMyDictionary<InterfaceValue, BufferedReader> fileTable)
    {
        this.fileTable = fileTable;
    }
    public InterfaceMyStack<InterfaceStatement> getExeStack() {
        return executionStack;
    }

    public void setExeStack(InterfaceMyStack<InterfaceStatement> exeStack) {
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
                "########################################################################\n\n";
    }
}
