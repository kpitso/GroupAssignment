package com.sesothoapp.model;

import java.util.HashMap;
import java.util.Map;

/*
  Represents a user in the Sesotho Learning App.
 */
public class User {
    private String username;
    private final Map<String, Map<Difficulty, Boolean>> unlockedDifficulties;
    private final Map<String, Map<Difficulty, Integer>> categoryScores;

     // Constructs a new User with default settings.
    public User() {
        this.username = "Player";
        this.unlockedDifficulties = new HashMap<>();
        this.categoryScores = new HashMap<>();

        // Initialize with only EASY difficulty unlocked for all categories
        unlockDifficulty("lilotho", Difficulty.EASY);
        unlockDifficulty("lipapali", Difficulty.EASY);
        unlockDifficulty("maele", Difficulty.EASY);
        unlockDifficulty("liboko", Difficulty.EASY);
        unlockDifficulty("lijo", Difficulty.EASY);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
     //Check if a difficulty level is unlocked for a category.
    public boolean isDifficultyUnlocked(String categoryId, Difficulty difficulty) {
        // Always unlock EASY difficulty
        if (difficulty == Difficulty.EASY) {
            return true;
        }

        // Check if the category exists in our map
        if (!unlockedDifficulties.containsKey(categoryId)) {
            return false;
        }

        // Check if the difficulty is unlocked for this category
        Map<Difficulty, Boolean> categoryDifficulties = unlockedDifficulties.get(categoryId);
        return categoryDifficulties.getOrDefault(difficulty, false);
    }

     // Unlock a difficulty level for a category.
    public void unlockDifficulty(String categoryId, Difficulty difficulty) {
        if (!unlockedDifficulties.containsKey(categoryId)) {
            unlockedDifficulties.put(categoryId, new HashMap<>());
        }
        Map<Difficulty, Boolean> categoryDifficulties = unlockedDifficulties.get(categoryId);
        categoryDifficulties.put(difficulty, true);
    }

     // Get the highest score for a category and difficulty.
    public int getHighestScore(String categoryId, Difficulty difficulty) {
        if (!categoryScores.containsKey(categoryId)) {
            return 0;
        }

        Map<Difficulty, Integer> difficultiesScore = categoryScores.get(categoryId);
        return difficultiesScore.getOrDefault(difficulty, 0);
    }

     // Complete a level and update user progress.
    public void completeLevel(String categoryId, Difficulty difficulty, int correctAnswers, int totalQuestions) {
        if (totalQuestions == 0) {
            return;
        }
        int scorePercentage = (int) ((double) correctAnswers / totalQuestions * 100);

        // Initialize category scores if needed
        if (!categoryScores.containsKey(categoryId)) {
            categoryScores.put(categoryId, new HashMap<>());
        }

        Map<Difficulty, Integer> difficultyScores = categoryScores.get(categoryId);

        // Update the score if it's higher than the previous one
        int currentHighScore = difficultyScores.getOrDefault(difficulty, 0);
        if (scorePercentage > currentHighScore) {
            difficultyScores.put(difficulty, scorePercentage);
        }

        // Unlock the next difficulty level if the score meets the threshold
        // 60% for Easy, 75% for Medium, 90% for Hard
        if (difficulty == Difficulty.EASY && scorePercentage >= 60) {
            unlockDifficulty(categoryId, Difficulty.MEDIUM);
            System.out.println("DEBUG: Unlocked MEDIUM difficulty for " + categoryId);
        } else if (difficulty == Difficulty.MEDIUM && scorePercentage >= 75) {
            unlockDifficulty(categoryId, Difficulty.HARD);
            System.out.println("DEBUG: Unlocked HARD difficulty for " + categoryId);
        }
    }

     // Get the threshold needed to pass a difficulty level
    public int getThresholdForDifficulty(Difficulty difficulty) {
        if (difficulty == Difficulty.EASY) {
            return 60;
        }
        else if (difficulty == Difficulty.MEDIUM) {
            return 75;
        }
        else if (difficulty == Difficulty.HARD) {
            return 90;
        }
        return 70;
    }
}