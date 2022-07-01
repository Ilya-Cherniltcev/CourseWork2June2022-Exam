package pro.sky.june2022.coursework2exam;

import pro.sky.june2022.coursework2exam.data.Question;

import java.util.*;

public class JavaQuestionServiceTestConstants {
    public static final String EXAMPLE_NULL = null;
    // ---
    public static final String EXAMPLE_Q1 = "Что такое «переменная»?";
    public static final String EXAMPLE_A1 = "Это ячейка в памяти компьютера, которой можно присвоить имя и в которой можно хранить данные.";
    // ---
    public static final String EXAMPLE_Q2 = "Перечислите примитивные типы данных.";
    public static final String EXAMPLE_A2 = "Целочисленный, вещественный, логический, символьный.";
    // ---
    public static final String EXAMPLE_Q3 = "Что такое «цикл?»";
    public static final String EXAMPLE_A3 = "Конструкция языка, позволяющая выполнять один и тот же код многократно в зависимости от условий.";
    // ---
    public static final String EXAMPLE_Q4 = "Что такое инкапсуляция?";
    public static final String EXAMPLE_A4 = "Концепция, согласно которой мы не даем доступа к свойствам объекта, а получаем их значения через методы (геттеры и сеттеры).";
    // ---
    public static final String EXAMPLE_Q5 = "Что означает «инициализация?»";
    public static final String EXAMPLE_A5 = "Присваивание какого-то значения переменной.";

    public static final Question ADDING_QUESTION = new Question(EXAMPLE_Q5, EXAMPLE_A5);

    public static final Question REMOVING_QUESTION = new Question(EXAMPLE_Q1, EXAMPLE_A1);
    public static final Collection ALL_QUESTIONS = new ArrayList(List.of(
            new Question(EXAMPLE_Q4, EXAMPLE_A4),
            new Question(EXAMPLE_Q1, EXAMPLE_A1),
            new Question(EXAMPLE_Q3, EXAMPLE_A3),
            new Question(EXAMPLE_Q2, EXAMPLE_A2)));

    public static final Collection ALL_QUESTIONS_SET = new HashSet(List.of(
            new Question(EXAMPLE_Q4, EXAMPLE_A4),
            new Question(EXAMPLE_Q1, EXAMPLE_A1),
            new Question(EXAMPLE_Q3, EXAMPLE_A3),
            new Question(EXAMPLE_Q2, EXAMPLE_A2)));

    public static final Collection<Question> EXAMPLE_COLLECTION = new HashSet<>(List.of(
            new Question(EXAMPLE_Q4, EXAMPLE_A4)));
    public static final Question EXAMPLE_QUESTION = new Question(EXAMPLE_Q4, EXAMPLE_A4);


}
