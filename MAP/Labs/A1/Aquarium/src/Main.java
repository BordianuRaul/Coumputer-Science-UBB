import Controller.Controller;
import Repository.InMemoryRepository;
import View.UI;

public class Main {
    public static void main(String[] args) {

        InMemoryRepository repository = new InMemoryRepository();
        Controller controller= new Controller(repository);
        controller.add("Dolby", 3);
        controller.add("Nemo", 324, 10);
        controller.add("Wally", 204, 30);
        UI ui = new UI(controller);
        ui.runMenu();

    }
}
