package view;

import controller.Controller;
import model.Exceptions.MyException;
import model.*;
import model.Expressions.*;
import model.Statements.*;
import model.Types.BoolType;
import model.Types.IntType;
import model.Types.ReferenceType;
import model.Values.BoolValue;
import model.Values.IntValue;
import model.Values.InterfaceValue;
import model.Values.ReferenceValue;
import repository.FileRepository;
import repository.InterfaceRepository;
import utils.*;

import java.io.BufferedReader;
import java.util.Scanner;

public class View {

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

    InterfaceStatement ex4 = new CompoundStatement(
            new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
            new CompoundStatement(
                    new New("v", new ValueExpression(new IntValue(20))),
                    new CompoundStatement(
                            new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                            new CompoundStatement(
                                    new New("a",new VariableExpression("v")),
                                    new CompoundStatement(
                                            new PrintStatement(new VariableExpression("v")),
                                            new PrintStatement(new VariableExpression("a"))
                                    )
                            )
                    )
            )
    );


    InterfaceStatement ex5 =  new CompoundStatement(
            new VariableDeclarationStatement("v", new IntType()),
            new CompoundStatement(
            new AssignStatement("v", new ValueExpression(new IntValue(4))),
                    new WhileStatement
                            (
                                    new BooleanExpression
                                            (
                                                new VariableExpression("v"),
                                                new ValueExpression(new IntValue(0)),
                                                ">"
                                            ),
                                    new CompoundStatement
                                            (
                                                    new PrintStatement(new VariableExpression("v")),
                                                    new AssignStatement
                                                            (
                                                                    "v",
                                                                    new ArithmeticExpression
                                                                            (
                                                                                    "-",
                                                                                    new VariableExpression("v"),
                                                                                    new ValueExpression(new IntValue(1))

                                                                            )
                                                            )
                                            )
                            )
            ));



    InterfaceStatement ex6 = new CompoundStatement(
            new VariableDeclarationStatement("v", new IntType()),
            new CompoundStatement(
                    new VariableDeclarationStatement("a", new ReferenceType(new IntType())),
                    new CompoundStatement(
                            new AssignStatement("v", new ValueExpression(new IntValue(10))),
                            new CompoundStatement(
                                    new New("a", new ValueExpression(new IntValue(22))),
                                    new CompoundStatement(
                                            new ForkStatement(
                                                    new CompoundStatement(
                                                            new HeapWriteStatement("a", new ValueExpression(new IntValue(30))),
                                                            new CompoundStatement(
                                                                    new AssignStatement("v", new ValueExpression(new IntValue(32))),
                                                                    new CompoundStatement(
                                                                            new PrintStatement(new VariableExpression("v")),
                                                                            new PrintStatement(new HeapReadingExpression(new VariableExpression("a")))
                                                                    )
                                                            )
                                                    )
                                            ),
                                            new CompoundStatement(
                                                    new PrintStatement(new VariableExpression("v")),
                                                    new PrintStatement(new HeapReadingExpression(new VariableExpression("a")))
                                            )
                                    )
                            )
                    )
            )
    );




    public View() {
    }

    private void runExample(InterfaceStatement example) {

        InterfaceMyStack<InterfaceStatement> exeStack = new MyStack<>();
        InterfaceMyDictionary<String, InterfaceValue> symTable = new MyDictionary<>();
        InterfaceMyList<InterfaceValue> output = new MyList<>();
        InterfaceMyDictionary<InterfaceValue, BufferedReader> fileTable = new MyDictionary<>();
        InterfaceMyDictionaryHeap<InterfaceValue> heap = new MyDictionaryHeap<>();
        ProgramState program = new ProgramState(exeStack, symTable, output, example, fileTable, heap);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the path of the file where the logs should be saved: ");
        final String filePath = scanner.nextLine();
        InterfaceRepository repository = new FileRepository(filePath);
        repository.add(program);

        Controller controller = new Controller(repository);
        try {
            controller.allSteps();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void menu() {
        System.out.println("0. Exit");
        System.out.println("1. Run Example 1");
        System.out.println("2. Run Example 2");
        System.out.println("3. Run Example 3");
        System.out.println("4. Run Example 4");
        System.out.println("5. Run Example 5");
        System.out.println("6. Run Example 6");
    }


    public static void main(String[] args) {
        View view = new View();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            view.menu();
            System.out.println("Select option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 0 -> {
                    System.out.println("Exiting...");
                    return;
                }
                case 1 -> view.runExample(view.ex1);
                case 2 -> view.runExample(view.ex2);
                case 3 -> view.runExample(view.ex3);
                case 4 -> view.runExample(view.ex4);
                case 5 -> view.runExample(view.ex5);
                case 6 -> view.runExample(view.ex6);
                default -> System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
}
