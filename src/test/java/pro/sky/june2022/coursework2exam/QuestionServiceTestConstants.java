package pro.sky.june2022.coursework2exam;

import pro.sky.june2022.coursework2exam.data.Question;

import java.util.*;

public class QuestionServiceTestConstants {
    public static final String EXAMPLE_NULL = null;
    public static final Question ADDING_QUESTION = new Question("Что означает «инициализация?»", "Присваивание какого-то значения переменной.");


    public static final Question REMOVING_QUESTION = new Question("Что такое «переменная»?", "Это ячейка в памяти компьютера, которой можно присвоить имя и в которой можно хранить данные.");
    public static final Collection<Question> ALL_QUESTIONS = new ArrayList<>(List.of(
            new Question("Что такое инкапсуляция?", "Концепция, согласно которой мы не даем доступа к свойствам объекта, а получаем их значения через методы (геттеры и сеттеры)."),
            new Question("Что такое «переменная»?", "Это ячейка в памяти компьютера, которой можно присвоить имя и в которой можно хранить данные."),
            new Question("Что такое «цикл?»", "Конструкция языка, позволяющая выполнять один и тот же код многократно в зависимости от условий."),
            new Question("Перечислите примитивные типы данных.", "Целочисленный, вещественный, логический, символьный.")));

    public static final Collection<Question> EXAMPLE_COLLECTION = new HashSet<>(List.of(
            new Question("Что такое инкапсуляция?", "Концепция, согласно которой мы не даем доступа к свойствам объекта, а получаем их значения через методы (геттеры и сеттеры).")));
    public static final Question EXAMPLE_QUESTION =
            new Question("Что такое инкапсуляция?", "Концепция, согласно которой мы не даем доступа к свойствам объекта, а получаем их значения через методы (геттеры и сеттеры).");


}
