package com.sesothoapp.util;

import javafx.animation.*;
import javafx.collections.ObservableList;
import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

 // Utility class for animations in the application.
public class AnimationUtil {

     //Duration for fade animations
    private static final Duration FADE_DURATION = Duration.millis(300);

    //Duration for rotation animations
    private static final Duration ROTATION_DURATION = Duration.millis(1000);

     //Duration for scale animations
    private static final Duration SCALE_DURATION = Duration.millis(400);
    //Duration for translate animations
    private static final Duration TRANSLATE_DURATION = Duration.millis(500);

    //Private constructor to prevent instantiation
    private AnimationUtil() {
        // Utility class should not be instantiated
    }

    //Create a fade-in animation for a node.
    public static FadeTransition createFadeInTransition(Node node) {
        FadeTransition fadeTransition = new FadeTransition(FADE_DURATION, node);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        return fadeTransition;
    }

     // Create a fade-out animation for a node.
    public static FadeTransition createFadeOutTransition(Node node) {
        FadeTransition fadeTransition = new FadeTransition(FADE_DURATION, node);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        return fadeTransition;
    }

     // Create a rotation animation for a node.
    public static RotateTransition createRotationTransition(Node node, double angle) {
        RotateTransition rotateTransition = new RotateTransition(ROTATION_DURATION, node);
        rotateTransition.setFromAngle(0);
        rotateTransition.setToAngle(angle);
        rotateTransition.setCycleCount(1);
        rotateTransition.setAutoReverse(false);
        return rotateTransition;
    }

    //Create a 3D rotation animation for a node.
    public static RotateTransition create3DRotationTransition(Node node, double angle, Point3D axis) {
        RotateTransition rotateTransition = new RotateTransition(ROTATION_DURATION, node);
        rotateTransition.setAxis(axis);
        rotateTransition.setFromAngle(0);
        rotateTransition.setToAngle(angle);
        rotateTransition.setCycleCount(1);
        rotateTransition.setAutoReverse(false);
        return rotateTransition;
    }

    //Create a scale transition for a node.
    public static ScaleTransition createScaleTransition(Node node, double fromScale, double toScale) {
        ScaleTransition scaleTransition = new ScaleTransition(SCALE_DURATION, node);
        scaleTransition.setFromX(fromScale);
        scaleTransition.setFromY(fromScale);
        scaleTransition.setToX(toScale);
        scaleTransition.setToY(toScale);
        return scaleTransition;
    }
     //Create a translation (movement) transition for a node.

    public static TranslateTransition createTranslateTransition(Node node, double byX, double byY) {
        TranslateTransition translateTransition = new TranslateTransition(TRANSLATE_DURATION, node);
        translateTransition.setByX(byX);
        translateTransition.setByY(byY);
        return translateTransition;
    }

     //Create a sequence of animations to play in order.
    public static SequentialTransition createSequentialTransition(Animation... animations) {
        return new SequentialTransition(animations);
    }

     //Create a parallel animation (animations play at the same time).
    public static ParallelTransition createParallelTransition(Animation... animations) {
        return new ParallelTransition(animations);

    }
     // Create a pulsating effect for a node.
    public static SequentialTransition createPulseAnimation(Node node) {
        ScaleTransition scaleUp = createScaleTransition(node, 1.0, 1.1);
        ScaleTransition scaleDown = createScaleTransition(node, 1.1, 1.0);

        SequentialTransition pulse = new SequentialTransition(scaleUp, scaleDown);
        pulse.setCycleCount(2);

        return pulse;
    }

     // Create an entrance animation for a node.
    public static ParallelTransition createEntranceAnimation(Node node) {
        // Save original opacity and scale
        double originalOpacity = node.getOpacity();

        // Set initial state
        node.setOpacity(0.0);
        node.setScaleX(0.8);
        node.setScaleY(0.8);

        FadeTransition fadeIn = createFadeInTransition(node);
        fadeIn.setToValue(originalOpacity);

        ScaleTransition scaleIn = createScaleTransition(node, 0.8, 1.0);

        return new ParallelTransition(fadeIn, scaleIn);
    }

     // Create a rotation animation that spins a full 360 degrees and returns to original position.
    public static SequentialTransition createFullRotation(Node node) {
        RotateTransition rotateForward = createRotationTransition(node, 360);

        return new SequentialTransition(rotateForward);
    }

     //Replace one pane with another, using a fade animation.
    public static void replacePane(Pane container, Node currentPane, Node newPane) {
        ObservableList<Node> children = container.getChildren();

        newPane.setOpacity(0.0);

        // Add the new pane if it's not already there
        if (!children.contains(newPane)) {
            children.add(newPane);
        }

        FadeTransition fadeOut = createFadeOutTransition(currentPane);
        fadeOut.setOnFinished(event -> {
            // Remove the current pane once it's faded out
            children.remove(currentPane);

            // Create and play fade-in animation for the new pane
            FadeTransition fadeIn = createFadeInTransition(newPane);
            fadeIn.play();
        });

        // Start the transition
        fadeOut.play();
    }
}