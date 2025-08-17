

import Repository.InMemoryRepository;
import Controller.Controller;
import UI.UI;

import java.lang.constant.Constable;

public class main {
    public static void main(String[] args) {

        UI ui = new UI(new Controller(new InMemoryRepository()));
        ui.run();
    }
}
