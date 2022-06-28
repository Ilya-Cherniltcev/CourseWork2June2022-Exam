package pro.sky.june2022.coursework2exam;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pro.sky.june2022.coursework2exam.data.Question;
import pro.sky.june2022.coursework2exam.exceptions.WrongRequestOfQuestionsException;
import pro.sky.june2022.coursework2exam.interfaces.QuestionService;
import pro.sky.june2022.coursework2exam.services.JavaQuestionService;

import java.util.Collection;

import static pro.sky.june2022.coursework2exam.QuestionServiceTestConstants.*;

@SpringBootTest
class QuestionServiceTests {
    private final QuestionService questionService = new JavaQuestionService();

    // ===== заполняем JAVA-репозиторий перед всеми тестами =====
    @BeforeEach
    public void setup() {
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
    void shouldReturnExceptionIfTheQuestionIsExist() {
        Assertions.assertThrows(WrongRequestOfQuestionsException.class,
                () -> questionService.add("Что такое «переменная»?", "Это ячейка в памяти компьютера, которой можно присвоить имя и в которой можно хранить данные."));
    }


    @Test
    void shouldReturnWriteAddingQuestiongIfStrings() {
        Question result = questionService.add("Что означает «инициализация?»", "Присваивание какого-то значения переменной.");
        Assertions.assertEquals(ADDING_QUESTION, result);
    }

    @Test
    void shouldReturnWriteRemovingQuestiong() {
        Question result = questionService.remove("Что такое «переменная»?", "Это ячейка в памяти компьютера, которой можно присвоить имя и в которой можно хранить данные.");
        Assertions.assertEquals(REMOVING_QUESTION, result);
    }

    @Test
    void shouldReturnAllQuestiongs() {
        Collection<Question> results = questionService.getAll().stream().toList();
        Assertions.assertEquals(ALL_QUESTIONS, results);
    }

}
