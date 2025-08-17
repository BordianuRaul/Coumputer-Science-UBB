package repository;

import controller.MyException;
import model.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FileRepository implements IRepository{

    String logFilePath;

    private List<PrgState> programs;

    public FileRepository(String logFilePath){

        programs = new ArrayList<PrgState>();
        this.logFilePath = logFilePath;
    }

    public void add(PrgState program){
        programs.add(program);
    }

    @Override
    public void logPrgStateExec() throws MyException, IOException {

        PrintWriter logFile= new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));

    }

    @Override
    public PrgState getCrtPrg() {
        return programs.get(0);
    }

    public String toString(){
        return "Repository={" + programs + "}";
    }
}
