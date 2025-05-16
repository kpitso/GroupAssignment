package com.sesothoapp;

import com.sesothoapp.view.HomeScreen;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/*
 This class initializes the JavaFX application
  and sets up the primary stage.
 */
public class Main extends Application {
    private static final String APP_TITLE = "App ea ho Ithuta Sesotho";
    private static final int WINDOW_WIDTH = 1000;
    private static final int WINDOW_HEIGHT = 650;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            // Set up the primary stage
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/app_logo.png")));
            primaryStage.setTitle(APP_TITLE);
            primaryStage.setMinWidth(WINDOW_WIDTH);
            primaryStage.setMinHeight(WINDOW_HEIGHT);

            // Load the home screen
            HomeScreen homeScreen = new HomeScreen();
            Scene scene = new Scene(homeScreen, WINDOW_WIDTH, WINDOW_HEIGHT);

            // Add stylesheet
            scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

            // Set the scene and show the stage
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}