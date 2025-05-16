package com.sesothoapp.model;

import java.util.*;

/**
 * Manages the collection of questions available in the game.
 * All questions are created directly in code instead of loading from external files.
 */
public class QuestionBank {
    private final Map<String, Map<Difficulty, List<Question>>> questions;

    public QuestionBank() {
        this.questions = new HashMap<>();
    }
    /*
      Initialize all questions for the game
     */
    public void loadQuestions() {
        // Get categories (creating them directly here)
        Category lilotho = new Category("lilotho", "Lilotho", "category_lilotho",
                "Lipotso le lipapali tsa kelello tse tsoang setso sa Sesotho");
        Category lipapali = new Category("lipapali", "Lipapali", "category_lipapali",
                "Lipapali tsa Basotho tse bapaloang ke bana le batho ba baholo");
        Category maele = new Category("maele", "Maele", "category_maele",
                "Litaole le maele a jereng bohlale le meetlo ea setso");
        Category liboko = new Category("liboko", "Liboko", "category_liboko",
                "Lithoko tsa meloko le lefa la Basotho, tsona li bontsoa ka liphoofolo tse hlaha");
        Category lijo = new Category("lijo", "Lijo", "category_lijo",
                "Lijo tsa setso le mekhoa ea ho pheha ea setso sa Basotho");

        // Create and add sample questions for each category and difficulty
        addLilothoQuestions(lilotho);
        addLipapaliQuestions(lipapali);
        addMaeleQuestions(maele);
        addLibokoQuestions(liboko);
        addLijoQuestions(lijo);
    }

    // Add sample questions for the Lilotho category.
    private void addLilothoQuestions(Category category) {
        // EASY questions
        List<Question> easyQuestions = new ArrayList<>();

        // Question 1
        Question q1 = new Question(
                "lilotho_easy_1",
                "Ka u lotha ka: Monna ea sekhoalita hloohong?",
                "man.png",
                null,
                category,
                Difficulty.EASY
        );
        q1.addAnswer(new Answer("a1", "Noka ", false));
        q1.addAnswer(new Answer("a2", "Lesokoana ", true));
        q1.addAnswer(new Answer("a3", "Letsatsi ", false));
        q1.addAnswer(new Answer("a4", "Koloi ", false));
        easyQuestions.add(q1);

        // Question 2
        Question q2 = new Question(
                "lilotho_easy_2",
                " Monna eo e reng ha a khotše a roalle. Ke eng?",
                "man1.png",
                null,
                category,
                Difficulty.EASY
        );
        q2.addAnswer(new Answer("a1", "Setopo", false));
        q2.addAnswer(new Answer("a2", "Noka", true));
        q2.addAnswer(new Answer("a3", "Naleli", false));
        q2.addAnswer(new Answer("a4", "Buka", false));
        easyQuestions.add(q2);

        // Question 3
        Question q3 = new Question(
                "lilotho_easy_3",
                "Khomo ea bohali ba 'mao e selota mpeng?",
                "cow.jpeg",
                null,
                category,
                Difficulty.EASY
        );
        q3.addAnswer(new Answer("a1", "leloala le tšiloana", true));
        q3.addAnswer(new Answer("a2", "Ntja", false));
        q3.addAnswer(new Answer("a3", "Khoeli", false));
        q3.addAnswer(new Answer("a4", "Tau", false));
        easyQuestions.add(q3);

        // Question 4
        Question q4 = new Question(
                "lilotho_easy_4",
                "Lipoli tsa makeleketla li fula li bothile?",
                "poli.png",
                null,
                category,
                Difficulty.EASY
        );
        q4.addAnswer(new Answer("a1", "Sekele", true));
        q4.addAnswer(new Answer("a2", "Lerato", false));
        q4.addAnswer(new Answer("a3", "Pina", false));
        q4.addAnswer(new Answer("a4", "Thipa", false));
        easyQuestions.add(q4);

        // Question 5
        Question q5 = new Question(
                "lilotho_easy_5",
                "E phela metsing empa e shoa ha e koloba?",
                "water.png",
                null,
                category,
                Difficulty.EASY
        );
        q5.addAnswer(new Answer("a1", "Tlhapi", false));
        q5.addAnswer(new Answer("a2", "Letswai", true));
        q5.addAnswer(new Answer("a3", "Qoqoilane", false));
        q5.addAnswer(new Answer("a4", "Noha", false));
        easyQuestions.add(q5);

        // MEDIUM questions
        List<Question> mediumQuestions = new ArrayList<>();

        // Question 1
        Question m1 = new Question(
                "lilotho_medium_1",
                "Ntate o na le bana ba leshome, empa ha ho le a mong ea ts'oanang le e mong. Bana bao ke bo mang?",
                "father.png",
                null,
                category,
                Difficulty.MEDIUM
        );
        m1.addAnswer(new Answer("a1", "Likhoeli", false));
        m1.addAnswer(new Answer("a2", "Menoana", true));
        m1.addAnswer(new Answer("a3", "Linomoro", false));
        m1.addAnswer(new Answer("a4", "Linaleli", false));
        mediumQuestions.add(m1);

        // Question 2
        Question m2 = new Question(
                "lilotho_medium_2",
                " Thankha-thankha ke tla tsoalla kae?",
                "preg.jpg",
                null,
                category,
                Difficulty.MEDIUM
        );
        m2.addAnswer(new Answer("a1", "Noka ", false));
        m2.addAnswer(new Answer("a2", "Mokopu", true));
        m2.addAnswer(new Answer("a3", "Pula", false));
        m2.addAnswer(new Answer("a4", "Pompo", false));
        mediumQuestions.add(m2);

        Question m3 = new Question(
                "lilotho_medium_3",
                " 'Mme o sekoti, ntate o khopo, bana beso ba bararo",
                "family.jpg",
                null,
                category,
                Difficulty.MEDIUM
        );
        m3.addAnswer(new Answer("a1", "Pitsa", true));
        m3.addAnswer(new Answer("a2", "Poto", false));
        m3.addAnswer(new Answer("a3", "Sebaresela", false));
        m3.addAnswer(new Answer("a4", "Khampoto", false));
        mediumQuestions.add(m3);

        Question m4 = new Question(
                "lilotho_medium_4",
                " Maqeku a qabane ka lehaheng",
                "papa.jpg",
                null,
                category,
                Difficulty.MEDIUM
        );
        m4.addAnswer(new Answer("a1", "Poone", false));
        m4.addAnswer(new Answer("a2", "Lehoetla", false));
        m4.addAnswer(new Answer("a3", "Mabele", false));
        m4.addAnswer(new Answer("a4", "Likhobe", true));
        mediumQuestions.add(m4);

        Question m5 = new Question(
                "lilotho_medium_5",
                " O monate fela oa hlaba",
                "fruit.jpg",
                null,
                category,
                Difficulty.MEDIUM
        );
        m5.addAnswer(new Answer("a1", "Lekhala", false));
        m5.addAnswer(new Answer("a2", "Torofeee", true));
        m5.addAnswer(new Answer("a3", "Morobei", false));
        m5.addAnswer(new Answer("a4", "Lepopo", false));
        mediumQuestions.add(m5);

        // HARD questions
        List<Question> hardQuestions = new ArrayList<>();

        // Question 1
        Question h1 = new Question(
                "lilotho_hard_1",
                "Ke teng pele ho motho, ke be ke ba pele ho Molimo, empa ha ke eo leholimong kapa lefat'seng. Ke eng?",
                "letter.jpg",
                null,
                category,
                Difficulty.HARD
        );
        h1.addAnswer(new Answer("a1", "Lentsoe 'Le'", false));
        h1.addAnswer(new Answer("a2", "Tlhaku 'M'", true));
        h1.addAnswer(new Answer("a3", "Moea ", false));
        h1.addAnswer(new Answer("a4", "Lefu", false));
        hardQuestions.add(h1);

        Question h2 = new Question(
                "lilotho_hard_2",
                " Botala ke ba joang, bofubelu keba mali, monate ke oa tsoekere",
                "tomato.jpg",
                null,
                category,
                Difficulty.HARD
        );
        h2.addAnswer(new Answer("a1", "Tamati", false));
        h2.addAnswer(new Answer("a2", "Pekisi tsa mariha", false));
        h2.addAnswer(new Answer("a3", "Lehapu", true));
        h2.addAnswer(new Answer("a4", "Morara", false));
        hardQuestions.add(h2);

        Question h3 = new Question(
                "lilotho_hard_3",
                " Lithung-thung tsa tlapa le leholo",
                "sky.jpg",
                null,
                category,
                Difficulty.HARD
        );
        h3.addAnswer(new Answer("a1", "Lefat'se", false));
        h3.addAnswer(new Answer("a2", "Linaleli", true));
        h3.addAnswer(new Answer("a3", "mosali", false));
        h3.addAnswer(new Answer("a4", "lipalesa", false));
        hardQuestions.add(h3);

        Question h4 = new Question(
                "lilotho_hard_4",
                " Nthethe a bina moholo a lutse",
                "sing.jpg",
                null,
                category,
                Difficulty.HARD
        );
        h4.addAnswer(new Answer("a1", "Lets'oele", false));
        h4.addAnswer(new Answer("a2", "Sefate", true));
        h4.addAnswer(new Answer("a3", "Sehlopha tsa Banna", false));
        h4.addAnswer(new Answer("a4", "Moru", false));
        hardQuestions.add(h4);

        Question h5 = new Question(
                "lilotho_hard_5",
                " Lehalima leleri le pota motse",
                "animal.jpg",
                null,
                category,
                Difficulty.HARD
        );
        h5.addAnswer(new Answer("a1", "Nku", false));
        h5.addAnswer(new Answer("a2", "Ntja", false));
        h5.addAnswer(new Answer("a3", "Namane", true));
        h5.addAnswer(new Answer("a4", "Phoofolo", false));
        hardQuestions.add(h5);

        // Add the questions to the question bank
        addQuestionsForCategory(category.getId(), Difficulty.EASY, easyQuestions);
        addQuestionsForCategory(category.getId(), Difficulty.MEDIUM, mediumQuestions);
        addQuestionsForCategory(category.getId(), Difficulty.HARD, hardQuestions);
    }

    //Add sample questions for the Lipapali category.
    private void addLipapaliQuestions(Category category) {
        // EASY questions
        List<Question> easyQuestions = new ArrayList<>();

        // Question 1
        Question q1 = new Question(
                "lipapali_easy_1",
                "Ke efeng papali e neng e bapaloa ka ho khetha majoe?",
                "diketo.jpg",
                null,
                category,
                Difficulty.EASY
        );
        q1.addAnswer(new Answer("a1", "Morabaraba", false));
        q1.addAnswer(new Answer("a2", "Liketo", true));
        q1.addAnswer(new Answer("a3", "Khati", false));
        q1.addAnswer(new Answer("a4", "'Mantloane", false));
        easyQuestions.add(q1);

        // Question 2
        Question q2 = new Question(
                "lipapali_easy_2",
                "Ke papali efe moo bana ba tlolang khoele e entsoeng ka malapi?",
                "khati.jpeg",
                null,
                category,
                Difficulty.EASY
        );
        q2.addAnswer(new Answer("a1", "Morabaraba", false));
        q2.addAnswer(new Answer("a2", "Khati", true));
        q2.addAnswer(new Answer("a3", "Lengangajane", false));
        q2.addAnswer(new Answer("a4", "Mokallo", false));
        easyQuestions.add(q2);

        // Question 3
        Question q3 = new Question(
                "lipapali_easy_3",
                "Ke papali efe eo batho ba talimananang nako e telele?",
                "papa.jpg",
                null,
                category,
                Difficulty.EASY
        );
        q3.addAnswer(new Answer("a1", "Ho Pheha, Khobe", true));
        q3.addAnswer(new Answer("a2", "Khati", false));
        q3.addAnswer(new Answer("a3", "Lengangajane", false));
        q3.addAnswer(new Answer("a4", "Mokallo", false));
        easyQuestions.add(q3);

        // Question 4
        Question q4 = new Question(
                "lipapali_easy_4",
                "Mokou ke papali e bapaloang ke bo mang?",
                "mokou.jpg",
                null,
                category,
                Difficulty.EASY
        );
        q4.addAnswer(new Answer("a1", "Banna", false));
        q4.addAnswer(new Answer("a2", "Basali", false));
        q4.addAnswer(new Answer("a3", "Bashanyana", false));
        q4.addAnswer(new Answer("a4", "Banana le Basali", true));
        easyQuestions.add(q4);

        // Question 5
        Question q5 = new Question(
                "lipapali_easy_5",
                "Manketjoane ke papali e bapaloang nako e fe ea selemo?",
                "hide_and_seek.jpg",
                null,
                category,
                Difficulty.EASY
        );
        q5.addAnswer(new Answer("a1", "Mariha", false));
        q5.addAnswer(new Answer("a2", "Selemo", false));
        q5.addAnswer(new Answer("a3", "Hlabula", true));
        q5.addAnswer(new Answer("a4", "Hoetla", false));
        easyQuestions.add(q5);

        // MEDIUM questions
        List<Question> mediumQuestions = new ArrayList<>();
        // Question 1
        Question l1 = new Question(
                "lipapali_medium_1",
                "PAPALI E BAPALOANG KE BASHANYANA LE BANNA MME E BAPALLOA LETLAPENG LE SEPHARA HO SEBELISOA MAJOE AO BA BITSANG LIKHOMO?",
                "rock.jpg",
                null,
                category,
                Difficulty.MEDIUM);

        l1.addAnswer(new Answer("a1", "Chess", false));
        l1.addAnswer(new Answer("a2", "Morabaraba", true));
        l1.addAnswer(new Answer("a3", "Mohobelo", false));
        l1.addAnswer(new Answer("a4", "Mokallo", false));
        mediumQuestions.add(l1);

        // Question 2
        Question l2 = new Question(
                "lipapali_medium_2",
                "E bapaloa ke baroetsana le basali.e bapalloa pantleng ea motse. Leka koptjoa moreneng kapa la nioa ha moahi e mong motseng. Ho sebelisoa thupa e bitsoang lesokoana?",
                "l.jpg",
                null,
                category,
                Difficulty.MEDIUM);

        l2.addAnswer(new Answer("a1", "Lebelo", false));
        l2.addAnswer(new Answer("a2", "Lesokoana", true));
        l2.addAnswer(new Answer("a3", "Mokhibo", false));
        l2.addAnswer(new Answer("a4", "Mokallo", false));
        mediumQuestions.add(l2);

        // Question 3
        Question l3 = new Question(
                "lipapali_medium_3",
                "Ke lisebelisoa lefe tse sebelisoang papaling ea Manketjoane?",
                "water.png",
                null,
                category,
                Difficulty.MEDIUM);

        l3.addAnswer(new Answer("a1", "Metsi", true));
        l3.addAnswer(new Answer("a2", "Mofana", false));
        l3.addAnswer(new Answer("a3", "Kobo", false));
        l3.addAnswer(new Answer("a4", "Majoana", false));
        mediumQuestions.add(l3);

        // Question 4
        Question l4 = new Question(
                "lipapali_medium_4",
                "Papali ea se qata majoana, e bapalloa ho kae?",
                "stones.jpg",
                null,
                category,
                Difficulty.MEDIUM);

        l4.addAnswer(new Answer("a1", "Katlung", false));
        l4.addAnswer(new Answer("a2", "Ka thoko ho motse", false));
        l4.addAnswer(new Answer("a3", "Naheng", true));
        l4.addAnswer(new Answer("a4", "Ka sakeng", false));
        mediumQuestions.add(l4);

        // Question 5
        Question l5 = new Question(
                "lipapali_medium_5",
                " Papali ea lesokoana, e bapaloa ka sepheo sefeng?",
                "running.jpg",
                null,
                category,
                Difficulty.MEDIUM);

        l5.addAnswer(new Answer("a1", "Ho nesa pula", true));
        l5.addAnswer(new Answer("a2", "Ho alosa likhomo", false));
        l5.addAnswer(new Answer("a3", "Ho tsamaea", false));
        l5.addAnswer(new Answer("a4", "Ho ithapolla", false));
        mediumQuestions.add(l5);

        // HARD questions
        List<Question> hardQuestions = new ArrayList<>();
        // Question 1
        Question h1 = new Question(
                "lipapali_hard_1",
                "Papali e bapaloang ke bana ba seng bale boemong ba ho khenoha ?",
                "kid.jpg",
                null,
                category,
                Difficulty.HARD);

        h1.addAnswer(new Answer("a1", "Lebekere", false));
        h1.addAnswer(new Answer("a2", "Mankholi-Nkholi", true));
        h1.addAnswer(new Answer("a3", "Mokhibo", false));
        h1.addAnswer(new Answer("a4", "Mokallo", false));
        hardQuestions.add(h1);

        // Question 2
        Question h2 = new Question(
                "lipapali_hard_2",
                "Ke papali efe e bapaloang ka ho khaba le ho qhoma?",
                "jumping.jpg",
                null,
                category,
                Difficulty.HARD);

        h2.addAnswer(new Answer("a1", "Mokhibo", false));
        h2.addAnswer(new Answer("a2", "Mokou", true));
        h2.addAnswer(new Answer("a3", "Morabaraba", false));
        h2.addAnswer(new Answer("a4", "Liketo", false));
        hardQuestions.add(h2);

        // Question 3
        Question h3 = new Question(
                "lipapali_hard_3",
                "Papali efe e sebelisa litlhako tse peli tse entsoeng ka lerumo la nku?",
                "Sticks.jpg",
                null,
                category,
                Difficulty.HARD);

        h3.addAnswer(new Answer("a1", "Manketjoane", false));
        h3.addAnswer(new Answer("a2", "Mokallo", true));
        h3.addAnswer(new Answer("a3", "Khati", false));
        h3.addAnswer(new Answer("a4", "Lengangajane", false));
        hardQuestions.add(h3);

        // Question 4
        Question h4 = new Question(
                "lipapali_hard_4",
                "Ke papali efe e bapaloang ke basali kaho tsamaisa mahetla?",
                "jump_rope.jpg",
                null,
                category,
                Difficulty.HARD);

        h4.addAnswer(new Answer("a1", "Mokhibo", true));
        h4.addAnswer(new Answer("a2", "Morabaraba", false));
        h4.addAnswer(new Answer("a3", "khati", false));
        h4.addAnswer(new Answer("a4", "Liketo", false));
        hardQuestions.add(h4);

        // Question 5
        Question h5 = new Question(
                "lipapali_hard_5",
                "Ke Papali efe e bapaloang ka ho bapala le ho bina?",
                "sing.jpg",
                null,
                category,
                Difficulty.HARD);

        h5.addAnswer(new Answer("a1", "litolobonya", true));
        h5.addAnswer(new Answer("a2", "Morabaraba", false));
        h5.addAnswer(new Answer("a3", "Mokou", false));
        h5.addAnswer(new Answer("a4", "Liketo", false));
        hardQuestions.add(h5);

        // Add the questions to the question bank
        addQuestionsForCategory(category.getId(), Difficulty.EASY, easyQuestions);
        addQuestionsForCategory(category.getId(), Difficulty.MEDIUM, mediumQuestions);
        addQuestionsForCategory(category.getId(), Difficulty.HARD, hardQuestions);
    }

    //Add sample questions for the Maele category.
    private void addMaeleQuestions(Category category) {
        // EASY questions
        List<Question> easyQuestions = new ArrayList<>();

        // Question 1
        Question q1 = new Question(
                "maele_easy_1",
                "Qetella maele: 'Ngoana sa lleng, o...'",
                "baby.jpg",
                null,
                category,
                Difficulty.EASY
        );
        q1.addAnswer(new Answer("a1", "shoela tharing ", true));
        q1.addAnswer(new Answer("a2", "oa tsamaea", false));
        q1.addAnswer(new Answer("a3", "khethoa ke batho", false));
        q1.addAnswer(new Answer("a4", "ja bohobe", false));
        easyQuestions.add(q1);

        // Question 2
        Question q2 = new Question(
                "maele_easy_2",
                "Qetella maele: 'Khomo li lla...'",
                "cow.jpeg",
                null,
                category,
                Difficulty.EASY
        );
        q2.addAnswer(new Answer("a1", "Linala", false));
        q2.addAnswer(new Answer("a2", "Mali", false));
        q2.addAnswer(new Answer("a3", "Metsi", false));
        q2.addAnswer(new Answer("a4", "Mosoang", true));
        easyQuestions.add(q2);

        // Question 3
        Question q3 = new Question(
                "maele_easy_3",
                "Qetella maele: 'Ntja tsa Setsomi li...'",
                "dog.jpg",
                null,
                category,
                Difficulty.EASY
        );
        q3.addAnswer(new Answer("a1", "jana Maro", true));
        q3.addAnswer(new Answer("a2", "tsamaea mmoho", false));
        q3.addAnswer(new Answer("a3", "lla", false));
        q3.addAnswer(new Answer("a4", "ja nama", false));
        easyQuestions.add(q3);

        // Question 4
        Question q4 = new Question(
                "maele_easy_4",
                "Qetella maele: 'Ngoana mahana a joetsoa o...'",
                "kid.jpg",
                null,
                category,
                Difficulty.EASY
        );
        q4.addAnswer(new Answer("a1", "oa tsamaea", false));
        q4.addAnswer(new Answer("a2", "ha a tšoare", false));
        q4.addAnswer(new Answer("a3", "bonoa ka likhapha", true));
        q4.addAnswer(new Answer("a4", "oa ja", false));
        easyQuestions.add(q4);

        // Question 5
        Question q5 = new Question(
                "maele_easy_5",
                "'Ntja e ingoaile ka leoto la pele e bolela...'",
                "dog.jpg",
                null,
                category,
                Difficulty.EASY
        );
        q5.addAnswer(new Answer("a1", "Monate", false));
        q5.addAnswer(new Answer("a2", "Ho boima", true));
        q5.addAnswer(new Answer("a3", "Hoa bata", false));
        q5.addAnswer(new Answer("a4", "pula e nele", false));
        easyQuestions.add(q5);

        // MEDIUM questions
        List<Question> mediumQuestions = new ArrayList<>();
        Question m1 = new Question(
                "maele_medium_1",
                "Qetella maele: 'Monga Khomo o ...'",
                "cow.jpeg",
                null,
                category,
                Difficulty.MEDIUM
        );
        m1.addAnswer(new Answer("a1", "Lema ka eona", false));
        m1.addAnswer(new Answer("a2", "Ja nama e ngata", false));
        m1.addAnswer(new Answer("a3", "Noa Metsi", false));
        m1.addAnswer(new Answer("a4", "Ja lefureng", true));
        mediumQuestions.add(m1);

        Question m2 = new Question(
                "maele_medium_2",
                "Qetella maele: 'Leoto ke ...'",
                "foot.jpg",
                null,
                category,
                Difficulty.MEDIUM
        );
        m2.addAnswer(new Answer("a1", "Moloi", true));
        m2.addAnswer(new Answer("a2", "tsamaea", false));
        m2.addAnswer(new Answer("a3", "Molamu", false));
        m2.addAnswer(new Answer("a4", "lijo", false));
        mediumQuestions.add(m2);

        Question m3 = new Question(
                "maele_medium_3",
                "Katse haele sio toeba lia...'",
                "cat.jpg",
                null,
                category,
                Difficulty.MEDIUM
        );
        m3.addAnswer(new Answer("a1", "tsamaea", false));
        m3.addAnswer(new Answer("a2", "tšoaroa", false));
        m3.addAnswer(new Answer("a3", "nyanyaka", true));
        m3.addAnswer(new Answer("a4", "ja", false));
        mediumQuestions.add(m3);

        Question m4 = new Question(
                "maele_medium_4",
                "khomo ea lebese hae itsoale' a bolelang maele a?",
                "milk.jpg",
                null,
                category,
                Difficulty.MEDIUM
        );
        m4.addAnswer(new Answer("a1", "oa tsamaea", false));
        m4.addAnswer(new Answer("a2", "hase hangata ngoana a futsang Motsoali", true));
        m4.addAnswer(new Answer("a3", "oa lla", false));
        m4.addAnswer(new Answer("a4", "oa ja", false));
        mediumQuestions.add(m4);

        Question m5 = new Question(
                "maele_medium_5",
                "Qetella maele: Leboela ha le ...'",
                "leboela.jpg",
                null,
                category,
                Difficulty.MEDIUM
        );
        m5.addAnswer(new Answer("a1", "tsamaee", false));
        m5.addAnswer(new Answer("a2", "jooe", false));
        m5.addAnswer(new Answer("a3", "ngalloe", true));
        m5.addAnswer(new Answer("a4", "khutleloe", false));
        mediumQuestions.add(m5);

        // HARD questions
        List<Question> hardQuestions = new ArrayList<>();
        Question h1 = new Question(
                "maele_hard_1",
                "Qetela: Mejo hae rutanoe, ho rutanoa...'",
                "father.png",
                null,
                category,
                Difficulty.HARD
        );
        h1.addAnswer(new Answer("a1", "litlhare", true));
        h1.addAnswer(new Answer("a2", "boloi", false));
        h1.addAnswer(new Answer("a3", "Leshano", false));
        h1.addAnswer(new Answer("a4", "ho kalla", false));
        hardQuestions.add(h1);

        Question h2 = new Question(
                "maele_hard_2",
                "Qetella maele: 'Pinyane hae ...'",
                "secret.jpg",
                null,
                category,
                Difficulty.HARD
        );
        h2.addAnswer(new Answer("a1", "tsamaee", false));
        h2.addAnswer(new Answer("a2", "ha e bolokehe", false));
        h2.addAnswer(new Answer("a3", "senye motse", true));
        h2.addAnswer(new Answer("a4", "fihle", false));
        hardQuestions.add(h2);

        Question h3 = new Question(
                "maele_hard_3",
                "Qetella maele: 'Tsela-kgopo ha e robatse...'",
                "tsela.jpg",
                null,
                category,
                Difficulty.HARD
        );
        h3.addAnswer(new Answer("a1", "Naheng", true));
        h3.addAnswer(new Answer("a2", "Hae", false));
        h3.addAnswer(new Answer("a3", "Matsekela", false));
        h3.addAnswer(new Answer("a4", "Sekolong", false));
        hardQuestions.add(h3);

        Question h4 = new Question(
                "maele_hard_4",
                "u jela khoebeleng joaloaka ka...'",
                "papa.jpg",
                null,
                category,
                Difficulty.HARD
        );
        h4.addAnswer(new Answer("a1", "Motho", false));
        h4.addAnswer(new Answer("a2", "Ntja", false));
        h4.addAnswer(new Answer("a3", "leeba", true));
        h4.addAnswer(new Answer("a4", "Tlou", false));
        hardQuestions.add(h4);

        Question h5 = new Question(
                "maele_hard_5",
                "makhulo ho psha a...?'",
                "makhulo.jpg",
                null,
                category,
                Difficulty.HARD
        );
        h5.addAnswer(new Answer("a1", "a matala", true));
        h5.addAnswer(new Answer("a2", "a morena", false));
        h5.addAnswer(new Answer("a3", "bohle", false));
        h5.addAnswer(new Answer("a4", "Leribe", false));
        hardQuestions.add(h5);

        // Add the questions to the question bank
        addQuestionsForCategory(category.getId(), Difficulty.EASY, easyQuestions);
        addQuestionsForCategory(category.getId(), Difficulty.MEDIUM, mediumQuestions);
        addQuestionsForCategory(category.getId(), Difficulty.HARD, hardQuestions);
    }

    //Add sample questions for the Liboko category.
    private void addLibokoQuestions(Category category) {
        // EASY questions
        List<Question> easyQuestions = new ArrayList<>();

        // Question 1
        Question q1 = new Question(
                "liboko_easy_1",
                "Ke seboko sefe se rehelletsoeng ka 'Kuena'?",
                "crocodile.jpeg",
                null,
                category,
                Difficulty.EASY
        );
        q1.addAnswer(new Answer("a1", "Bataung", false));
        q1.addAnswer(new Answer("a2", "Bakuena", true));
        q1.addAnswer(new Answer("a3", "Bafokeng", false));
        q1.addAnswer(new Answer("a4", "Batlokoa", false));
        easyQuestions.add(q1);

        // Question 2
        Question q2 = new Question(
                "liboko_easy_2",
                "Bafokeng ba ana kang?",
                "foks.jpg",
                null,
                category,
                Difficulty.EASY
        );
        q2.addAnswer(new Answer("a1", "Poli", false));
        q2.addAnswer(new Answer("a2", "'Mutlanyane", true));
        q2.addAnswer(new Answer("a3", "Ntja", false));
        q2.addAnswer(new Answer("a4", "Ts'ints'i", false));
        easyQuestions.add(q2);

        // Question 3
        Question q3 = new Question(
                "liboko_easy_3",
                "Batlokoa ba ana kang?",
                "tlokoa.jpg",
                null,
                category,
                Difficulty.EASY
        );
        q3.addAnswer(new Answer("a1", "Poli", false));
        q3.addAnswer(new Answer("a2", "'Mutlanyane", false));
        q3.addAnswer(new Answer("a3", "Lengau", true));
        q3.addAnswer(new Answer("a4", "Ts'ints'i", false));
        easyQuestions.add(q3);

        // Question 4
        Question q4 = new Question(
                "liboko_easy_4",
                "Bataung ba ana kang?",
                "bataung.jpg",
                null,
                category,
                Difficulty.EASY
        );
        q4.addAnswer(new Answer("a1", "Tau", true));
        q4.addAnswer(new Answer("a2", "'Mutlanyane", false));
        q4.addAnswer(new Answer("a3", "Ntja", false));
        q4.addAnswer(new Answer("a4", "Ts'ints'i", false));
        easyQuestions.add(q4);

        // Question 5
        Question q5 = new Question(
                "liboko_easy_5",
                "Bakuena ba ana kang?",
                "koena.jpg",
                null,
                category,
                Difficulty.EASY
        );
        q5.addAnswer(new Answer("a1", "Poli", false));
        q5.addAnswer(new Answer("a2", "'Mutlanyane", false));
        q5.addAnswer(new Answer("a3", "Ntja", false));
        q5.addAnswer(new Answer("a4", "Kuena", true));
        easyQuestions.add(q5);

        // MEDIUM questions
        List<Question> mediumQuestions = new ArrayList<>();
        Question m1 = new Question(
                "liboko_medium_1",
                "Basia ba ana kang?",
                "basia.jpg",
                null,
                category,
                Difficulty.MEDIUM
        );
        m1.addAnswer(new Answer("a1", "nonyana", false));
        m1.addAnswer(new Answer("a2", "katse", true));
        m1.addAnswer(new Answer("a3", "khoho", false));
        m1.addAnswer(new Answer("a4", "khomo", false));
        mediumQuestions.add(m1);

        Question m2 = new Question(
                "liboko_medium_2",
                "Makholoekoe a ana kang?",
                "wild.jpg",
                null,
                category,
                Difficulty.MEDIUM
        );
        m2.addAnswer(new Answer("a1", "nonyana", false));
        m2.addAnswer(new Answer("a2", "katse", false));
        m2.addAnswer(new Answer("a3", "khoho", true));
        m2.addAnswer(new Answer("a4", "khomo", false));
        mediumQuestions.add(m2);

        Question m3 = new Question(
                "liboko_medium_3",
                " Makhoakhoa a ana kang?",
                "khoa.jpg",
                null,
                category,
                Difficulty.MEDIUM
        );
        m3.addAnswer(new Answer("a1", "nonyana", false));
        m3.addAnswer(new Answer("a2", "katse", false));
        m3.addAnswer(new Answer("a3", "khoho", false));
        m3.addAnswer(new Answer("a4", "Mokopu", true));
        mediumQuestions.add(m3);

        Question m4 = new Question(
                "liboko_medium_4",
                "BaPhuthi ba ana kang?",
                "lion.jpg",
                null,
                category,
                Difficulty.MEDIUM
        );
        m4.addAnswer(new Answer("a1", "nonyana", false));
        m4.addAnswer(new Answer("a2", "Phuthi", true));
        m4.addAnswer(new Answer("a3", "khoho", false));
        m4.addAnswer(new Answer("a4", "khomo", false));
        mediumQuestions.add(m4);

        Question m5 = new Question(
                "liboko_medium_5",
                "Batlokoa ba ana kang?",
                "croc.jpg",
                null,
                category,
                Difficulty.MEDIUM
        );
        m5.addAnswer(new Answer("a1", "Nkoe", true));
        m5.addAnswer(new Answer("a2", "katse", false));
        m5.addAnswer(new Answer("a3", "khoho", false));
        m5.addAnswer(new Answer("a4", "khomo", false));
        mediumQuestions.add(m5);

        // HARD questions
        List<Question> hardQuestions = new ArrayList<>();
        Question h1 = new Question(
                "liboko_hard_1",
                "Ke seboko sefe se rehelletsoeng ka 'Kuena'?",
                "crocodile.svg",
                null,
                category,
                Difficulty.HARD
        );
        h1.addAnswer(new Answer("a1", "Bataung", false));
        h1.addAnswer(new Answer("a2", "Bakuena", true));
        h1.addAnswer(new Answer("a3", "Bafokeng", false));
        h1.addAnswer(new Answer("a4", "Batlokoa", false));
        hardQuestions.add(h1);

        Question h2 = new Question(
                "liboko_hard_2",
                "Bafokeng ba ana kang?",
                "baf.jpg",
                null,
                category,
                Difficulty.HARD
        );
        h2.addAnswer(new Answer("a1", "Poli", false));
        h2.addAnswer(new Answer("a2", "'Mutlanyane", true));
        h2.addAnswer(new Answer("a3", "Ntja", false));
        h2.addAnswer(new Answer("a4", "Ts'ints'i", false));
        hardQuestions.add(h2);

        Question h3 = new Question(
                "liboko_hard_3",
                "Bakhatla ba ana kang?",
                "khatla.jpg",
                null,
                category,
                Difficulty.HARD
        );
        h3.addAnswer(new Answer("a1", "Poli", false));
        h3.addAnswer(new Answer("a2", "'Mutlanyane", false));
        h3.addAnswer(new Answer("a3", "Lenong", true));
        h3.addAnswer(new Answer("a4", "Ts'ints'i", false));
        hardQuestions.add(h3);

        Question h4 = new Question(
                "liboko_hard_4",
                "Bataung ba ana kang?",
                "tau.jpg",
                null,
                category,
                Difficulty.HARD
        );
        h4.addAnswer(new Answer("a1", "Poli", true));
        h4.addAnswer(new Answer("a2", "'Mutlanyane", false));
        h4.addAnswer(new Answer("a3", "Ntja", false));
        h4.addAnswer(new Answer("a4", "Ts'ints'i", false));
        hardQuestions.add(h4);

        Question h5 = new Question(
                "liboko_hard_5",
                "Bakubung ba ana kang?",
                "kubu.jpg",
                null,
                category,
                Difficulty.HARD
        );
        h5.addAnswer(new Answer("a1", "Poli", false));
        h5.addAnswer(new Answer("a2", "'Mutlanyane", false));
        h5.addAnswer(new Answer("a3", "Ntja", false));
        h5.addAnswer(new Answer("a4", "kubu", true));
        hardQuestions.add(h5);

        // Add the questions to the question bank
        addQuestionsForCategory(category.getId(), Difficulty.EASY, easyQuestions);
        addQuestionsForCategory(category.getId(), Difficulty.MEDIUM, mediumQuestions);
        addQuestionsForCategory(category.getId(), Difficulty.HARD, hardQuestions);
    }

    //Add sample questions for the Lijo category.
    private void addLijoQuestions(Category category) {
        // EASY questions
        List<Question> easyQuestions = new ArrayList<>();

        // Question 1
        Question q1 = new Question(
                "lijo_easy_1",
                "Ke eng dijo tsa motheo tsa Basotho?",
                "poone.jpg",
                null,
                category,
                Difficulty.EASY
        );
        q1.addAnswer(new Answer("a1", "Nama", false));
        q1.addAnswer(new Answer("a2", "Poone", true));
        q1.addAnswer(new Answer("a3", "Dinawa ", false));
        q1.addAnswer(new Answer("a4", "Raese", false));
        easyQuestions.add(q1);

        // Question 2
        Question q2 = new Question(
                "lijo_easy_2",
                "Ke sesebelisoa se feng se sa lokeleng hoba teng haho etsoa joala ba sesotho?",
                "joala.jpg",
                null,
                category,
                Difficulty.EASY
        );
        q2.addAnswer(new Answer("a1", "Phoofo ea poone", false));
        q2.addAnswer(new Answer("a2", "Biri", true));
        q2.addAnswer(new Answer("a3", "Litomoso ", false));
        q2.addAnswer(new Answer("a4", "Metsi a chesang", false));
        easyQuestions.add(q2);

        // Question 3
        Question q3 = new Question(
                "lijo_easy_3",
                "sebera ke eng?",
                "maize.jpg",
                null,
                category,
                Difficulty.EASY
        );
        q3.addAnswer(new Answer("a1", "Nama", false));
        q3.addAnswer(new Answer("a2", "Poone e Inetsoeng", true));
        q3.addAnswer(new Answer("a3", "Dinawa ", false));
        q3.addAnswer(new Answer("a4", "Raese", false));
        easyQuestions.add(q3);

        // Question 4
        Question q4 = new Question(
                "lijo_easy_4",
                "Lepu le jooa nakong efeng ea selemo",
                "lepu.jpg",
                null,
                category,
                Difficulty.EASY
        );
        q4.addAnswer(new Answer("a1", "Selemo", false));
        q4.addAnswer(new Answer("a2", "Hoetla", true));
        q4.addAnswer(new Answer("a3", "Hlabula ", false));
        q4.addAnswer(new Answer("a4", "Mariha", false));
        easyQuestions.add(q4);

        // Question 5
        Question q5 = new Question(
                "lijo_easy_5",
                "Sebutsoabutsoane keng?",
                "s.jpg",
                null,
                category,
                Difficulty.EASY
        );
        q5.addAnswer(new Answer("a1", "Nama", false));
        q5.addAnswer(new Answer("a2", "Mokupu o besitsoeng", true));
        q5.addAnswer(new Answer("a3", "Dinawa ", false));
        q5.addAnswer(new Answer("a4", "Raese", false));
        easyQuestions.add(q5);

        // MEDIUM questions
        List<Question> mediumQuestions = new ArrayList<>();
        Question m1 = new Question(
                "lijo_medium_1",
                "Nyekoe keng?",
                "papa.jpg",
                null,
                category,
                Difficulty.MEDIUM
        );
        m1.addAnswer(new Answer("a1", "Nama", false));
        m1.addAnswer(new Answer("a2", "Mabele, Mokopu le Linaoa", true));
        m1.addAnswer(new Answer("a3", "Robella ka lebese ", false));
        m1.addAnswer(new Answer("a4", "Papa ea mabele", false));
        mediumQuestions.add(m1);

        Question m2 = new Question(
                "lijo_medium_2",
                "Qhubu ke eng?",
                "maize.jpg",
                null,
                category,
                Difficulty.MEDIUM
        );
        m2.addAnswer(new Answer("a1", "Nama", false));
        m2.addAnswer(new Answer("a2", "Likhobe Tsa Poone", true));
        m2.addAnswer(new Answer("a3", "Dinawa ", false));
        m2.addAnswer(new Answer("a4", "Raese", false));
        mediumQuestions.add(m2);

        Question m3 = new Question(
                "lijo_medium_3",
                "Lekhets'o ke lijo tse joang?",
                "likhe.jpg",
                null,
                category,
                Difficulty.MEDIUM
        );
        m3.addAnswer(new Answer("a1", "Phoofo ea poone", false));
        m3.addAnswer(new Answer("a2", "Mokopu o phehiloeng o as betloa", true));
        m3.addAnswer(new Answer("a3", "Lihoapa ", false));
        m3.addAnswer(new Answer("a4", "Metsi a chesang", false));
        mediumQuestions.add(m3);

        Question m4 = new Question(
                "lijo_medium_4",
                "Lefotho keng?",
                "maize.jpg",
                null,
                category,
                Difficulty.MEDIUM
        );
        m4.addAnswer(new Answer("a1", "Nama", false));
        m4.addAnswer(new Answer("a2", "Poone ea lehoetla e phehiloeng ka bongata", true));
        m4.addAnswer(new Answer("a3", "Robella ka lebese ", false));
        m4.addAnswer(new Answer("a4", "Papa ea mabele", false));
        mediumQuestions.add(m4);

        Question m5 = new Question(
                "lijo_medium_5",
                "Ke eng Sekele?",
                "mokupu.jpg",
                null,
                category,
                Difficulty.MEDIUM
        );
        m5.addAnswer(new Answer("a1", "Nama", false));
        m5.addAnswer(new Answer("a2", "Mokopu o Khotluoeng", true));
        m5.addAnswer(new Answer("a3", "Dinawa ", false));
        m5.addAnswer(new Answer("a4", "Raese", false));
        mediumQuestions.add(m5);

        // HARD questions
        List<Question> hardQuestions = new ArrayList<>();
        Question h1 = new Question(
                "lijo_hard_1",
                "Maqebekoane keng?",
                "bread.jpg",
                null,
                category,
                Difficulty.HARD
        );
        h1.addAnswer(new Answer("a1", "Nama", false));
        h1.addAnswer(new Answer("a2", "Bohobe ba koro bo apehiloeng ka metsi", true));
        h1.addAnswer(new Answer("a3", "Robella ka lebese ", false));
        h1.addAnswer(new Answer("a4", "Papa ea mabele", false));
        hardQuestions.add(h1);

        Question h2 = new Question(
                "lijo_hard_2",
                "Ke eng dijo tsa motheo tsa Basotho?",
                "poone.jpg",
                null,
                category,
                Difficulty.HARD
        );
        h2.addAnswer(new Answer("a1", "Nama", false));
        h2.addAnswer(new Answer("a2", "Poone", true));
        h2.addAnswer(new Answer("a3", "Dinawa ", false));
        h2.addAnswer(new Answer("a4", "Raese", false));
        hardQuestions.add(h2);

        Question h3 = new Question(
                "lijo_hard_3",
                "Thahameso keng?",
                "meso.jpg",
                null,
                category,
                Difficulty.HARD
        );
        h3.addAnswer(new Answer("a1", "Phoofo ea poone", false));
        h3.addAnswer(new Answer("a2", "Lijo tse pheoang hoseng ke Ngoetsi", true));
        h3.addAnswer(new Answer("a3", "Litomoso ", false));
        h3.addAnswer(new Answer("a4", "Metsi a chesang", false));
        hardQuestions.add(h3);

        Question h4 = new Question(
                "lijo_hard_4",
                "Potele ke eng?",
                "potele.jpg",
                null,
                category,
                Difficulty.HARD
        );
        h4.addAnswer(new Answer("a1", "Nama", false));
        h4.addAnswer(new Answer("a2", "Bohobe bo sokelletsoeng le mororo", true));
        h4.addAnswer(new Answer("a3", "Robella ka lebese ", false));
        h4.addAnswer(new Answer("a4", "Papa ea mabele", false));
        hardQuestions.add(h4);

        Question h5 = new Question(
                "lijo_hard_5",
                "Mangajane ke eng?",
                "dry.jpg",
                null,
                category,
                Difficulty.HARD
        );
        h5.addAnswer(new Answer("a1", "Nama", false));
        h5.addAnswer(new Answer("a2", "Liperekisi tse omisitsoeng", true));
        h5.addAnswer(new Answer("a3", "Dinawa ", false));
        h5.addAnswer(new Answer("a4", "Raese", false));
        hardQuestions.add(h5);

        // Add the questions to the question bank
        addQuestionsForCategory(category.getId(), Difficulty.EASY, easyQuestions);
        addQuestionsForCategory(category.getId(), Difficulty.MEDIUM, mediumQuestions);
        addQuestionsForCategory(category.getId(), Difficulty.HARD, hardQuestions);
    }

    //Add questions for a specific category and difficulty.
    private void addQuestionsForCategory(String categoryId, Difficulty difficulty, List<Question> questionList) {
        questions.computeIfAbsent(categoryId, k -> new HashMap<>());
        questions.get(categoryId).computeIfAbsent(difficulty, k -> new ArrayList<>());
        questions.get(categoryId).get(difficulty).addAll(questionList);
    }

    /*
     Get questions for a specific category and difficult
     */
    public List<Question> getQuestions(Category category, Difficulty difficulty) {
        return getQuestions(category.getId(), difficulty);
    }

    /*
     * Get questions for a specific category ID and difficulty
     */
    public List<Question> getQuestions(String categoryId, Difficulty difficulty) {
        if (questions.containsKey(categoryId) &&
                questions.get(categoryId).containsKey(difficulty)) {
            return new ArrayList<>(questions.get(categoryId).get(difficulty));
        }
        return new ArrayList<>();
    }

    /*
     Get the number of questions available for a specific category and difficulty.
     */
    public int getQuestionCount(Category category, Difficulty difficulty) {
        return getQuestionCount(category.getId(), difficulty);
    }

    /*
     * Get the number of questions available for a specific category ID and difficulty
     */
    public int getQuestionCount(String categoryId, Difficulty difficulty) {
        return getQuestions(categoryId, difficulty).size();
    }
}