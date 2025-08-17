package controller;

import model.Statements.IStmt;
import model.PrgState;
import repository.IRepository;
import utils.MyIStack;


public class Controller {
    IRepository repo;

    public IRepository getRepo() {
        return repo;
    }

    public void setRepo(IRepository repo) {
        this.repo = repo;
    }

    public Controller(IRepository r) {
        repo = r;
    }

    public PrgState oneStep(PrgState state) throws MyException{

        MyIStack<IStmt> stack= state.getExeStack();
        if(stack.isEmpty()) throw new MyException("Program state stack is empty\n.");
        IStmt currentStatement = stack.pop();
        return currentStatement.execute(state);

    }

    public void allStep() throws MyException {

        PrgState currentProgramState = repo.getCrtPrg();
        System.out.println(currentProgramState.toString());
        while (!currentProgramState.getExeStack().isEmpty()) {

            oneStep(currentProgramState);
            //display flag?
            System.out.println(currentProgramState.toString());
        }
        System.out.println(currentProgramState.toString());
    }

}
