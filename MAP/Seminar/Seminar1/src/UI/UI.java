package UI;

import Controller.Controller;
import Model.Apple;
import Model.Item;

import java.util.List;
import java.util.Scanner;

public class UI {
    private Controller controller;

    public UI(Controller controller)
    {
        this.controller = controller;
    }

    public void printMenu()
    {
        System.out.println("1.Add");
        System.out.println("2.Filter");
        System.out.println("0.Exit");
    }

    public void run()
    {
        Scanner sc = new Scanner(System.in);
        while(true){
            printMenu();
            int choice = sc.nextInt();
            switch (choice)
            {
                case 0: return;
                case 1: {
                    controller.add(new Apple(100));
                    controller.add(new Apple(150));
                    controller.add(new Apple(200));
                    System.out.println("Add compelted.");
                    break;
                }
                case 2: {
                    System.out.println("Weight = ");
                    int weight = sc.nextInt();
                    List<Item> result = controller.filter(200);
                    for(Item item: result)
                    {
                        System.out.println(item.toString());
                    }
                    System.out.println("Filter Completed");
                }
            }
        }


    }
}
