package com.example.rehabready;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import kotlin.jvm.internal.Intrinsics;

public final class Constants {
    @NotNull
    public static final String QUIZ_TYPE = "quiz_type";
    @NotNull
    public static final String USER_NAME = "user_name";
    @NotNull
    public static final String TOTAL_QUESTIONS = "total_questions";
    @NotNull
    public static final String CORRECT_ANSWER = "correct_answers";
    public static final int QUIZ_TYPE_1 = 1;
    public static final int QUIZ_TYPE_2 = 2;
    private static int MAX_PROGRESS_VALUE;
    @NotNull
    public static final Constants INSTANCE;

    @NotNull
    public final ArrayList getQuestionsType1() {
        ArrayList questionsList = new ArrayList();
        QuizQuestion que1 = new QuizQuestion(1, "I feel optimistic about my future.", "Strongly Agree", "Agree", "Disagree", "Strongly Disagree");
        questionsList.add(que1);
        QuizQuestion que2 = new QuizQuestion(2, "I have a positive outlook on life.", "Strongly Agree", "Agree", "Disagree", "Strongly Disagree");
        questionsList.add(que2);
        QuizQuestion que3 = new QuizQuestion(3, "I am confident in my ability to overcome challenges.", "Strongly Agree", "Agree", "Disagree", "Strongly Disagree");
        questionsList.add(que3);
        QuizQuestion que4 = new QuizQuestion(4, "I believe in my capacity for personal growth.", "Strongly Agree", "Agree", "Disagree", "Strongly Disagree");
        questionsList.add(que4);
        QuizQuestion que5 = new QuizQuestion(5, "I am resilient in the face of adversity.", "Strongly Agree", "Agree", "Disagree", "Strongly Disagree");
        questionsList.add(que5);
        QuizQuestion que6 = new QuizQuestion(6, "I have a positive attitude towards myself.", "Strongly Agree", "Agree", "Disagree", "Strongly Disagree");
        questionsList.add(que6);
        QuizQuestion que7 = new QuizQuestion(7, "I am motivated to achieve my goals.", "Strongly Agree", "Agree", "Disagree", "Strongly Disagree");
        questionsList.add(que7);
        QuizQuestion que8 = new QuizQuestion(8, "I see opportunities in every challenge.", "Strongly Agree", "Agree", "Disagree", "Strongly Disagree");
        questionsList.add(que8);
        QuizQuestion que9 = new QuizQuestion(9, "I believe in my ability to change for the better.", "Strongly Agree", "Agree", "Disagree", "Strongly Disagree");
        questionsList.add(que9);
        QuizQuestion que10 = new QuizQuestion(10, "I have a sense of purpose in life.", "Strongly Agree", "Agree", "Disagree", "Strongly Disagree");
        questionsList.add(que10);
        return questionsList;
    }

    @NotNull
    public final ArrayList getQuestionsType2() {
        ArrayList questionsList = new ArrayList();
        QuizQuestion que1 = new QuizQuestion(1, "I prioritize my physical well-being.", "Strongly Agree", "Agree", "Disagree", "Strongly Disagree");
        questionsList.add(que1);
        QuizQuestion que2 = new QuizQuestion(2, "I practice mindfulness regularly.", "Strongly Agree", "Agree", "Disagree", "Strongly Disagree");
        questionsList.add(que2);
        QuizQuestion que3 = new QuizQuestion(3, "I set healthy boundaries in my relationships.", "Strongly Agree", "Agree", "Disagree", "Strongly Disagree");
        questionsList.add(que3);
        QuizQuestion que4 = new QuizQuestion(4, "I make time for activities I enjoy.", "Strongly Agree", "Agree", "Disagree", "Strongly Disagree");
        questionsList.add(que4);
        QuizQuestion que5 = new QuizQuestion(5, "I prioritize getting enough sleep.", "Strongly Agree", "Agree", "Disagree", "Strongly Disagree");
        questionsList.add(que5);
        QuizQuestion que6 = new QuizQuestion(6, "I engage in activities that bring me joy.", "Strongly Agree", "Agree", "Disagree", "Strongly Disagree");
        questionsList.add(que6);
        QuizQuestion que7 = new QuizQuestion(7, "I practice self-compassion.", "Strongly Agree", "Agree", "Disagree", "Strongly Disagree");
        questionsList.add(que7);
        QuizQuestion que8 = new QuizQuestion(8, "I take breaks to rest and recharge.", "Strongly Agree", "Agree", "Disagree", "Strongly Disagree");
        questionsList.add(que8);
        QuizQuestion que9 = new QuizQuestion(9, "I express my emotions in a healthy way.", "Strongly Agree", "Agree", "Disagree", "Strongly Disagree");
        questionsList.add(que9);
        QuizQuestion que10 = new QuizQuestion(10, "I practice gratitude regularly.", "Strongly Agree", "Agree", "Disagree", "Strongly Disagree");
        questionsList.add(que10);
        QuizQuestion que11 = new QuizQuestion(11, "I engage in activities that promote relaxation.", "Strongly Agree", "Agree", "Disagree", "Strongly Disagree");
        questionsList.add(que11);
        QuizQuestion que12 = new QuizQuestion(12, "I communicate openly about my needs.", "Strongly Agree", "Agree", "Disagree", "Strongly Disagree");
        questionsList.add(que12);
        QuizQuestion que13 = new QuizQuestion(13, "I engage in regular physical activity.", "Strongly Agree", "Agree", "Disagree", "Strongly Disagree");
        questionsList.add(que13);
        QuizQuestion que14 = new QuizQuestion(14, "I seek support when needed.", "Strongly Agree", "Agree", "Disagree", "Strongly Disagree");
        questionsList.add(que14);
        QuizQuestion que15 = new QuizQuestion(15, "I make time for self-reflection.", "Strongly Agree", "Agree", "Disagree", "Strongly Disagree");
        questionsList.add(que15);
        return questionsList;
    }

    @NotNull
    public final ArrayList getQuestions(int selectedQuizType) {
        String var10000;
        switch (selectedQuizType) {
            case 1:
                var10000 = "Psychological Quiz";
                break;
            case 2:
                var10000 = "Self Help Quiz";
                break;
            default:
                var10000 = "Psychological Quiz";
        }

        String quizTypeString = var10000;
        ArrayList var3;
        switch (this.getSelectedQuizType(quizTypeString)) {
            case 1:
                var3 = this.getQuestionsType1();
                break;
            case 2:
                var3 = this.getQuestionsType2();
                break;
            default:
                var3 = this.getQuestionsType1();
        }

        return var3;
    }

    public final int getSelectedQuizType(@NotNull String quizType) {
        Intrinsics.checkNotNullParameter(quizType, "quizType");
        byte var10000;
        switch (quizType) {
            case "Psychological Quiz":
                var10000 = 1;
                return var10000;
            case "Self Help Quiz":
                var10000 = 2;
                return var10000;
        }

        var10000 = 1;
        return var10000;
    }

    public final int getMAX_PROGRESS_VALUE() {
        return MAX_PROGRESS_VALUE;
    }

    public final void setMAX_PROGRESS_VALUE(int var1) {
        MAX_PROGRESS_VALUE = var1;
    }

    private Constants() {
    }

    static {
        Constants var0 = new Constants();
        INSTANCE = var0;
        MAX_PROGRESS_VALUE = 10;
    }
}
