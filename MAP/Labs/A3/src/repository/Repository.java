package repository;
import model.Exceptions.MyException;
import model.ProgramState;

import java.util.List;
import java.util.ArrayList;
public class Repository implements InterfaceRepository {
    private List<ProgramState> programs;

    public Repository(){
        programs = new ArrayList<ProgramState>();
    }

    public void add(ProgramState program){
        programs.add(program);
    }

    @Override
    public void logProgramStateExecutable() throws MyException {

    }

    @Override
    public ProgramState getCurrentProgramState() {
        return programs.get(0);
    }

    public String toString(){
        return "Repository={" + programs + "}";
    }
}
