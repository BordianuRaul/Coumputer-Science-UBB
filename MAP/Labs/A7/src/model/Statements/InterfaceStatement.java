package model.Statements;

import model.Exceptions.MyException;
import model.ProgramState;
import model.Types.InterfaceType;
import utils.InterfaceMyDictionary;

import java.io.IOException;

public interface InterfaceStatement {

    ProgramState execute(ProgramState state) throws MyException, IOException;

    String toString();

    public InterfaceMyDictionary<String, InterfaceType> typeCheck(InterfaceMyDictionary<String,InterfaceType> typeEnv) throws MyException;
}
