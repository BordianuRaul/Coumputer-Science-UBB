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

    public List<ProgramState> getProgramList(){return this.programs;}
    public void setProgramList(List<ProgramState> programs){this.programs = programs;}
    public void add(ProgramState program){
        programs.add(program);
    }

    public void logProgramStateExecutable(ProgramState state) throws MyException, IOException {

        PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        logFile.println(state.toString());
        logFile.close();
    }

    public String toString(){
        return "Repository={" + programs + "}";
    }
}
