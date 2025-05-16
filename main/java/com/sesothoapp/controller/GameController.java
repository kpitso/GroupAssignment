package com.sesothoapp.controller;

import com.sesothoapp.model.Answer;
import com.sesothoapp.model.Category;
import com.sesothoapp.model.Difficulty;
import com.sesothoapp.model.Game;
import com.sesothoapp.model.Question;
import com.sesothoapp.model.User;

import java.util.List;

/*
 Controller that mediates between the UI and the game model.
 */
public class GameController {
    private final Game game;

     // Constructs a new GameController.
    public GameController() {
        this.game = Game.getInstance();
    }

    public List<Category> getAllCategories() {
        return game.getAllCategories();
    }


    public Category getCategoryById(String categoryId) {
        return game.getCategoryById(categoryId);
    }

     //Check if a difficulty level is unlocked for a category.
    public boolean isDifficultyUnlocked(Category category, Difficulty difficulty) {
        return game.getCurrentUser().isDifficultyUnlocked(category.getId(), difficulty);
    }


     //Get the current user.
    public User getCurrentUser() {
        return game.getCurrentUser();
    }


     //Start a new game with the specified category and difficulty.
    public boolean startGame(Category category, Difficulty difficulty) {
        return game.startGame(category, difficulty);
    }


     //Get the current question.
    public Question getCurrentQuestion() {
        return game.getCurrentQuestion();
    }

      //Get the current question number (1-based).

    public int getCurrentQuestionNumber() {
        return game.getCurrentQuestionNumber();
    }

     //Get the total number of questions in the current game.
    public int getTotalQuestions() {
        return game.getTotalQuestions();
    }

     //Submit an answer to the current question.
    public boolean submitAnswer(Answer answer) {
        return game.answerQuestion(answer);
    }


     //Check if there are more questions after the current one.
    public boolean hasNextQuestion() {
        return game.hasNextQuestion();
    }


     // Move to the next question.

    public boolean nextQuestion() {
        return game.nextQuestion();
    }

    /*
     End the current game and update user progress.
     */
    public void endGame() {
        game.endGame();
    }


     // Get the number of correct answers in the current game.
    public int getCorrectAnswers() {
        return game.getCorrectAnswers();
    }

     // Get the score as a percentage.
    public int getScorePercentage() {
        return game.getScorePercentage();
    }

     // Check if the player has passed the current level
    public boolean hasPassed() {
        return game.hasPassed();
    }

     // Get the current category being played.
    public Category getCurrentCategory() {
        return game.getCurrentCategory();
    }

     // Get the current difficulty level being played.
    public Difficulty getCurrentDifficulty() {
        return game.getCurrentDifficulty();
    }
}