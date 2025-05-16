package com.sesothoapp.view;

import com.sesothoapp.controller.GameController;
import com.sesothoapp.model.Category;
import com.sesothoapp.model.Difficulty;
import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.List;

/*
 * The progress screen for the Sesotho Learning App.
 */
public class ProgressScreen extends BorderPane {

    private final GameController gameController;
    private VBox mainContent;


     // Constructs a new progress screen.
    public ProgressScreen() {
        this.gameController = new GameController();

        // Set up the layout
        setupLayout();
    }


     //Set up the layout of the progress screen.
    private void setupLayout() {
        // Set up the layout components
        setTop(createHeader());

        // Main content
        mainContent = new VBox(20);
        mainContent.getStyleClass().add("main-content");
        mainContent.setPadding(new Insets(20));
        mainContent.setAlignment(Pos.CENTER);

        // Title
        Label title = new Label("Tsoelo-pele ea hao");
        title.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;-fx-text-fill: #5D3954;");



        // Progress grid
        GridPane progressGrid = createProgressGrid();

        mainContent.getChildren().addAll(title, progressGrid);
        setCenter(mainContent);

        // Footer
        setBottom(createFooter());
    }


     // Create the grid of progress information.
    private GridPane createProgressGrid() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(60);
        grid.setVgap(40);
        grid.setPadding(new Insets(20));
        grid.setStyle("-fx-background-color: #DEB887; -fx-background-radius: 10px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 8, 0, 0, 1);");

        // Headers
        Label categoryHeader = new Label("Category");
        categoryHeader.setStyle("-fx-font-weight: bold;");
        grid.add(categoryHeader, 0, 0);

        Label easyHeader = new Label("Bonolo");
        easyHeader.setStyle("-fx-font-weight: bold;");
        grid.add(easyHeader, 1, 0);

        Label mediumHeader = new Label("Bohareng");
        mediumHeader.setStyle("-fx-font-weight: bold;");
        grid.add(mediumHeader, 2, 0);

        Label hardHeader = new Label("Thata");
        hardHeader.setStyle("-fx-font-weight: bold;");
        grid.add(hardHeader, 3, 0);

        // Get all categories
        List<Category> categories = gameController.getAllCategories();

        // Add a row for each category
        int row = 1;
        for (Category category : categories) {
            // Category name
            Label categoryName = new Label(category.getName());
            categoryName.setStyle("-fx-font-weight: bold;");
            grid.add(categoryName, 0, row);

            // Progress for each difficulty
            addDifficultyProgress(grid, category, Difficulty.EASY, 1, row);
            addDifficultyProgress(grid, category, Difficulty.MEDIUM, 2, row);
            addDifficultyProgress(grid, category, Difficulty.HARD, 3, row);

            row++;
        }

        return grid;
    }

     // Add a progress display for a category and difficulty.
    private void addDifficultyProgress(GridPane grid, Category category,
                                       Difficulty difficulty, int col, int row) {
        VBox container = new VBox(5);
        container.setAlignment(Pos.CENTER);

        // Get the highest score for this category and difficulty
        int score = gameController.getCurrentUser().getHighestScore(category.getId(), difficulty);

        // Show a progress bar for the score
        ProgressBar progressBar = new ProgressBar(score / 100.0);
        progressBar.setPrefWidth(100);

        // Show the percentage
        Label scoreLabel = new Label(score + "%");

        // Check if this difficulty is unlocked
        boolean isUnlocked = gameController.isDifficultyUnlocked(category, difficulty);
        if (!isUnlocked && difficulty != Difficulty.EASY) {
            // Show locked status
            progressBar.setDisable(true);
            Label lockedLabel = new Label("Locked");
            lockedLabel.setStyle("-fx-text-fill: darkblue;");
            container.getChildren().addAll(progressBar, lockedLabel);
        } else {
            container.getChildren().addAll(progressBar, scoreLabel);
        }

        grid.add(container, col, row);
    }

     //Create the header for the progress screen.
    private HBox createHeader() {
        HBox header = new HBox();
        header.getStyleClass().add("header");
        header.setPadding(new Insets(10, 20, 10, 20));
        header.setAlignment(Pos.CENTER_LEFT);

        Label titleLabel = new Label("App Ea ho Ithuta Sesotho");
        titleLabel.getStyleClass().add("header-title");

        HBox spacer = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button backButton = new Button("Morao");
        backButton.setOnAction(e -> navigateToHomeScreen());

        header.getChildren().addAll(titleLabel, spacer, backButton);

        return header;
    }

     // Create the footer for the progress screen.
    private HBox createFooter() {
        HBox footer = new HBox();
        footer.getStyleClass().add("footer");
        footer.setAlignment(Pos.CENTER);

        Text footerText = new Text("© 2025 App Ea Ho Ithuta Sesotho-Ka Ntate Pitso");
        footerText.getStyleClass().add("footer-text");

        footer.getChildren().add(footerText);

        return footer;
    }

     //Navigate back to the home screen.
    private void navigateToHomeScreen() {
        try {
            // Create the home screen
            HomeScreen homeScreen = new HomeScreen();

            // Replace the scene
            Scene currentScene = getScene();
            if (currentScene == null) {
                System.err.println("Error: Current scene is null");
                return;
            }

            if (currentScene.getWindow() == null) {
                System.err.println("Error: Window is null");
                return;
            }

            Stage stage = (Stage) currentScene.getWindow();
            double width = currentScene.getWidth();
            double height = currentScene.getHeight();

            // Add smooth transition
            javafx.animation.FadeTransition fadeOut = new javafx.animation.FadeTransition(Duration.millis(300), this);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);

            fadeOut.setOnFinished(e -> {
                // Create and set the new scene
                Scene newScene = new Scene(homeScreen, width, height);
                if (currentScene.getStylesheets() != null) {
                    newScene.getStylesheets().addAll(currentScene.getStylesheets());
                }

                stage.setScene(newScene);

                // Fade in the home screen
                homeScreen.setOpacity(0);
                javafx.animation.FadeTransition fadeIn = new javafx.animation.FadeTransition(Duration.millis(300), homeScreen);
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);
                fadeIn.play();
            });

            // Start the transition
            fadeOut.play();

        } catch (Exception e) {
            System.err.println("Error navigating to home screen: " + e.getMessage());
            e.printStackTrace();
        }
    }
}