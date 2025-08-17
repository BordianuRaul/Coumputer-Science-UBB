package view;

import controller.Controller;
import controller.MyException;
import model.*;
import repository.IRepository;
import repository.Repository;
import utils.*;

public class View {

    private IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()),
            new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(2))),
                    new PrintStmt(new VarExp("v"))));

    public View() {
    }

    public void menu() {
        System.out.println("0. Exit");
        System.out.println("1. Example 1 " + ex1);
        System.out.println("2. Example 2 " + ex1); // TODO: ex2 etc...
    }

    private void runEx1() {

        MyIStack<IStmt> exeStack = new MyStack<>();
        MyIDictionary<String, Value> symTable = new MyDictionary<>();
        MyIList<Value> output = new MyList<>();

        PrgState program = new PrgState(exeStack, symTable, output, ex1);

        IRepository repository = new Repository();
        repository.add(program);

        Controller controller = new Controller(repository);
        try {
            controller.allStep();
        } catch (MyException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        View view = new View();
        view.runEx1();
        // TODO: actual menu
    }
}
