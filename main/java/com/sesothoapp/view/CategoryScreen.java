package com.sesothoapp.view;

import com.sesothoapp.controller.GameController;
import com.sesothoapp.model.Category;
import com.sesothoapp.util.AnimationUtil;
import javafx.animation.*;
import javafx.geometry.Insets;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.List;

/*
 The category selection screen
 */
public class CategoryScreen extends BorderPane {

    private final GameController gameController;
    private VBox mainContent;

     //Constructs a new category screen.
    public CategoryScreen() {
        this.gameController = new GameController();

        // Set up the layout
        setupLayout();
    }

      //Set up the layout of the category screen.
    private void setupLayout() {
        // Set up the layout components
        setTop(createHeader());

        // Main content
        mainContent = new VBox(20);
        mainContent.getStyleClass().add("main-content");
        mainContent.setPadding(new Insets(20));
        mainContent.setStyle("-fx-background-color: rgba(235, 234, 234, 0.8);;");

        // Add a title for the category selection
        Label title = new Label("Khetha Karolo");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Add the category cards
        FlowPane categoryContainer = createCategoryCards();

        mainContent.getChildren().addAll(title, categoryContainer);
        setCenter(mainContent);

        // Footer
        setBottom(createFooter());
    }

     // Create the category cards for selection.
    private FlowPane createCategoryCards() {
        FlowPane flowPane = new FlowPane();
        flowPane.setHgap(20);
        flowPane.setVgap(20);
        flowPane.setPadding(new Insets(10));
        flowPane.setAlignment(Pos.CENTER);

        // Get all categories from the game controller
        List<Category> categories = gameController.getAllCategories();

        // Create a card for each category
        for (Category category : categories) {
            VBox card = createCategoryCard(category);
            flowPane.getChildren().add(card);
        }

        return flowPane;
    }

     //Create a card for a category.
    private VBox createCategoryCard(Category category) {
        VBox card = new VBox(10);
        card.getStyleClass().add("category-card");
        card.setPadding(new Insets(15));
        card.setPrefWidth(250);
        card.setPrefHeight(200);

        // Load category icon
        ImageView icon = new ImageView();
        String iconName = category.getIconName() + ".png";
        Image iconImage = com.sesothoapp.util.ResourceLoader.loadImage(iconName);
        icon.setImage(iconImage);
        icon.setFitWidth(64);
        icon.setFitHeight(64);
        icon.setPreserveRatio(true);

        // Apply initial rotation animation to the icon
        SequentialTransition initialRotation = AnimationUtil.createFullRotation(icon);
        PauseTransition delayStart = new PauseTransition(Duration.millis(300));
        SequentialTransition delayedRotation = new SequentialTransition(delayStart, initialRotation);
        delayedRotation.play();

        // Title
        Label title = new Label(category.getName());
        title.getStyleClass().add("category-card-title");

        // Description
        Label description = new Label(category.getDescription());
        description.getStyleClass().add("category-card-description");
        description.setWrapText(true);

        card.getChildren().addAll(icon, title, description);
        card.setAlignment(Pos.CENTER);

        // Add hover effects
        card.setOnMouseEntered(e -> {
            // Scale up card slightly
            ScaleTransition scaleUp = AnimationUtil.createScaleTransition(card, 1.0, 1.05);
            scaleUp.play();

            // Give icon a subtle pulse
            SequentialTransition pulse = AnimationUtil.createPulseAnimation(icon);
            pulse.play();
        });

        card.setOnMouseExited(e -> {
            // Scale back down
            ScaleTransition scaleDown = AnimationUtil.createScaleTransition(card, 1.05, 1.0);
            scaleDown.play();
        });

        // Set click animation and action
        card.setOnMouseClicked(e -> {
            // Create a click animation
            ParallelTransition clickEffect = new ParallelTransition();

            // Create a quick scale down/up effect
            ScaleTransition scaleDown = AnimationUtil.createScaleTransition(card, 1.05, 0.95);
            scaleDown.setDuration(Duration.millis(100));

            ScaleTransition scaleUp = AnimationUtil.createScaleTransition(card, 0.95, 1.0);
            scaleUp.setDuration(Duration.millis(100));

            SequentialTransition scaleEffect = new SequentialTransition(scaleDown, scaleUp);

            // Create a rotation for the icon
            RotateTransition iconSpin = AnimationUtil.createRotationTransition(icon, 360);
            iconSpin.setDuration(Duration.millis(500));

            // Add both animations to parallel transition
            clickEffect.getChildren().addAll(scaleEffect, iconSpin);

            // When animation finishes, navigate to difficulty screen
            clickEffect.setOnFinished(event -> navigateToDifficultyScreen(category));

            // Play the animation
            clickEffect.play();
        });

        return card;
    }

     // Create the header for the category screen.
    private HBox createHeader() {
        HBox header = new HBox();
        header.getStyleClass().add("header");
        header.setPadding(new Insets(10, 20, 10, 20));
        header.setAlignment(Pos.CENTER_LEFT);

        Label titleLabel = new Label("App Ea Ho Ithuta sesotho");
        titleLabel.getStyleClass().add("header-title");

        HBox spacer = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button backButton = new Button("Morao");
        backButton.setOnAction(e -> navigateToHomeScreen());

        header.getChildren().addAll(titleLabel, spacer, backButton);

        return header;
    }


     // Create the footer for the category screen.
    private HBox createFooter() {
        HBox footer = new HBox();
        footer.getStyleClass().add("footer");
        footer.setAlignment(Pos.CENTER);

        Text footerText = new Text("© 2025 App Ea Ho Ithuta Sesotho- Ka Ntate Pitso");
        footerText.getStyleClass().add("footer-text");

        footer.getChildren().add(footerText);

        return footer;
    }


     //Navigate to the difficulty selection screen for a category.
    private void navigateToDifficultyScreen(Category category) {
        try {
            // Create the difficulty screen for the selected category
            DifficultyScreen difficultyScreen = new DifficultyScreen(category);

            // Replace the scene
            Scene currentScene = getScene();
            Stage stage = (Stage) currentScene.getWindow();
            Scene newScene = new Scene(difficultyScreen, currentScene.getWidth(), currentScene.getHeight());
            newScene.getStylesheets().addAll(currentScene.getStylesheets());

            stage.setScene(newScene);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

     //Navigate back to the home screen.
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