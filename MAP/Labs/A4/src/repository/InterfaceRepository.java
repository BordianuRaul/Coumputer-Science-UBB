package repository;
import model.Exceptions.MyException;
import model.ProgramState;

import java.io.IOException;

public interface InterfaceRepository {
    ProgramState getCurrentProgramState();

    void add(ProgramState program);

    void logProgramStateExecutable() throws MyException, IOException;
}
