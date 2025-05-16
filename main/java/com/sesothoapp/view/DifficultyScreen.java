package com.sesothoapp.view;

import com.sesothoapp.controller.GameController;
import com.sesothoapp.model.Category;
import com.sesothoapp.model.Difficulty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
 The difficulty selection screen for the Sesotho Learning App.
 */
public class DifficultyScreen extends BorderPane {

    private final GameController gameController;
    private final Category selectedCategory;
    private VBox mainContent;

     //Constructs a new difficulty screen for a category.
    public DifficultyScreen(Category category) {
        this.gameController = new GameController();
        this.selectedCategory = category;

        // Set up the layout
        setupLayout();
    }

     // Set up the layout of the difficulty screen.
    private void setupLayout() {
        // Set up the layout components
        setTop(createHeader());

        // Main content
        mainContent = new VBox(20);
        mainContent.getStyleClass().add("main-content");
        mainContent.setPadding(new Insets(20));

        // Add a title for the difficulty selection
        Label title = new Label("Khetha Boima ba " + selectedCategory.getName());
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Category description
        Label description = new Label(selectedCategory.getDescription());
        description.setStyle("-fx-font-size: 16px; -fx-text-fill: #6c757d;");
        description.setWrapText(true);

        // Add the difficulty options
        VBox difficultyContainer = createDifficultyOptions();

        mainContent.getChildren().addAll(title, description, difficultyContainer);
        setCenter(mainContent);

        // Footer
        setBottom(createFooter());
    }


     //Create the difficulty options for selection.
    private VBox createDifficultyOptions() {
        VBox container = new VBox(15);
        container.setPadding(new Insets(20, 0, 20, 0));
        container.setAlignment(Pos.CENTER);

        // Create a button for each difficulty level
        for (Difficulty difficulty : Difficulty.values()) {
            VBox difficultyOption = createDifficultyOption(difficulty);
            container.getChildren().add(difficultyOption);
        }

        return container;
    }


    // Create a button for a difficulty level.
    private VBox createDifficultyOption(Difficulty difficulty) {
        VBox option = new VBox(10);
        option.getStyleClass().add("category-card");
        option.setPadding(new Insets(15));
        option.setAlignment(Pos.CENTER);

        // Check if this difficulty is unlocked for the user
        boolean isUnlocked = gameController.isDifficultyUnlocked(selectedCategory, difficulty);

        if (!isUnlocked) {
            option.getStyleClass().add("locked");
        }

        // Set an event handler to start the game when clicked, if unlocked
        if (isUnlocked) {
            option.setOnMouseClicked(e -> startGame(difficulty));
        }

        // Difficulty name
        Label nameLabel = new Label(difficulty.getDisplayName());
        nameLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        // Difficulty description
        Label descriptionLabel = new Label(difficulty.getDescription());
        descriptionLabel.setStyle("-fx-font-size: 14px;");
        descriptionLabel.setWrapText(true);

        // Locked/unlocked status
        Label statusLabel = new Label(isUnlocked ? "Available" : "Locked - Complete easier levels first");
        statusLabel.setStyle(isUnlocked ?
                "-fx-font-size: 12px; -fx-text-fill: #28a745;" :
                "-fx-font-size: 12px; -fx-text-fill: #dc3545;");

        // Add components to option
        option.getChildren().addAll(nameLabel, descriptionLabel, statusLabel);

        return option;
    }

     //Create the header for the difficulty screen.
    private HBox createHeader() {
        HBox header = new HBox();
        header.getStyleClass().add("header");
        header.setPadding(new Insets(10, 20, 10, 20));
        header.setAlignment(Pos.CENTER_LEFT);

        Label titleLabel = new Label("App Ea Ho Ithuta Sesotho");
        titleLabel.getStyleClass().add("header-title");

        HBox spacer = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button backButton = new Button("Morao");
        backButton.setOnAction(e -> navigateToCategoryScreen());

        header.getChildren().addAll(titleLabel, spacer, backButton);

        return header;
    }

     //Create the footer for the difficulty screen.
    private HBox createFooter() {
        HBox footer = new HBox();
        footer.getStyleClass().add("footer");
        footer.setAlignment(Pos.CENTER);

        Text footerText = new Text("© 2025 App Ea Ho Ithuta Sesotho - Ka Ntate Pitso");
        footerText.getStyleClass().add("footer-text");

        footer.getChildren().add(footerText);

        return footer;
    }

     // Start a game with the selected category and difficulty.
    private void startGame(Difficulty difficulty) {
        try {
            // Attempt to start the game
            boolean success = gameController.startGame(selectedCategory, difficulty);

            if (success) {
                // Navigate to the question screen if game started successfully
                QuestionScreen questionScreen = new QuestionScreen();

                // Replace the scene
                Scene currentScene = getScene();
                Stage stage = (Stage) currentScene.getWindow();
                Scene newScene = new Scene(questionScreen, currentScene.getWidth(), currentScene.getHeight());
                newScene.getStylesheets().addAll(currentScene.getStylesheets());

                stage.setScene(newScene);
            } else {
                // If game failed to start, show an error (this should not happen if UI disabled locked difficulties)
                System.err.println("Failed to start game: " + selectedCategory.getName() + " - " + difficulty.getDisplayName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


     //Navigate back to the category screen.
    private void navigateToCategoryScreen() {
        try {
            // Create the category screen
            CategoryScreen categoryScreen = new CategoryScreen();

            // Replace the scene
            Scene currentScene = getScene();
            Stage stage = (Stage) currentScene.getWindow();
            Scene newScene = new Scene(categoryScreen, currentScene.getWidth(), currentScene.getHeight());
            newScene.getStylesheets().addAll(currentScene.getStylesheets());

            stage.setScene(newScene);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}