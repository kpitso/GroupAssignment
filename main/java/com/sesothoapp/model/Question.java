package com.sesothoapp.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/*
    Represents a question in the Sesotho Learning App.
 */
public class Question {
    private final String id;
    private final String text;
    private final String imageName;
    private final String videoName;
    private final Category category;
    private final Difficulty difficulty;
    private final List<Answer> answers;
    private boolean answered = false;

    /*
     Constructs a new Question.
     */
    public Question(String id, String text, String imageName, String videoName, Category category, Difficulty difficulty) {
        this.id = id;
        this.text = text;
        this.imageName = imageName;
        this.videoName = videoName;
        this.category = category;
        this.difficulty = difficulty;
        this.answers = new ArrayList<>();
    }
     // Add an answer to this question.
    public void addAnswer(Answer answer) {
        answers.add(answer);
    }
     // Get the question ID.
    public String getId() {
        return id;
    }
     //Get the question text.
    public String getText() {
        return text;
    }
     //Get the image name.
    public String getImageName() {
        return imageName;
    }

    // Get the video name.
    public String getVideoName() {
        return videoName;
    }

     //Get the category.
    public Category getCategory() {
        return category;
    }


     //Get the difficulty level.
    public Difficulty getDifficulty() {
        return difficulty;
    }
     //Get all answers for this question.
    public List<Answer> getAnswers() {
        return new ArrayList<>(answers);
    }

     //Shuffle the order of answers.
    public void shuffleAnswers() {
        Collections.shuffle(answers);
    }
     //Get the correct answer for this question.

    public Answer getCorrectAnswer() {
        for (Answer answer : answers) {
            if (answer.isCorrect()) {
                return answer;
            }
        }
        return null;
    }
     //Check if the question has been answered.
    public boolean isAnswered() {
        return answered;
    }
    // Set the answered state of the question.
    public void setAnswered(boolean answered) {
        this.answered = answered;
    }
}