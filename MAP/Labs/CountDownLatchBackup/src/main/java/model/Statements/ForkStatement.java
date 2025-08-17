package model.Statements;

import model.Exceptions.MyException;
import model.ProgramState;
import model.Types.InterfaceType;
import utils.InterfaceMyDictionary;
import utils.MyStack;

import java.io.IOException;

public class ForkStatement implements InterfaceStatement{

    private InterfaceStatement statement;

    public ForkStatement(InterfaceStatement statement){this.statement = statement;}

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
        ProgramState newProgram = new ProgramState(new MyStack<>(), state.getSymbolTable().cloneDictionary(),
                state.getOut(), this.statement, state.getFileTable(), state.getHeap(), state.getLatchTable());
        return newProgram;
    }

    @Override
    public String toString(){
        return "fork(" + statement.toString() + ")";
    }

    @Override
    public InterfaceMyDictionary<String, InterfaceType> typeCheck(InterfaceMyDictionary<String, InterfaceType> typeEnv) throws MyException {
        statement.typeCheck(typeEnv.cloneDictionary());
        return typeEnv;
    }
}
