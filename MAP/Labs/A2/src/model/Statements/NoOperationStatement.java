package model.Statements;

import controller.MyException;
import model.PrgState;

public class NoOperationStatement implements IStmt {
    @Override
    public PrgState execute(PrgState state) throws MyException {
        return null;
    }

    @Override
    public String toString() {
        return null;
    }
}
