package view;

import com.sun.jdi.Value;
import controller.Controller;
import model.Exceptions.MyException;
import model.*;
import model.Expressions.ArithmeticExpression;
import model.Expressions.InterfaceExpression;
import model.Expressions.ValueExpression;
import model.Expressions.VariableExpression;
import model.Statements.*;
import model.Types.BoolType;
import model.Types.IntType;
import model.Values.BoolValue;
import model.Values.IntValue;
import model.Values.InterfaceValue;
import model.Values.StringValue;
import repository.FileRepository;
import repository.InterfaceRepository;
import utils.*;
import view.Commands.ExitCommand;
import view.Commands.RunCommand;

import java.io.BufferedReader;
import java.util.Scanner;

public class Interpreter {

    InterfaceStatement ex1 = new CompoundStatement(
            new VariableDeclarationStatement("v", new IntType()),
            new CompoundStatement(
                    new AssignStatement("v", new ValueExpression(new IntValue(2))),
                    new PrintStatement(new VariableExpression("v"))
            )
    );

    InterfaceStatement ex2 = new CompoundStatement(
            new VariableDeclarationStatement("a", new IntType()),
            new CompoundStatement(
                    new VariableDeclarationStatement("b", new IntType()),
                    new CompoundStatement(
                            new AssignStatement("a",
                                    new ArithmeticExpression("+",
                                            new ValueExpression(new IntValue(2)),
                                            new ArithmeticExpression("*",
                                                    new ValueExpression(new IntValue(3)),
                                                    new ValueExpression(new IntValue(5))
                                            )
                                    )
                            ),
                            new CompoundStatement(
                                    new AssignStatement("b",
                                            new ArithmeticExpression(
                                                    "+",
                                                    new VariableExpression("a"),
                                                    new ValueExpression(new IntValue(1))
                                            )),
                                    new PrintStatement(new VariableExpression("b"))
                            )
                    )
            )
    );


    InterfaceStatement ex3 = new CompoundStatement
            (
                    new VariableDeclarationStatement("a", new BoolType()),
                    new CompoundStatement
                            (
                                    new VariableDeclarationStatement("v", new IntType()),
                                    new CompoundStatement
                                            (
                                                    new AssignStatement("a", new ValueExpression(new BoolValue(true))),
                                                    new CompoundStatement
                                                            (
                                                                    new IfStatement
                                                                            (
                                                                                    new VariableExpression("a"),
                                                                                    new AssignStatement("v", new ValueExpression(new IntValue(2))),
                                                                                    new AssignStatement("v", new ValueExpression(new IntValue(3)))
                                                                            ),
                                                                    new PrintStatement(new VariableExpression("v"))
                                                            )
                                            )
                            )
            );

    private void runExample(InterfaceStatement example) {

        InterfaceMyStack<InterfaceStatement> exeStack = new MyStack<>();
        InterfaceMyDictionary<String, InterfaceValue> symTable = new MyDictionary<>();
        InterfaceMyList<InterfaceValue> output = new MyList<>();
        InterfaceMyDictionary<InterfaceValue, BufferedReader> fileTable = new MyDictionary<>();

        ProgramState program = new ProgramState(exeStack, symTable, output, example, fileTable);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the path of the file where the logs should be saved: ");
        final String filePath = scanner.nextLine();
        InterfaceRepository repository = new FileRepository(filePath);
        repository.add(program);

        Controller controller = new Controller(repository);
        try {
            TextMenu menu = new TextMenu();
            menu.addCommand(new ExitCommand("0", "exit"));
            menu.addCommand(new RunCommand("1",ex1.toString(),controller));
            menu.addCommand(new RunCommand("2",ex2.toString(),controller));
            menu.addCommand(new RunCommand("3",ex3.toString(),controller));
            menu.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void menu() {
        System.out.println("0. Exit");
        System.out.println("1. Run Example 1");
        System.out.println("2. Run Example 2");
        System.out.println("3. Run Example 3");
    }


    public static void main(String[] args) {
        Interpreter interpreter = new Interpreter();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            interpreter.menu();
            System.out.println("Select option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 0 -> {
                    System.out.println("Exiting...");
                    return;
                }
                case 1 -> interpreter.runExample(interpreter.ex1);
                case 2 -> interpreter.runExample(interpreter.ex2);
                case 3 -> interpreter.runExample(interpreter.ex3);
                default -> System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
}
