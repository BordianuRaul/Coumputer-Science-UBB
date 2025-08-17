package model.Statements;

import model.Exceptions.MyException;
import model.ProgramState;

import java.io.IOException;

public interface InterfaceStatement {

    ProgramState execute(ProgramState state) throws MyException, IOException;

    String toString();
}
