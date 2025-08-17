package controller;

import model.Exceptions.MyException;
import model.Statements.InterfaceStatement;
import model.ProgramState;
import repository.InterfaceRepository;
import utils.InterfaceMyStack;

import java.io.IOException;


public class Controller {
    InterfaceRepository repo;

    public InterfaceRepository getRepo() {
        return repo;
    }

    public void setRepo(InterfaceRepository repo) {
        this.repo = repo;
    }

    public Controller(InterfaceRepository repository) {
        repo = repository;
    }

    public ProgramState oneStep(ProgramState state) throws MyException, IOException {

        InterfaceMyStack<InterfaceStatement> stack= state.getExeStack();
        if(stack.isEmpty()) throw new MyException("Program state stack is empty\n.");
        InterfaceStatement currentStatement = stack.pop();
        return currentStatement.execute(state);

    }

    public void allStep() throws MyException {

        try {


            ProgramState currentProgramState = repo.getCurrentProgramState();
            repo.logProgramStateExecutable();
            while (!currentProgramState.getExeStack().isEmpty()) {

                oneStep(currentProgramState);
                repo.logProgramStateExecutable();

            }
        }
        catch (IOException ioException)
        {
            System.out.println(ioException.getMessage());
        }
        catch (MyException myException)
        {
            System.out.println("An error has occurred!");
        }
    }

}
