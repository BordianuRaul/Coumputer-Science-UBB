package View;

import Controller.Controller;
import Model.Animal;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UI {
    Controller controller;

    public UI(Controller controller)
    {
        this.controller = controller;
    }

    void printMenu()
    {
        System.out.println("1.Add fish.");
        System.out.println("2.Add tortoise.");
        System.out.println("3.Delete fish.");
        System.out.println("4.Delete tortoise.");
        System.out.println("5.Print all.");
        System.out.println("6.Filter.");
        System.out.println("0.Exit.");
    }

    public void runMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            System.out.println("Select option:");

            int option = getUserInput(scanner);

            switch (option) {
                case 1 -> handleAddFish();
                case 2 -> handleAddTortoise();
                case 3 -> handleDeleteFish();
                case 4 -> handleDeleteTortoise();
                case 5 -> handlePrintAll();
                case 6 -> handleFilter();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid option");
            }
        }
    }

    private int getUserInput(Scanner scanner) {
        while (true) {
            try {
                int option = scanner.nextInt();
                if (option >= 0 && option <= 6) {
                    return option;
                } else {
                    System.out.println("Invalid option. Please select a valid option.");
                    printMenu();
                }
            } catch (InputMismatchException ex) {
                System.out.println("Invalid input. Please enter a valid option.");
                runMenu();
            }
        }
    }

    void handlePrintAll()
    {
        try{

            Animal[] animals = controller.getAll();
            int size = controller.getSize();
            for(int i = 0; i < size; i++)
            {
                System.out.println(animals[i].toString());
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    void handleFilter()
    {
        Scanner scanner = new Scanner(System.in);
        try{
            System.out.println("Age: ");
            int age = scanner.nextInt();
            List<Animal> filteredAnimals = controller.filterByAge(age);
            for(Animal animal: filteredAnimals)
            {
                System.out.println(animal.toString());
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    void handleDeleteFish()
    {
        Scanner scanner = new Scanner(System.in);
        try{
            System.out.println("Fish name: ");
            String name = scanner.nextLine();
            System.out.println("Fish age: ");
            int age = scanner.nextInt();
            controller.delete(name, age);
        }catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    void handleDeleteTortoise()
    {
        Scanner scanner = new Scanner(System.in);
        try{
            System.out.println("Fish name: ");
            String name = scanner.nextLine();
            System.out.println("Fish age: ");
            int age = scanner.nextInt();
            System.out.println("Fish size: ");
            int size = scanner.nextInt();
            controller.delete(name, age, size);
        }catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    void handleAddFish()
    {
        Scanner scanner = new Scanner(System.in);
        try{
            System.out.println("Fish name: ");
            String name = scanner.nextLine();
            System.out.println("Fish age: ");
            int age = scanner.nextInt();
            controller.add(name, age);
        }catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    void handleAddTortoise()
    {
        Scanner scanner = new Scanner(System.in);
        try{
            System.out.println("Fish name: ");
            String name = scanner.nextLine();
            System.out.println("Fish age: ");
            int age = scanner.nextInt();
            System.out.println("Fish size: ");
            int size = scanner.nextInt();
            controller.add(name, age, size);
        }catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
