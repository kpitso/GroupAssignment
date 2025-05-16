package com.sesothoapp.util;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;

/*
 * Utility class for managing application styles and themes.
 */
public class StyleManager {

    // Path to the main CSS file
    private static final String MAIN_CSS_PATH = "/css/style.css";

    // The current application scene
    private static Scene currentScene;

      //Initialize the style manager with the main application scene.
    public static void initialize(Scene scene) {
        currentScene = scene;
        applyMainStylesheet();
    }


     // Apply the main stylesheet to the current scene.
    private static void applyMainStylesheet() {
        if (currentScene != null) {
            // Add the main stylesheet if it's not already added
            if (!currentScene.getStylesheets().contains(MAIN_CSS_PATH)) {
                currentScene.getStylesheets().add(MAIN_CSS_PATH);
            }
        }
    }

     //Apply the main stylesheet to a dialog.
    public static void applyStyleToDialog(Alert dialog) {
        DialogPane dialogPane = dialog.getDialogPane();

        // Add the main stylesheet to the dialog
        dialogPane.getStylesheets().add(MAIN_CSS_PATH);

        // Add specific style class for dialogs
        dialogPane.getStyleClass().add("styled-dialog");

        // Get the dialog's stage and set its modality
        Stage stage = (Stage) dialogPane.getScene().getWindow();
        if (stage != null) {
            stage.setResizable(false);
        }
    }

     // Apply a specific theme to the application.
    public static void applyTheme(String themeName) {
        if (currentScene == null) return;

        // Remove any existing theme classes
        currentScene.getRoot().getStyleClass().removeAll(
                "theme-default", "theme-dark", "theme-high-contrast");

        // Add the new theme class
        switch (themeName.toLowerCase()) {
            case "dark":
                currentScene.getRoot().getStyleClass().add("theme-dark");
                break;
            case "high-contrast":
                currentScene.getRoot().getStyleClass().add("theme-high-contrast");
                break;
            case "default":
            default:
                currentScene.getRoot().getStyleClass().add("theme-default");
                break;
        }
    }

     //Create a custom Alert dialog with the application's styling.
    public static Alert createStyledAlert(Alert.AlertType alertType, String title,
                                          String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        // Apply styling
        applyStyleToDialog(alert);

        return alert;
    }

     //Create a styled information dialog.
    public static Alert createInfoDialog(String title, String message) {
        return createStyledAlert(Alert.AlertType.INFORMATION, title, null, message);
    }

     //Create a styled error dialog.
    public static Alert createErrorDialog(String title, String message) {
        return createStyledAlert(Alert.AlertType.ERROR, title, null, message);
    }

     //Create a styled confirmation dialog.
    public static Alert createConfirmDialog(String title, String message) {
        return createStyledAlert(Alert.AlertType.CONFIRMATION, title, null, message);
    }
}
