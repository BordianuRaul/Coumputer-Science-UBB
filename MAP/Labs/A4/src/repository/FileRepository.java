package repository;

import model.Exceptions.MyException;
import model.ProgramState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FileRepository implements InterfaceRepository {

    String logFilePath;

    private List<ProgramState> programs;

    public FileRepository(String logFilePath){

        programs = new ArrayList<ProgramState>();
        this.logFilePath = logFilePath;
    }

    public void add(ProgramState program){
        programs.add(program);
    }

    @Override
    public void logProgramStateExecutable() throws MyException, IOException {

        PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        logFile.println(this.getCurrentProgramState().toString());
        logFile.close();
    }

    @Override
    public ProgramState getCurrentProgramState() {
        return programs.get(0);
    }

    public String toString(){
        return "Repository={" + programs + "}";
    }
}
