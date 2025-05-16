package com.sesothoapp.model;

/*
 * Represents an answer option for a question in the Sesotho Learning App.
 */
public class Answer {
    private final String id;
    private final String text;
    private final boolean correct;

     //Constructs a new Answer.
    public Answer(String id, String text, boolean correct) {
        this.id = id;
        this.text = text;
        this.correct = correct;
    }

     //Get the answer ID.
    public String getId() {
        return id;
    }
     // Get the answer text.
    public String getText() {
        return text;
    }
      //Check if this answer is correct.
    public boolean isCorrect() {
        return correct;
    }
}