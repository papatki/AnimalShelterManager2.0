package com.patrycjap;

import com.patrycjap.view.UserInterface;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        UserInterface userInterface = new UserInterface();
        userInterface.startApp(primaryStage);

    }
}
