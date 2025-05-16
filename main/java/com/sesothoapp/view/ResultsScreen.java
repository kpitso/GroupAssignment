package com.sesothoapp.view;

import com.sesothoapp.controller.GameController;
import com.sesothoapp.model.Category;
import com.sesothoapp.model.Difficulty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
 * The results screen for the Sesotho Learning App.
 */
public class ResultsScreen extends BorderPane {

    private final GameController gameController;
    private VBox mainContent;

     // Constructs a new results screen.
    public ResultsScreen() {
        this.gameController = new GameController();

        // Set up the layout
        setupLayout();
    }

     // Set up the layout of the results screen.
    private void setupLayout() {
        // Set up the layout components
        setTop(createHeader());

        // Main content
        mainContent = new VBox(20);
        mainContent.getStyleClass().add("main-content");
        mainContent.setPadding(new Insets(20));

        // Results container
        VBox resultsContainer = createResultsContainer();

        // Add all components to the main content
        mainContent.getChildren().add(resultsContainer);
        setCenter(mainContent);

        // Footer
        setBottom(createFooter());
    }

     //Create the results container showing the game results.
    private VBox createResultsContainer() {
        VBox container = new VBox(20);
        container.getStyleClass().add("results-container");
        container.setAlignment(Pos.CENTER);
        container.setPadding(new Insets(30));

        // Get game results
        Category category = gameController.getCurrentCategory();
        Difficulty difficulty = gameController.getCurrentDifficulty();
        int correctAnswers = gameController.getCorrectAnswers();
        int totalQuestions = gameController.getTotalQuestions();

        // Debug statements to diagnose calculation issues
        System.out.println("DEBUG RESULTS: Correct answers: " + correctAnswers);
        System.out.println("DEBUG RESULTS: Total questions: " + totalQuestions);

        // Ensure we have valid values before calculating
        if (totalQuestions <= 0) {
            totalQuestions = 5; // Fallback to 5 questions if total is invalid
            System.out.println("WARNING: Invalid total questions count, using fallback value of 5");
        }

        // Manual calculation of percentage to ensure accuracy
        int scorePercentage = (totalQuestions > 0) ? (correctAnswers * 100) / totalQuestions : 0;
        System.out.println("DEBUG RESULTS: Calculated score percentage: " + scorePercentage + "%");

        boolean passed = scorePercentage >= 60;

        // Results title
        Label titleLabel = new Label("Sephetho tsa Papali");
        titleLabel.getStyleClass().add("results-title");

        // Category and difficulty
        Label categoryLabel = new Label(category.getName() + " - " + difficulty.getDisplayName());
        categoryLabel.setStyle("-fx-font-size: 18px;");

        // Score with enhanced visibility
        Label scoreLabel = new Label(scorePercentage + "%");
        scoreLabel.getStyleClass().addAll("results-score", passed ? "passed" : "failed");

        // Correct answers - made more prominent with detailed information
        Label correctLabel = new Label(correctAnswers + " out of " + totalQuestions + " correct");
        correctLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-padding: 5px;");

        // Add detailed score explanation
        Label scoreExplanationLabel = new Label(" U arabile lipotso tse " + correctAnswers +
                " ka nepo, holima tse " + totalQuestions +
                " se akaretsang.");
        scoreExplanationLabel.setStyle("-fx-font-size: 15px; -fx-padding: 5px;");
        scoreExplanationLabel.setWrapText(true);

        // Progress bar showing advancement toward next level
        VBox progressBox = createProgressIndicator(difficulty, scorePercentage);

        // Result message
        Label messageLabel = new Label(getResultMessage(passed, scorePercentage));
        messageLabel.getStyleClass().add("results-message");
        messageLabel.setWrapText(true);

        // Buttons
        HBox buttonContainer = new HBox(20);
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.setPadding(new Insets(20, 0, 0, 0));

        Button playAgainButton = new Button("Bapala Hape");
        playAgainButton.setOnAction(e -> navigateToCategoryScreen());

        Button homeButton = new Button("Lehae");
        homeButton.getStyleClass().add("secondary-button");
        homeButton.setOnAction(e -> navigateToHomeScreen());

        buttonContainer.getChildren().addAll(playAgainButton, homeButton);

        // Add all elements to the container
        container.getChildren().addAll(
                titleLabel,
                categoryLabel,
                scoreLabel,
                correctLabel,
                scoreExplanationLabel,
                progressBox,
                messageLabel,
                buttonContainer
        );

        return container;
    }


     // Get an appropriate result message based on score.
    private String getResultMessage(boolean passed, int scorePercentage) {
        Difficulty currentDifficulty = gameController.getCurrentDifficulty();

        // Thresholds for passing each difficulty level
        int threshold = getThresholdForDifficulty(currentDifficulty);
        String nextLevelText = getNextLevelName(currentDifficulty);

        // Debug result calculation
        System.out.println("DEBUG MESSAGE: Current difficulty: " + currentDifficulty.getDisplayName());
        System.out.println("DEBUG MESSAGE: Score: " + scorePercentage + "%, Threshold: " + threshold + "%");
        System.out.println("DEBUG MESSAGE: Passed: " + passed);

        // Message based on score and current difficulty
        if (scorePercentage >= 90) {
            return "Excellent! You've mastered this level with " + scorePercentage + "% correct!";
        } else if (scorePercentage >= threshold) {
            if (currentDifficulty.hasNextDifficulty()) {
                return "Well done! With " + scorePercentage + "%, you've unlocked the " + nextLevelText + " difficulty level!";
            } else {
                return "Congratulations! With " + scorePercentage + "%, you've completed all difficulty levels for this category!";
            }
        } else if (scorePercentage >= threshold - 10) {
            int needed = threshold - scorePercentage;
            return "Almost there! You scored " + scorePercentage + "%. You need " + needed + "% more to reach " + threshold + "% and unlock the next level.";
        } else {
            return "Keep practicing! You scored " + scorePercentage + "%. You need " + threshold + "% to unlock the next difficulty level.";
        }
    }

     // Get the threshold percentage needed to pass a difficulty level
    private int getThresholdForDifficulty(Difficulty difficulty) {
        if (difficulty == Difficulty.EASY) {
            return 60;
        } else if (difficulty == Difficulty.MEDIUM) {
            return 75;
        } else if (difficulty == Difficulty.HARD) {
            return 90;
        }
        return 70; // Default fallback
    }

     // Get the name of the next difficulty level
    private String getNextLevelName(Difficulty difficulty) {
        if (difficulty == Difficulty.EASY) {
            return "MEDIUM";
        }
        else if (difficulty == Difficulty.MEDIUM) {
            return "HARD";
        }
        else {
            return "";
        }
    }


     // Create a visual indicator of progress toward the next level
    private VBox createProgressIndicator(Difficulty difficulty, int scorePercentage) {
        VBox container = new VBox(10);
        container.setAlignment(Pos.CENTER);
        container.setPadding(new Insets(10, 0, 10, 0));

        // Progress toward next level
        int threshold = getThresholdForDifficulty(difficulty);

        // Ensure score percentage is valid (prevent negative progress)
        scorePercentage = Math.max(0, scorePercentage);

        // Debug calculation of progress
        System.out.println("DEBUG PROGRESS: Score percentage: " + scorePercentage + "%, threshold: " + threshold + "%");
        System.out.println("DEBUG PROGRESS: Progress ratio: " + ((double) scorePercentage / 100));

        // Progress bar - ensure it shows the actual progress
        ProgressBar progressBar = new ProgressBar((double) scorePercentage / 100);
        progressBar.setPrefWidth(300);
        progressBar.setMinHeight(15);
        progressBar.setStyle("-fx-accent: " + (scorePercentage >= threshold ? "#28a745" : "#f0ad4e") + ";");

        // Text for thresholds
        HBox thresholdMarkers = new HBox();
        thresholdMarkers.setPrefWidth(300);
        thresholdMarkers.setAlignment(Pos.CENTER_LEFT);

        // Title for the progress section
        String titleText = difficulty.hasNextDifficulty() ?
                "Progress towards " + getNextLevelName(difficulty) + " level" :
                "Mastery level";
        Label title = new Label(titleText);
        title.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        // Create indicators for Easy, Medium, Hard thresholds
        HBox indicators = new HBox(68); // Spaced to match the thresholds visually
        indicators.setAlignment(Pos.CENTER_LEFT);
        indicators.setPadding(new Insets(5, 0, 0, 0));

        // Easy threshold (60%)
        VBox easyBox = createThresholdIndicator("Easy: 60%", 60, scorePercentage, threshold);

        // Medium threshold (75%)
        VBox mediumBox = createThresholdIndicator("Medium: 75%", 75, scorePercentage, threshold);

        // Hard threshold (90%)
        VBox hardBox = createThresholdIndicator("Hard: 90%", 90, scorePercentage, threshold);

        indicators.getChildren().addAll(easyBox, mediumBox, hardBox);

        container.getChildren().addAll(title, progressBar, indicators);
        return container;
    }

    //Create an indicator for a specific threshold level
    private VBox createThresholdIndicator(String label, int thresholdValue, int scorePercentage, int requiredThreshold) {
        VBox box = new VBox(5);
        box.setAlignment(Pos.CENTER);

        // Circle indicator
        Circle circle = new Circle(8);

        // Determine color based on whether this threshold is reached and if it's the required one
        if (scorePercentage >= thresholdValue) {
            circle.setFill(Color.valueOf("#28a745"));
        }
        else if (thresholdValue == requiredThreshold) {
            circle.setFill(Color.valueOf("#f0ad4e"));
        }
        else
        {
            circle.setFill(Color.valueOf("#6c757d"));
        }

        circle.setStroke(Color.valueOf("#343a40"));
        circle.setStrokeWidth(1.5);

        // Label
        Label text = new Label(label);
        text.setStyle("-fx-font-size: 12px;");

        box.getChildren().addAll(circle, text);
        return box;
    }

     //Create the header for the results screen.
    private HBox createHeader() {
        HBox header = new HBox();
        header.getStyleClass().add("header");
        header.setPadding(new Insets(10, 20, 10, 20));
        header.setAlignment(Pos.CENTER_LEFT);

        Label titleLabel = new Label("App Ea Ho Ithuta Sesotho");
        titleLabel.getStyleClass().add("header-title");

        HBox spacer = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        header.getChildren().addAll(titleLabel, spacer);

        return header;
    }

     // Create the footer for the results screen.
    private HBox createFooter() {
        HBox footer = new HBox();
        footer.getStyleClass().add("footer");
        footer.setAlignment(Pos.CENTER);

        Text footerText = new Text("© 2025 App Ea Ho Ithuta Sesotho - Ka Ntate Pitso");
        footerText.getStyleClass().add("footer-text");

        footer.getChildren().add(footerText);

        return footer;
    }

    //Navigate to the category screen.
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


     // Navigate to the home screen.
    private void navigateToHomeScreen() {
        try {
            // Create the home screen
            HomeScreen homeScreen = new HomeScreen();

            // Replace the scene
            Scene currentScene = getScene();
            Stage stage = (Stage) currentScene.getWindow();
            Scene newScene = new Scene(homeScreen, currentScene.getWidth(), currentScene.getHeight());
            newScene.getStylesheets().addAll(currentScene.getStylesheets());

            stage.setScene(newScene);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}