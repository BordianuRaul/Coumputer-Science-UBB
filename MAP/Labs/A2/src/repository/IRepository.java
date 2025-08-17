package repository;
import controller.MyException;
import model.PrgState;

import java.io.IOException;

public interface IRepository {
    PrgState getCrtPrg();

    void add(PrgState program);

    void logPrgStateExec() throws MyException, IOException;
}
