package model.Statements;

import model.Exceptions.MyException;
import model.ProgramState;
import model.Types.InterfaceType;
import utils.InterfaceMyDictionary;

public class NoOperationStatement implements InterfaceStatement {
    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        return null;
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public InterfaceMyDictionary<String, InterfaceType> typeCheck(InterfaceMyDictionary<String, InterfaceType> typeEnv) throws MyException {
        return typeEnv;
    }
}
