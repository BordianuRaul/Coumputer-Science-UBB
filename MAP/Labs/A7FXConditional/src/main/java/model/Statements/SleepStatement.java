package model.Statements;

import model.Exceptions.MyException;
import model.ProgramState;
import model.Types.InterfaceType;
import utils.InterfaceMyDictionary;

public class SleepStatement implements InterfaceStatement{
    private int nr;

    public SleepStatement(int n){nr = n;}

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        if(nr!=0) state.getExecutionStack().push(new SleepStatement(nr-1));
        return null;
    }

    @Override
    public InterfaceMyDictionary<String, InterfaceType> typeCheck(InterfaceMyDictionary<String, InterfaceType> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "sleep("+nr+")";
    }
}
