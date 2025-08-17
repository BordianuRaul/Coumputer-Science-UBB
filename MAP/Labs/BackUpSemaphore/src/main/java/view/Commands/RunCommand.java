package view.Commands;

import controller.Controller;
import view.Command;

public class RunCommand extends Command {
    private Controller controller;

    public RunCommand(String key, String description, Controller controller){
        super(key, description);
        this.controller = controller;
    }

    @Override
    public void execute() {
        try{
            controller.allSteps();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
