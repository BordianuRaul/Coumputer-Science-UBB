package repository;
import controller.MyException;
import model.PrgState;

import java.util.List;
import java.util.ArrayList;
public class Repository implements IRepository {
    private List<PrgState> programs;

    public Repository(){
        programs = new ArrayList<PrgState>();
    }

    public void add(PrgState program){
        programs.add(program);
    }

    @Override
    public void logPrgStateExec() throws MyException {

    }

    @Override
    public PrgState getCrtPrg() {
        return programs.get(0);
    }

    public String toString(){
        return "Repository={" + programs + "}";
    }
}
