package model.Statements;

import controller.MyException;
import model.PrgState;

public interface IStmt {

    PrgState execute(PrgState state) throws MyException;

    String toString();
}
