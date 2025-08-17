package com.example.a7fx;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFX extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader mainLoader = new FXMLLoader();
        mainLoader.setLocation(getClass().getResource("MenuWindow.fxml"));
        Parent mainWindow = mainLoader.load();

        MenuWindowController controller = mainLoader.getController();
        controller.buildProgramStateExamples();


        primaryStage.setTitle("Menu");
        primaryStage.setScene(new Scene(mainWindow));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}