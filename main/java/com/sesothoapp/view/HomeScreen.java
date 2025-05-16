package com.sesothoapp.view;

import com.sesothoapp.controller.GameController;
import com.sesothoapp.util.AnimationUtil;
import javafx.animation.*;
import javafx.geometry.Insets;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.InputStream;

/*
 The home screen for the Sesotho Learning App.
 */
public class HomeScreen extends BorderPane {

    private final GameController gameController;
    private VBox mainContent;

    public HomeScreen() {
        this.gameController = new GameController();
        setupLayout();
    }

    private void setupLayout() {
        // Header
        setTop(createHeader());

        // Main Content
        mainContent = new VBox(20);
        mainContent.getStyleClass().add("main-content");
        mainContent.setPadding(new Insets(20));
        mainContent.getChildren().addAll(
                createWelcomeBanner(),
                createMenuOptions()
        );

        // Wrap main content in ScrollPane
        ScrollPane scrollPane = new ScrollPane(mainContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        setCenter(scrollPane);

        // Footer
        HBox footer = createFooter();
        footer.setMinHeight(50);
        setBottom(footer);
    }

    private VBox createWelcomeBanner() {
        VBox welcomeBanner = new VBox(20);
        welcomeBanner.getStyleClass().add("welcome-banner");
        welcomeBanner.setPadding(new Insets(30));
        welcomeBanner.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("U Amoheleile...");
        titleLabel.getStyleClass().add("welcome-title");

        Label messageLabel = new Label("Ithute Sesotho! Ithute puo le setso sa Sesotho ka lipapali tse monate tse sebelisanang.");
        messageLabel.getStyleClass().add("welcome-message");
        messageLabel.setWrapText(true);

        GridPane imageGrid = new GridPane();
        imageGrid.setHgap(15);
        imageGrid.setVgap(15);
        imageGrid.setAlignment(Pos.CENTER);
        imageGrid.setPadding(new Insets(20, 0, 10, 0));

        try {
            // Image 1
            ImageView cultureImage = loadSVGImage("/images/sesotho_culture.png", 180, 135);
            StackPane image1Pane = createImageContainer(cultureImage);

            // Image 2
            ImageView tradImage = loadSVGImage("/images/sesotho_traditional.png", 180, 135);
            StackPane image2Pane = createImageContainer(tradImage);

            // Image 3
            ImageView learnImage = loadSVGImage("/images/sesotho_learning.png", 180, 135);
            StackPane image3Pane = createImageContainer(learnImage);

            // Image 4 - using a category image for the fourth spot
            ImageView fourthImage = loadSVGImage("/images/category_lijo.png", 180, 135);
            StackPane image4Pane = createImageContainer(fourthImage);

            // Add images to grid in a 2x2 layout
            imageGrid.add(image1Pane, 0, 0);
            imageGrid.add(image2Pane, 1, 0);
            imageGrid.add(image3Pane, 0, 1);
            imageGrid.add(image4Pane, 1, 1);

            // Create animation sequence for all four images
            createHomeScreenAnimation(imageGrid, image1Pane, image2Pane, image3Pane, image4Pane);

        } catch (Exception e) {
            System.err.println("Error loading images: " + e.getMessage());
            Label errorLabel = new Label("Cultural images not available");
            errorLabel.getStyleClass().add("image-error");
            imageGrid.add(errorLabel, 0, 0, 2, 2);
        }

        welcomeBanner.getChildren().addAll(titleLabel, messageLabel, imageGrid);
        return welcomeBanner;
    }

    private ImageView loadSVGImage(String path, double width, double height) {
        try (InputStream stream = getClass().getResourceAsStream(path)) {
            if (stream == null) {
                throw new RuntimeException("Image not found: " + path);
            }
            Image image = new Image(stream);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(width);
            imageView.setFitHeight(height);
            imageView.setPreserveRatio(true);
            return imageView;
        } catch (Exception e) {
            System.err.println("Failed to load image: " + path);
            return createPlaceholderImage(width, height);
        }
    }

    private ImageView createPlaceholderImage(double width, double height) {
        ImageView placeholder = new ImageView();
        placeholder.setFitWidth(width);
        placeholder.setFitHeight(height);
        placeholder.setStyle("-fx-background-color: #e0e0e0;");
        return placeholder;
    }

    private StackPane createImageContainer(ImageView imageView) {
        StackPane pane = new StackPane(imageView);
        pane.getStyleClass().add("image-container");
        pane.setMinSize(180, 135);
        pane.setMaxSize(180, 135);
        return pane;
    }

    private void createHomeScreenAnimation(GridPane grid, StackPane image1, StackPane image2, StackPane image3, StackPane image4) {
        // Initially set images to be transparent
        image1.setOpacity(0);
        image2.setOpacity(0);
        image3.setOpacity(0);
        image4.setOpacity(0);

        // Create fade-in animations with different timings for each image
        FadeTransition fadeIn1 = new FadeTransition(Duration.seconds(1.2), image1);
        fadeIn1.setFromValue(0);
        fadeIn1.setToValue(1.0);
        fadeIn1.setDelay(Duration.millis(300));

        FadeTransition fadeIn2 = new FadeTransition(Duration.seconds(1.2), image2);
        fadeIn2.setFromValue(0);
        fadeIn2.setToValue(1.0);
        fadeIn2.setDelay(Duration.millis(600));

        FadeTransition fadeIn3 = new FadeTransition(Duration.seconds(1.2), image3);
        fadeIn3.setFromValue(0);
        fadeIn3.setToValue(1.0);
        fadeIn3.setDelay(Duration.millis(900));

        FadeTransition fadeIn4 = new FadeTransition(Duration.seconds(1.2), image4);
        fadeIn4.setFromValue(0);
        fadeIn4.setToValue(1.0);
        fadeIn4.setDelay(Duration.millis(1200));

        // Play the animations
        fadeIn1.play();
        fadeIn2.play();
        fadeIn3.play();
        fadeIn4.play();

        // Add subtle hover effects to each image
        setupHoverEffects(image1);
        setupHoverEffects(image2);
        setupHoverEffects(image3);
        setupHoverEffects(image4);
    }

     // Sets up hover effects for an image container
    private void setupHoverEffects(StackPane imagePane) {
        // Create hover animations - subtle glow and scale effects
        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(250), imagePane);
        scaleUp.setToX(1.05);
        scaleUp.setToY(1.05);

        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(250), imagePane);
        scaleDown.setToX(1.0);
        scaleDown.setToY(1.0);

        // Apply hover effects
        imagePane.setOnMouseEntered(e -> {
            scaleUp.play();
            imagePane.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 0);");
        });

        imagePane.setOnMouseExited(e -> {
            scaleDown.play();
            imagePane.setStyle("-fx-effect: null;");
        });
    }
    private VBox createMenuOptions() {
        VBox menuContainer = new VBox(20);
        menuContainer.setAlignment(Pos.CENTER);

        Label menuTitle = new Label("Lenane le Leholo");
        menuTitle.setFont(Font.font("System", FontWeight.BOLD, 22));

        HBox menuOptions = new HBox(20);
        menuOptions.setAlignment(Pos.CENTER);

        // Play Game option
        VBox playGameOption = createMenuOption("🎮", "Bapala Papali", this::navigateToCategoryScreen);
        HBox.setHgrow(playGameOption, Priority.ALWAYS);

        // Progress option
        VBox progressOption = createMenuOption("📊", "Sheba Tsoelopele", this::navigateToProgressScreen);
        HBox.setHgrow(progressOption, Priority.ALWAYS);

        menuOptions.getChildren().addAll(playGameOption, progressOption);
        menuContainer.getChildren().addAll(menuTitle, menuOptions);

        return menuContainer;
    }

    private VBox createMenuOption(String icon, String text, Runnable action) {
        VBox option = new VBox(10);
        option.getStyleClass().add("menu-option");

        // Create the animated icon
        Label iconLabel = new Label(icon);
        iconLabel.setFont(Font.font("Tahoma", 48));

        // Text label
        Label textLabel = new Label(text);
        textLabel.getStyleClass().add("menu-option-text");

        // Add components to container
        option.getChildren().addAll(iconLabel, textLabel);
        option.setAlignment(Pos.CENTER);

        // Add initial animation when the screen loads
        SequentialTransition rotateOnLoad = AnimationUtil.createFullRotation(iconLabel);
        rotateOnLoad.play();

        // Add click animations and action
        option.setOnMouseClicked(e -> {
            // Execute the action directly without animation to avoid NullPointerException
            // This ensures that navigation happens immediately on click
            action.run();
        });

        // Add hover animations
        option.setOnMouseEntered(e -> {
            ScaleTransition scaleUp = AnimationUtil.createScaleTransition(option, 1.0, 1.05);
            scaleUp.play();
        });

        option.setOnMouseExited(e -> {
            ScaleTransition scaleDown = AnimationUtil.createScaleTransition(option, 1.05, 1.0);
            scaleDown.play();
        });

        return option;
    }

    private HBox createHeader() {
        HBox header = new HBox(15);
        header.getStyleClass().add("header");
        header.setPadding(new Insets(10, 20, 10, 20));
        header.setAlignment(Pos.CENTER_LEFT);

        // Always add the title label
        Label titleLabel = new Label("App Ea Ho Ithuta Sesotho");
        titleLabel.getStyleClass().add("header-title");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        try {
            // Try to add logo image first
            ImageView logoImage = loadSVGImage("/images/app_logo.png", 130, 100);
            header.getChildren().add(logoImage);
            // Add some space between logo and title
            header.getChildren().add(new HBox(10));
            // Add the title
            header.getChildren().add(titleLabel);
        } catch (Exception e) {
            System.err.println("Logo image not found, using flag instead");
            // If app logo not found, use the flag as fallback
            try {
                ImageView flagImage = loadSVGImage("/images/lesotho_flag.png", 50, 40);
                header.getChildren().addAll(flagImage, titleLabel);
            } catch (Exception ex) {
                // If all images fail, just show the title
                header.getChildren().add(titleLabel);
            }
        }

        HBox spacer = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // Add flag to the right side of the header
        try {
            ImageView flagImage = loadSVGImage("/images/lesotho_flag.png", 50, 40);
            header.getChildren().addAll(spacer, flagImage);
        } catch (Exception e) {
            header.getChildren().add(spacer);
        }

        return header;
    }

    private HBox createFooter() {
        HBox footer = new HBox();
        footer.getStyleClass().add("footer");
        footer.setAlignment(Pos.CENTER);
        footer.setPadding(new Insets(15));
        footer.setMinHeight(50);

        Text footerText = new Text("© 2025 App Ea Ho Ithuta Sesotho - Ka Ntate Pitso");
        footerText.getStyleClass().add("footer-text");

        footer.getChildren().add(footerText);
        return footer;
    }

    private void navigateToCategoryScreen() {
        try {
            CategoryScreen categoryScreen = new CategoryScreen();
            switchScene(categoryScreen);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void navigateToProgressScreen() {
        try {
            ProgressScreen progressScreen = new ProgressScreen();
            switchScene(progressScreen);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void switchScene(Region root) {
        try {
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

            // Create the new scene first, keeping it ready
            Scene newScene = new Scene(root, width, height);
            if (currentScene.getStylesheets() != null) {
                newScene.getStylesheets().addAll(currentScene.getStylesheets());
            }

            // Set initial opacity for smoother transition
            root.setOpacity(0);

            // Switch to the new scene immediately without white flash
            stage.setScene(newScene);

            // Apply subtle fade-in after scene is already switched
            FadeTransition fadeIn = new FadeTransition(Duration.millis(400), root);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();

        } catch (Exception e) {
            System.err.println("Error switching scenes: " + e.getMessage());
            e.printStackTrace();
        }
    }
}