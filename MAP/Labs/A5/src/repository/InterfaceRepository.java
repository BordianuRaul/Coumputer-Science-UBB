package repository;
import model.Exceptions.MyException;
import model.ProgramState;

import java.io.IOException;
import java.util.List;

public interface InterfaceRepository {

    void add(ProgramState program);

    void logProgramStateExecutable(ProgramState state) throws MyException, IOException;

    public void setProgramList(List<ProgramState> programs);
    public List<ProgramState> getProgramList();
}
