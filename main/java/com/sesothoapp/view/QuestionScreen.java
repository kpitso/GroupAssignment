package com.sesothoapp.view;

import com.sesothoapp.controller.GameController;
import com.sesothoapp.model.Answer;
import com.sesothoapp.model.Question;
import com.sesothoapp.util.AnimationUtil;
import com.sesothoapp.util.ResourceLoader;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.List;

/*
 The screen for answering questions in the Sesotho Learning App.
 */
public class QuestionScreen extends BorderPane {

    private final GameController gameController;
    private VBox mainContent;
    private VBox questionContainer;
    private VBox answerContainer;
    private Label questionText;
    private ProgressBar progressBar;
    private Label progressLabel;
    private Button nextButton;
    private StackPane mediaContainer;

    // Timer related fields
    private final int QUESTION_TIME_SECONDS = 20;
    private IntegerProperty timeSeconds = new SimpleIntegerProperty(QUESTION_TIME_SECONDS);
    private Timeline timeline;
    private Label timerLabel;
    private ProgressBar timerProgressBar;

    /*
     Constructs a new question screen.
     */
    public QuestionScreen() {
        this.gameController = new GameController();

        // Set up the layout
        setupLayout();

        // Load the current question
        loadCurrentQuestion();
    }

    /*
    Set up the layout of the question screen.
     */
    private void setupLayout() {
        // Set up the layout components
        setTop(createHeader());

        // Main content
        mainContent = new VBox(20);
        mainContent.getStyleClass().add("main-content");

        // Progress information
        HBox progressContainer = createProgressContainer();

        // Question container with compact spacing
        questionContainer = new VBox(8);
        questionContainer.getStyleClass().add("question-container");
        questionContainer.setPadding(new Insets(10));
        questionContainer.setAlignment(Pos.CENTER);

        // Question text
        questionText = new Label();
        questionText.getStyleClass().add("question-text");
        questionText.setWrapText(true);
        questionText.setAlignment(Pos.CENTER);
        questionText.setMaxWidth(Double.MAX_VALUE);
        questionText.setStyle("-fx-font-size: 16px; -fx-alignment: center;");

        // Media container (for images)
        mediaContainer = new StackPane();
        mediaContainer.setMinHeight(180);
        mediaContainer.setMaxHeight(200);
        mediaContainer.setMinWidth(300);
        mediaContainer.setMaxWidth(450);
        mediaContainer.setAlignment(Pos.CENTER);
        mediaContainer.setStyle("-fx-background-color: linear-gradient(to bottom, #f8f9fa, #e9ecef); " +
                "-fx-border-color: #1976D2; -fx-border-width: 2px; " +
                "-fx-border-radius: 8px; -fx-background-radius: 8px; " +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 0, 2);");

        // Answer container
        answerContainer = new VBox(10);
        answerContainer.setAlignment(Pos.CENTER);
        answerContainer.setMaxWidth(Double.MAX_EXPONENT);
        answerContainer.setPadding(new Insets(3));

        // Make answer buttons fill the available width
        answerContainer.setFillWidth(true);

        // Next button - styled to be more visible
        nextButton = new Button("Potso E Latelang ►");
        nextButton.setDisable(true);
        nextButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px 20px; -fx-background-color: #e8e8e8;");
        nextButton.setOnAction(e -> nextQuestion());

        HBox buttonContainer = new HBox();
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.setPadding(new Insets(10, 0, 0, 0));
        buttonContainer.getChildren().add(nextButton);

        // Create a container for feedback
        VBox feedbackContainer = new VBox(5);
        feedbackContainer.setAlignment(Pos.CENTER);
        feedbackContainer.setMinHeight(40);

        questionContainer.getChildren().addAll(questionText, mediaContainer, answerContainer, feedbackContainer, buttonContainer);

        // Add all components to main content
        mainContent.getChildren().addAll(progressContainer, questionContainer);

        mainContent.setMaxHeight(Double.MAX_VALUE);
        mainContent.setMinHeight(550);

        // Make content more compact
        mainContent.setSpacing(5);
        questionContainer.setSpacing(8);
        answerContainer.setSpacing(3);

        VBox.setVgrow(questionContainer, Priority.ALWAYS);
        questionContainer.setMaxHeight(480);

        mainContent.setPadding(new Insets(15, 20, 30, 20));

        setCenter(mainContent);

        // Footer
        setBottom(createFooter());
    }
    //Create the progress indicator.
    private HBox createProgressContainer() {
        HBox container = new HBox(15);
        container.setAlignment(Pos.CENTER_LEFT);

        // Progress bar
        progressBar = new ProgressBar(0);
        progressBar.setPrefWidth(200);
        progressBar.setProgress(0);

        // Progress text
        progressLabel = new Label("Question 1 of 5");

        container.getChildren().addAll(progressBar, progressLabel);

        return container;
    }

    /*
     Load and display the current question.
     */
    private void loadCurrentQuestion() {
        Question currentQuestion = gameController.getCurrentQuestion();

        if (currentQuestion == null) {
            // No question available
            showResultsScreen();
            return;
        }

        // Create a fade transition for the question container
        FadeTransition fadeOut = new FadeTransition(Duration.millis(300), questionContainer);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.3);

        FadeTransition fadeIn = new FadeTransition(Duration.millis(300), questionContainer);
        fadeIn.setFromValue(0.3);
        fadeIn.setToValue(1.0);

        // When fade out completes, update the question content
        fadeOut.setOnFinished(e -> {
            updateQuestionContent(currentQuestion);
            fadeIn.play();
        });

        // Start the transition
        fadeOut.play();
    }

    /*
     Updates the question content without animations
     */
    private void updateQuestionContent(Question currentQuestion) {
        // Clear any previous feedback messages
        VBox feedbackContainer = (VBox) questionContainer.getChildren().get(3);
        feedbackContainer.getChildren().clear();

        // Reset the next button
        nextButton.setDisable(true);
        nextButton.getStyleClass().remove("primary-button");
        nextButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px 20px; -fx-background-color: #e8e8e8;");

        // Update question text
        questionText.setText(currentQuestion.getText());

        // Clear previous answers
        answerContainer.getChildren().clear();

        // Load image if available - with enhanced styling and animations
        mediaContainer.getChildren().clear();
        if (currentQuestion.getImageName() != null) {
            try {
                // Create a stylish container for the image - with minimal padding
                StackPane imageContainer = new StackPane();
                imageContainer.setPadding(new Insets(5));
                imageContainer.setStyle("-fx-background-color: white; " +
                        "-fx-background-radius: 6px; " +
                        "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.15), 8, 0, 0, 1);");

                // Load and style the image - with dimensions that won't hide other content
                ImageView imageView = new ImageView(ResourceLoader.loadImage(currentQuestion.getImageName()));
                imageView.setFitHeight(160);
                imageView.setFitWidth(240);
                imageView.setPreserveRatio(true);
                imageView.setSmooth(true);
                imageView.setCache(true);

                // Add the image to its container
                imageContainer.getChildren().add(imageView);

                // Create a more sophisticated entrance animation sequence
                FadeTransition fadeIn = new FadeTransition(Duration.millis(400), imageContainer);
                fadeIn.setFromValue(0);
                fadeIn.setToValue(1);

                ScaleTransition scaleIn = new ScaleTransition(Duration.millis(500), imageContainer);
                scaleIn.setFromX(0.92);
                scaleIn.setFromY(0.92);
                scaleIn.setToX(1.0);
                scaleIn.setToY(1.0);

                // Combine animations
                ParallelTransition imageEntrance = new ParallelTransition(fadeIn, scaleIn);

                // Add to main container and play animation
                mediaContainer.getChildren().add(imageContainer);
                imageEntrance.play();
            } catch (Exception e) {
                System.err.println("Error loading image: " + currentQuestion.getImageName());
                mediaContainer.setVisible(false);
            }
        } else {
            mediaContainer.setVisible(false);
        }

        // Create buttons for each answer with staggered animations
        for (int i = 0; i < currentQuestion.getAnswers().size(); i++) {
            Answer answer = currentQuestion.getAnswers().get(i);
            Button answerButton = createAnswerButton(answer);

            // Apply initial state for animation
            answerButton.setOpacity(0);
            answerButton.setTranslateX(20);

            answerContainer.getChildren().add(answerButton);

            // Create a combined animation for each button
            PauseTransition delay = new PauseTransition(Duration.millis(100 * i));
            FadeTransition fadeIn = AnimationUtil.createFadeInTransition(answerButton);
            TranslateTransition slideIn = AnimationUtil.createTranslateTransition(answerButton, -20, 0);

            ParallelTransition combined = new ParallelTransition(fadeIn, slideIn);
            SequentialTransition seq = new SequentialTransition(delay, combined);
            seq.play();
        }

        // Update progress
        int currentQuestionNumber = gameController.getCurrentQuestionNumber();
        int totalQuestions = gameController.getTotalQuestions();
        progressBar.setProgress((double) (currentQuestionNumber - 1) / totalQuestions);
        progressLabel.setText("Question " + currentQuestionNumber + " of " + totalQuestions);

        // Disable next button until an answer is selected
        nextButton.setDisable(true);

        // Reset and start the timer
        resetTimer();
        startTimer();
    }

     //Resets the timer back to the initial time
    private void resetTimer() {
        // Stop any existing timer
        if (timeline != null) {
            timeline.stop();
        }

        // Reset time seconds to initial value
        timeSeconds.set(QUESTION_TIME_SECONDS);

        // Reset progress bar
        timerProgressBar.setProgress(1.0);
        timerProgressBar.setStyle("-fx-accent: #4CAF50;");
    }

    /*
     Starts the timer for the current question
     */
    private void startTimer() {
        // Create a new timeline for the timer
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        // Create a key frame that triggers every second
        KeyFrame frame = new KeyFrame(Duration.seconds(1), event -> {
            // Decrement the timer
            int currentTime = timeSeconds.get();
            timeSeconds.set(currentTime - 1);

            // Update progress bar
            double progress = (double) currentTime / QUESTION_TIME_SECONDS;
            timerProgressBar.setProgress(progress);

            // Change color based on time remaining
            if (currentTime <= 10) {
                timerProgressBar.setStyle("-fx-accent: #F44336;"); // Red for last 10 seconds
            } else if (currentTime <= 20) {
                timerProgressBar.setStyle("-fx-accent: #FFC107;"); // Yellow for 20-10 seconds
            }

            // Check if time is up
            if (currentTime <= 1) {
                // Time's up - handle it
                handleTimeUp();
            }
        });

        timeline.getKeyFrames().add(frame);
        timeline.play();
    }

    /*
     Handles the situation when time runs out for a question
     */
    private void handleTimeUp() {
        // Stop the timer
        timeline.stop();

        // Display a time's up message
        VBox feedbackContainer = (VBox) questionContainer.getChildren().get(3);
        feedbackContainer.getChildren().clear(); // Clear any existing content
        feedbackContainer.setAlignment(Pos.CENTER);
        feedbackContainer.setPadding(new Insets(15));
        feedbackContainer.getStyleClass().add("feedback-container");
        feedbackContainer.setStyle("-fx-background-color: #FFEBEE; -fx-border-color: #F44336; -fx-border-width: 1px; -fx-border-radius: 4px;");

        Label timeUpLabel = new Label("Time's up!");
        timeUpLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #F44336; -fx-font-weight: bold;");

        feedbackContainer.getChildren().add(timeUpLabel);

        // Add animation to the feedback
        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), feedbackContainer);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.play();

        // Disable all answer buttons
        for (javafx.scene.Node node : answerContainer.getChildren()) {
            if (node instanceof Button) {
                node.setDisable(true);
            }
        }

        // Enable the next button to move to the next question
        nextButton.setDisable(false);
        nextButton.getStyleClass().add("primary-button");
        nextButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px 20px; -fx-background-color: #4285f4; -fx-text-fill: white;");
    }

     //Create a button for an answer.
    private Button createAnswerButton(Answer answer) {
        Button button = new Button(answer.getText());
        button.getStyleClass().add("answer-button");
        button.setMaxWidth(Double.MAX_VALUE);

        // Make buttons more compact with smaller padding and font size
        button.setStyle("-fx-padding: 6px 10px; -fx-font-size: 14px;");

        button.setOnAction(e -> handleAnswerSelection(answer, button));
        return button;
    }
    /*
     Handle selecting an answer.
     */
    private void handleAnswerSelection(Answer answer, Button selectedButton) {
        // Stop the timer when an answer is selected
        if (timeline != null) {
            timeline.stop();
        }
        // Submit the answer
        boolean isCorrect = gameController.submitAnswer(answer);

        // Update button styles
        List<Button> buttons = answerContainer.getChildren().stream()
                .filter(node -> node instanceof Button)
                .map(node -> (Button) node)
                .toList();

        for (Button button : buttons) {
            // Disable all buttons
            button.setDisable(true);

            // If this is the selected button, show if it's correct
            if (button == selectedButton) {
                if (isCorrect) {
                    button.getStyleClass().add("correct");
                } else {
                    button.getStyleClass().add("incorrect");
                }
            }
        }

        // Show enhanced, more immediate feedback message
        VBox feedbackBox = new VBox(2);
        feedbackBox.setAlignment(Pos.CENTER);
        feedbackBox.setPadding(new Insets(2));
        feedbackBox.setMaxWidth(Double.MAX_EXPONENT);  // Set preferred width
        feedbackBox.setPrefHeight(100); // Set preferred height
        feedbackBox.setStyle("-fx-background-color: " + (isCorrect ? "rgba(40, 167, 69, 0.2)" : "rgba(220, 53, 69, 0.2)") +
                "; -fx-border-color: " + (isCorrect ? "rgba(40, 167, 69, 0.8)" : "rgba(220, 53, 69, 0.8)") +
                "; -fx-border-width: 2px; -fx-border-radius: 10px; -fx-background-radius: 10px;" +
                "-fx-effect: dropshadow(three-pass-box, " + (isCorrect ? "rgba(40, 167, 69, 0.5)" : "rgba(220, 53, 69, 0.5)") + ", 10, 0, 0, 0);");

        Label feedbackIcon = new Label(isCorrect ? "✓" : "✗");
        feedbackIcon.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: " +
                (isCorrect ? "#006400" : "#721c24") + ";");

        Label feedbackLabel = new Label(isCorrect ? "Karabo e nepahetse! 🎉" : "Karabo e fosahetse 😕");
        feedbackLabel.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: " +
                (isCorrect ? "#006400" : "#721c24") + ";");

////        Label explanationLabel = new Label(isCorrect ?
////                "U fumane karabo hantle!" :
////                "Hopola ho bala potso ka hloko.");
////        explanationLabel.setStyle("-fx-font-size: 11px; -fx-text-fill: " +
////                (isCorrect ? "#006400" : "#721c24") + ";");
//        explanationLabel.setWrapText(true);


        // Add current score information to the feedback
        Label scoreLabel = new Label("Current Score: " + gameController.getCorrectAnswers() +
                " out of " + gameController.getTotalQuestions());
        scoreLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        feedbackBox.getChildren().addAll(feedbackIcon, feedbackLabel, scoreLabel);

        // Find the feedback container and replace its content
        VBox feedbackContainer = (VBox) questionContainer.getChildren().get(3);
        feedbackContainer.getChildren().clear(); // Clear any previous feedback
        feedbackContainer.getChildren().add(feedbackBox);

        // Update user's score in the progress display
        updateScoreDisplay();

        // Enable next button
        nextButton.setDisable(false);
        nextButton.getStyleClass().add("primary-button");
        nextButton.setStyle("-fx-font-size: 14px; -fx-padding: 10px 25px; " +
                "-fx-background-color: #2c7fb8; -fx-text-fill: white; " +
                "-fx-font-weight: bold; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 5, 0, 0, 1);");

        // Get the button container (last element in the questionContainer)
        // It's actually the 5th element (index 4) which is the HBox we created for the button
        if (questionContainer.getChildren().size() >= 5) {
            javafx.scene.Node node = questionContainer.getChildren().get(4);
            if (node instanceof HBox) {
                HBox buttonContainer = (HBox) node;
                buttonContainer.setPadding(new Insets(30, 0, 20, 0));
                buttonContainer.setAlignment(Pos.CENTER);
            }
        }
    }
    private void updateScoreDisplay() {
        int score = gameController.getCorrectAnswers();
        int totalAnswered = gameController.getCurrentQuestionNumber() - 1;
        int totalQuestions = gameController.getTotalQuestions();

        double progress = (double) totalAnswered / totalQuestions;
        progressBar.setProgress(progress);
        progressLabel.setText("Potso " + totalAnswered + " ho tse " + totalQuestions +
                " | Karabontle: " + score);
    }

    /*
     Move to the next question.
     */
    private void nextQuestion() {
        if (questionContainer.getChildren().size() >= 4) {
            VBox feedbackContainer = (VBox) questionContainer.getChildren().get(3);
            if (feedbackContainer != null) {
                feedbackContainer.getChildren().clear();

                feedbackContainer.setStyle("");
                feedbackContainer.getStyleClass().remove("feedback-container");
                feedbackContainer.setPadding(new Insets(0));
            }
        }
        // Disable the next button while loading the next question
        nextButton.setDisable(true);
        nextButton.getStyleClass().remove("primary-button");
        nextButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px 20px; -fx-background-color: #e8e8e8;");

        if (gameController.hasNextQuestion()) {
            gameController.nextQuestion();
            loadCurrentQuestion();
        } else {
            gameController.endGame();
            showResultsScreen();
        }
    }

    /*
     Show the results screen.
     */
    private void showResultsScreen() {
        // Stop the timer if it's running
        if (timeline != null) {
            timeline.stop();
        }

        try {
            // Create the results screen
            ResultsScreen resultsScreen = new ResultsScreen();

            // Get current scene and stage
            Scene currentScene = getScene();
            Stage stage = (Stage) currentScene.getWindow();

            // Create new scene with same dimensions and styles
            Scene newScene = new Scene(resultsScreen, currentScene.getWidth(), currentScene.getHeight());
            newScene.getStylesheets().addAll(currentScene.getStylesheets());

            // Apply initial state for animation
            resultsScreen.setOpacity(0);

            // First set the scene immediately
            stage.setScene(newScene);

            // Then create and play the fade-in effect
            FadeTransition fadeIn = new FadeTransition(Duration.millis(600), resultsScreen);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     Create the header for the question screen
     */
    private HBox createHeader() {
        HBox header = new HBox();
        header.getStyleClass().add("header");
        header.setPadding(new Insets(10, 20, 10, 20));
        header.setAlignment(Pos.CENTER_LEFT);

        Label titleLabel = new Label("App Ea Ho Ithuta Sesotho");
        titleLabel.getStyleClass().add("header-title");

        // Create timer display
        HBox timerBox = createTimerDisplay();

        HBox spacer = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button exitButton = new Button("Tsoa Papaling");
        exitButton.setOnAction(e -> confirmExitGame());

        header.getChildren().addAll(titleLabel, spacer, timerBox, new HBox(10), exitButton);

        return header;
    }
    /*
     Creates a timer display with countdown timer and progress bar
     HBox containing the timer components
     */
    private HBox createTimerDisplay() {
        HBox timerBox = new HBox(10);
        timerBox.setAlignment(Pos.CENTER);

        // Create timer label
        timerLabel = new Label();
        timerLabel.textProperty().bind(timeSeconds.asString().concat(" s"));
        timerLabel.getStyleClass().add("timer-label");

        // Create timer progress bar
        timerProgressBar = new ProgressBar(1);
        timerProgressBar.setPrefWidth(100);
        timerProgressBar.getStyleClass().add("timer-progress");

        timerBox.getChildren().addAll(new Label("Time: "), timerLabel, timerProgressBar);

        return timerBox;
    }
    /*
     Create the footer for the question screen.
     */
    private HBox createFooter() {
        HBox footer = new HBox();
        footer.getStyleClass().add("footer");
        footer.setAlignment(Pos.CENTER);

        Text footerText = new Text("© 2025 App Ea Ho Ithuta Sesotho  - Ka Ntate Pitsp");
        footerText.getStyleClass().add("footer-text");

        footer.getChildren().add(footerText);

        return footer;
    }

     //Confirm exiting the game.
    private void confirmExitGame() {
        // In a real app, you might want to show a confirmation dialog here
        gameController.endGame();
        navigateToCategoryScreen();
    }

     //Navigate back to the category screen.
    private void navigateToCategoryScreen() {
        // Stop the timer if it's running
        if (timeline != null) {
            timeline.stop();
        }

        try {
            // Create the category screen
            CategoryScreen categoryScreen = new CategoryScreen();

            // Get current scene and stage
            Scene currentScene = getScene();
            Stage stage = (Stage) currentScene.getWindow();

            // Create new scene with same dimensions and styles
            Scene newScene = new Scene(categoryScreen, currentScene.getWidth(), currentScene.getHeight());
            newScene.getStylesheets().addAll(currentScene.getStylesheets());

            // Create a fade-out effect for the current screen
            FadeTransition fadeOut = new FadeTransition(Duration.millis(300), this);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);

            // When fade-out is complete, switch scenes and fade in
            fadeOut.setOnFinished(e -> {
                // Apply initial state for animation
                categoryScreen.setOpacity(0);

                // Set the new scene
                stage.setScene(newScene);

                // Create and play the fade-in effect
                FadeTransition fadeIn = new FadeTransition(Duration.millis(500), categoryScreen);
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);

                // Add a bounce effect for the category cards
                PauseTransition pause = new PauseTransition(Duration.millis(200));
                pause.setOnFinished(event -> {
                    // The AnimationUtil will automatically add animations to the category cards
                    // when they're created in the CategoryScreen class
                });

                // Play the sequence
                SequentialTransition sequence = new SequentialTransition(fadeIn, pause);
                sequence.play();
            });

            // Start the transition
            fadeOut.play();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}