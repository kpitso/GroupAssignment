package com.sesothoapp.model;

/*
 * Represents a difficulty level for questions in the Sesotho Learning App.
 */
public enum Difficulty {
    EASY("Easy", "Lipotso tse bonolo bakeng sa ba qalang"),
    MEDIUM("Medium", "Lipotso tse phephetsang bakeng sa ba ithutang mahareng"),
    HARD("Hard", "Lipotso tse thata bakeng sa ba ithutang boemong bo phahameng");

    private final String displayName;
    private final String description;

     // Constructs a new Difficulty.
    Difficulty(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

     //Get the display name.
    public String getDisplayName() {
        return displayName;
    }

     //Get the description.
    public String getDescription() {
        return description;
    }

     // Check if there is a next (harder) difficulty level.
    public boolean hasNextDifficulty() {
        return this != HARD;
    }

     // Get the next (harder) difficulty level
    public Difficulty getNextDifficulty() {
        return switch (this) {
            case EASY -> MEDIUM;
            case MEDIUM -> HARD;
            case HARD -> null;
        };
    }
}