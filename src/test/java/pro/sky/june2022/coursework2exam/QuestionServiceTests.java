package pro.sky.june2022.coursework2exam;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pro.sky.june2022.coursework2exam.data.Question;
import pro.sky.june2022.coursework2exam.exceptions.WrongRequestOfQuestionsException;
import pro.sky.june2022.coursework2exam.exceptions.WrongSizeOfListException;
import pro.sky.june2022.coursework2exam.interfaces.QuestionService;
import pro.sky.june2022.coursework2exam.services.JavaQuestionService;

import java.util.Collection;

import static pro.sky.june2022.coursework2exam.QuestionServiceTestConstants.*;

@SpringBootTest
class QuestionServiceTests {
    private QuestionService questionService;

    // ===== заполняем мапу перед всеми тестами =====
    @BeforeEach
    public void setup() {
        questionService = new JavaQuestionService();
        questionService.add("Что такое «переменная»?", "Это ячейка в памяти компьютера, которой можно присвоить имя и в которой можно хранить данные.");
        questionService.add("Перечислите примитивные типы данных.", "Целочисленный, вещественный, логический, символьный.");
        questionService.add("Что такое «цикл?»", "Конструкция языка, позволяющая выполнять один и тот же код многократно в зависимости от условий.");
        questionService.add("Что такое инкапсуляция?", "Концепция, согласно которой мы не даем доступа к свойствам объекта, а получаем их значения через методы (геттеры и сеттеры).");
    }

    @Test
    void shouldReturnExceptionIfTheQuestionsStringIsNull() {
        Assertions.assertThrows(WrongRequestOfQuestionsException.class,
                () -> questionService.add(EXAMPLE_NULL, "Простой пример"));
    }

    @Test
    void shouldReturnExceptionIfTheAnswersStringIsNull() {
        Assertions.assertThrows(WrongRequestOfQuestionsException.class,
                () -> questionService.add("Простой пример", EXAMPLE_NULL));
    }

    @Test
    void shouldReturnExceptionIfTheMapIsEmpty() {
        questionService = new JavaQuestionService();
        Assertions.assertThrows(WrongSizeOfListException.class,
                () -> questionService.find("Простой пример", "Простой пример"));
    }

    @Test
    void shouldReturnWriteAddingQuestiongIfStrings() {
        Question result = questionService.add("Что означает «инициализация?»", "Присваивание какого-то значения переменной.");
        Assertions.assertEquals(ADDING_QUESTION, result);
    }

    @Test
    void shouldReturnWriteAddingQuestiong() {
        Question result = questionService.add(new Question("Что означает «инициализация?»", "Присваивание какого-то значения переменной."));
        Assertions.assertEquals(ADDING_QUESTION, result);
    }

    @Test
    void shouldReturnWriteFindingQuestiong() {
        Question result = questionService.find("Что такое «переменная»?", "Это ячейка в памяти компьютера, которой можно присвоить имя и в которой можно хранить данные.");
        Assertions.assertEquals(REMOVING_QUESTION, result);
    }

    @Test
    void shouldReturnWriteRemovingQuestiong() {
        Question request = new Question("Что такое «переменная»?", "Это ячейка в памяти компьютера, которой можно присвоить имя и в которой можно хранить данные.");
        Question result = questionService.remove(request);
        Assertions.assertEquals(REMOVING_QUESTION, result);
    }

    @Test
    void shouldReturnAllQuestiongs() {
        Collection<Question> results = questionService.getAll();
        results = results.stream().toList();
        Assertions.assertEquals(ALL_QUESTIONS, results);
    }

    @Test
    void shouldReturnSizeOfMap() {
        int result = questionService.getSizeOfMap();
        Assertions.assertEquals(SIZE_OF_MAP, result);
    }

}
