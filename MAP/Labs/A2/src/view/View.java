package view;

import controller.Controller;
import controller.MyException;
import model.*;
import model.Expressions.ArithmeticExpression;
import model.Expressions.ValueExp;
import model.Expressions.ValueExpression;
import model.Expressions.VariableExpression;
import model.Statements.*;
import repository.IRepository;
import repository.Repository;
import utils.*;

import java.util.Scanner;

public class View {

    IStmt ex1 = new CompStmt(
            new VarDeclStmt("v", new IntType()),
            new CompStmt(
                    new AssignStatement("v", new ValueExp(new IntValue(2))),
                    new PrintStatement(new VariableExpression("v"))
            )
    );

    IStmt ex2 = new CompStmt(
            new VarDeclStmt("a", new IntType()),
            new CompStmt(
                    new VarDeclStmt("b", new IntType()),
                    new CompStmt(
                            new AssignStatement("a",
                                    new ArithmeticExpression("+",
                                            new ValueExp(new IntValue(2)),
                                            new ArithmeticExpression("*",
                                                    new ValueExp(new IntValue(3)),
                                                    new ValueExp(new IntValue(5))
                                            )
                                    )
                            ),
                            new CompStmt(
                                    new AssignStatement("b",
                                            new ArithmeticExpression(
                                                    "+",
                                                    new VariableExpression("a"),
                                                    new ValueExp(new IntValue(1))
                                            )),
                                            new PrintStatement(new VariableExpression("b"))
                                    )
                            )
                    )
            );


    IStmt ex3 = new CompStmt
            (
                new VarDeclStmt("a", new BoolType()),
                new CompStmt
                        (
                            new VarDeclStmt("v", new IntType()),
                                new CompStmt
                                        (
                                            new AssignStatement("a", new ValueExp(new BoolValue(true))),
                                                    new CompStmt
                                                            (
                                                                new IfStatement
                                                                        (
                                                                        new VariableExpression("a"),
                                                                        new AssignStatement("v", new ValueExp(new IntValue(2))),
                                                                        new AssignStatement("v", new ValueExp(new IntValue(3)))
                                                                        ),
                                                                new PrintStatement(new VariableExpression("v"))
                                                            )
                                        )
                        )
            );






    public View() {
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

    private void runEx2() {

        MyIStack<IStmt> exeStack = new MyStack<>();
        MyIDictionary<String, Value> symTable = new MyDictionary<>();
        MyIList<Value> output = new MyList<>();

        PrgState program = new PrgState(exeStack, symTable, output, ex2);

        IRepository repository = new Repository();
        repository.add(program);

        Controller controller = new Controller(repository);
        try {
            controller.allStep();
        } catch (MyException e) {
            throw new RuntimeException(e);
        }

    }

    private void runEx3() {

        MyIStack<IStmt> exeStack = new MyStack<>();
        MyIDictionary<String, Value> symTable = new MyDictionary<>();
        MyIList<Value> output = new MyList<>();

        PrgState program = new PrgState(exeStack, symTable, output, ex3);

        IRepository repository = new Repository();
        repository.add(program);

        Controller controller = new Controller(repository);
        try {
            controller.allStep();
        } catch (MyException e) {
            throw new RuntimeException(e);
        }

    }

    public void menu() {
        System.out.println("0. Exit");
        System.out.println("1. Run Example 1");
        System.out.println("2. Run Example 2");
        System.out.println("3. Run Example 3");
    }

    public void runExample(IStmt example) {
        MyIStack<IStmt> exeStack = new MyStack<>();
        MyIDictionary<String, Value> symTable = new MyDictionary<>();
        MyIList<Value> output = new MyList<>();

        PrgState program = new PrgState(exeStack, symTable, output, example);

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
        Scanner scanner = new Scanner(System.in);

        while (true) {
            view.menu();
            System.out.println("Select option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 0:
                    System.out.println("Exiting...");
                    return;
                case 1:
                    view.runExample(view.ex1);
                    break;
                case 2:
                    view.runExample(view.ex2);
                    break;
                case 3:
                    view.runExample(view.ex3);
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
}
