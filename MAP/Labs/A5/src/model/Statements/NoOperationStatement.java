package model.Statements;

import model.Exceptions.MyException;
import model.ProgramState;

public class NoOperationStatement implements InterfaceStatement {
    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        return null;
    }

    @Override
    public String toString() {
        return null;
    }
}
