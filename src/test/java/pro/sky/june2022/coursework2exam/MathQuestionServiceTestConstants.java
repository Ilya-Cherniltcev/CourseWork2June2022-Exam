package pro.sky.june2022.coursework2exam;

import pro.sky.june2022.coursework2exam.data.Question;

import java.util.*;

public class MathQuestionServiceTestConstants {
    public static final String MATH_EXAMPLE_NULL = null;
    // ---
    public static final String MATH_EXAMPLE_Q1 = "5+5=";
    public static final String MATH_EXAMPLE_A1 = "10";
    // ---
    public static final String MATH_EXAMPLE_Q2 = "82-1=";
    public static final String MATH_EXAMPLE_A2 = "81";
    // ---
    public static final String MATH_EXAMPLE_Q3 = "100*2=";
    public static final String MATH_EXAMPLE_A3 = "200";
    // ---
    public static final String MATH_EXAMPLE_Q4 = "333/3=";
    public static final String MATH_EXAMPLE_A4 = "111";
    // ---
    public static final String MATH_EXAMPLE_Q5 = "500-500=";
    public static final String MATH_EXAMPLE_A5 = "0";

    public static final Question MATH_ADDING_QUESTION = new Question(MATH_EXAMPLE_Q5, MATH_EXAMPLE_A5);

    public static final Question MATH_REMOVING_QUESTION = new Question(MATH_EXAMPLE_Q1, MATH_EXAMPLE_A1);

    public static final Set<Question> MATH_ALL_QUESTIONS_SET = Set.of(
            new Question(MATH_EXAMPLE_Q4, MATH_EXAMPLE_A4),
            new Question(MATH_EXAMPLE_Q1, MATH_EXAMPLE_A1),
            new Question(MATH_EXAMPLE_Q3, MATH_EXAMPLE_A3),
            new Question(MATH_EXAMPLE_Q2, MATH_EXAMPLE_A2));
}
