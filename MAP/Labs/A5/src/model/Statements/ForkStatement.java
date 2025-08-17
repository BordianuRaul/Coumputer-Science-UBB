package model.Statements;

import model.Exceptions.MyException;
import model.ProgramState;
import utils.MyStack;

import java.io.IOException;

public class ForkStatement implements InterfaceStatement{

    private InterfaceStatement statement;

    public ForkStatement(InterfaceStatement statement){this.statement = statement;}

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
        ProgramState newProgram = new ProgramState(new MyStack<>(), state.getSymbolTable().cloneDictionary(),
                state.getOut(), this.statement, state.getFileTable(), state.getHeap());
        return newProgram;
    }

    @Override
    public String toString(){
        return "fork(" + statement.toString() + ")";
    }
}
