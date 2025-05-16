package com.sesothoapp.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 The main game model that holds the game state and manages game logic.
 */
public class Game {
    private static Game instance;

    // Game state
    private final User currentUser;
    private final QuestionBank questionBank;
    private final Map<String, Category> categories;

    // Current game session state
    private Category currentCategory;
    private Difficulty currentDifficulty;
    private List<Question> currentQuestions;
    private int currentQuestionIndex;
    private int correctAnswers;
    private boolean gameInProgress;

    // Private constructor for singleton pattern
    private Game() {
        this.currentUser = new User();
        this.questionBank = new QuestionBank();
        this.categories = new HashMap<>();
        initializeCategories();
        questionBank.loadQuestions();
    }

    // Get the singleton instance of the Game.
    public static synchronized Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    //Initialize the game categories.
    private void initializeCategories() {
        // Create the five main categories with custom icons
        addCategory(new Category("lilotho", "Lilotho", "category_lilotho",
                "Lipotso le lipapali tsa kelello tse tsoang setso sa Sesotho"));
        addCategory(new Category("lipapali", "Lipapali", "category_lipapali",
                "Lipapali tsa Basotho tse bapaloang ke bana le batho ba baholo"));
        addCategory(new Category("maele", "Maele", "category_maele",
                "Litaole le maele a jereng bohlale le meetlo ea sesotho"));
        addCategory(new Category("liboko", "Liboko", "category_liboko",
                "Lithoko tsa meloko le lefa la Basotho,li bontsoa ka liphoofolo tse hlaha"));
        addCategory(new Category("lijo", "Lijo", "category_lijo",
                "Lijo tsa setso le mekhoa ea ho pheha ea setso sa Basotho"));
    }

    // Add a category to the game.
    private void addCategory(Category category) {
        categories.put(category.getId(), category);
    }

    // Get all available categories.
    public List<Category> getAllCategories() {
        return new ArrayList<>(categories.values());
    }

    //Get a specific category by ID.
    public Category getCategoryById(String categoryId) {
        return categories.get(categoryId);
    }

    //Get the current user.
    public User getCurrentUser() {
        return currentUser;
    }

    //Start a new game with the specified category and difficulty.
//Start a new game with the specified category and difficulty.
    public boolean startGame(Category category, Difficulty difficulty) {
        // Check if the difficulty is unlocked for this category
        if (!currentUser.isDifficultyUnlocked(category.getId(), difficulty)) {
            return false;
        }

        // Get questions for this category and difficulty
        List<Question> questions = questionBank.getQuestions(category.getId(), difficulty);
        if (questions.isEmpty()) {
            return false;
        }

        // Reset game state
        currentQuestions = new ArrayList<>();
        currentQuestionIndex = 0;
        correctAnswers = 0;
        gameInProgress = false;

        // Shuffle and prepare new questions
        Collections.shuffle(questions);
        int questionCount = Math.min(5, questions.size());
        currentQuestions = new ArrayList<>(questions.subList(0, questionCount));

        for (Question question : currentQuestions) {
            question.setAnswered(false); // Reset answered flag
            question.shuffleAnswers();
        }

        // Set up game state
        currentCategory = category;
        currentDifficulty = difficulty;
        gameInProgress = true;

        return true;
    }

    // Check if there's currently a game in progress.
    public boolean isGameInProgress() {
        return gameInProgress;
    }

    //Get the current category being played.
    public Category getCurrentCategory() {
        return currentCategory;
    }

    //Get the current difficulty level being played.
    public Difficulty getCurrentDifficulty() {
        return currentDifficulty;
    }

    //Get the current question.
    public Question getCurrentQuestion() {
        if (!gameInProgress || currentQuestionIndex >= currentQuestions.size()) {
            return null;
        }
        return currentQuestions.get(currentQuestionIndex);
    }

    // Get the total number of questions in the current game.
    public int getTotalQuestions() {
        if (!gameInProgress) {
            return 0;
        }
        return currentQuestions.size();
    }

    //Get the current question number.
    public int getCurrentQuestionNumber() {
        if (!gameInProgress) {
            return 0;
        }
        return currentQuestionIndex + 1;
    }

    // Check if there are more questions after the current one.
    public boolean hasNextQuestion() {
        return gameInProgress && currentQuestionIndex < currentQuestions.size() - 1;
    }

    // Move to the next question.
    public boolean nextQuestion() {
        if (!hasNextQuestion()) {
            return false;
        }
        currentQuestionIndex++;
        return true;
    }

    //Answer the current question.
    public boolean answerQuestion(Answer answer) {
        if (!gameInProgress) {
            System.out.println("ERROR: Tried to answer question but no game in progress");
            return false;
        }
        Question currentQuestion = getCurrentQuestion();
        if (currentQuestion == null) {
            System.out.println("ERROR: Current question is null");
            return false;
        }

        // Check if the question has already been answered
        if (currentQuestion.isAnswered()) {
            System.out.println("WARNING: Question already answered, not counting again");
            return answer.isCorrect();
        }

        boolean isCorrect = answer.isCorrect();
        if (isCorrect) {
            correctAnswers++;
            System.out.println("DEBUG: Correct answer! Score increased to " + correctAnswers +
                    " out of " + currentQuestions.size());
        } else {
            System.out.println("DEBUG: Incorrect answer. Score remains " + correctAnswers +
                    " out of " + currentQuestions.size());
        }

        currentQuestion.setAnswered(true);

        return isCorrect;
    }

    //End the current game and update user progress.
    public void endGame() {
        if (!gameInProgress) {
            return;
        }

        // Update user progress with the results
        currentUser.completeLevel(
                currentCategory.getId(),
                currentDifficulty,
                correctAnswers,
                currentQuestions.size()
        );

        // Print completion stats for debugging
        int scorePercentage = getScorePercentage();
        System.out.println("DEBUG: Game completed. Category: " + currentCategory.getName() +
                ", Difficulty: " + currentDifficulty.getDisplayName() +
                ", Score: " + scorePercentage + "%");

        // Reset game state
        gameInProgress = false;
    }

    // Get the number of correct answers in the current game.
    public int getCorrectAnswers() {
        return correctAnswers;
    }

    //Get the score as a percentage.
    public int getScorePercentage() {
        if (currentQuestions == null || currentQuestions.isEmpty()) {
            System.out.println("DEBUG: Cannot calculate percentage - no questions available");
            return 0;
        }

        int totalQuestions = currentQuestions.size();
        System.out.println("DEBUG: Calculating score percentage - " + correctAnswers + " correct out of " + totalQuestions + " total questions");

        double percentage = (double) correctAnswers / totalQuestions * 100;
        int roundedPercentage = (int) Math.round(percentage);

        System.out.println("DEBUG: Score percentage calculated: " + roundedPercentage + "%");
        return roundedPercentage;
    }

    // Check if the player has passed the current level based on the threshold for their difficulty.
    public boolean hasPassed() {
        int threshold;

        if (currentDifficulty == Difficulty.EASY) {
            threshold = 60;  // 60% for Easy
        } else if (currentDifficulty == Difficulty.MEDIUM) {
            threshold = 75;  // 75% for Medium
        } else {
            threshold = 90;  // 90% for Hard
        }

        return getScorePercentage() >= threshold;
    }
}