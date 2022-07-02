package pro.sky.june2022.coursework2exam;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.june2022.coursework2exam.data.Question;
import pro.sky.june2022.coursework2exam.exceptions.WrongRequestOfQuestionsException;
import pro.sky.june2022.coursework2exam.interfaces.QuestionRepository;
import pro.sky.june2022.coursework2exam.services.JavaQuestionService;

import java.util.Collection;

import static pro.sky.june2022.coursework2exam.JavaQuestionServiceTestConstants.*;

@ExtendWith(MockitoExtension.class)
class JavaQuestionServiceTests {
    @Mock
    private QuestionRepository questionRepository;
    @InjectMocks
    private JavaQuestionService questionService;

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
        Mockito.when(questionRepository.add(ADDING_QUESTION))
                .thenThrow(new WrongRequestOfQuestionsException("Alert"));
        Assertions.assertThrows(WrongRequestOfQuestionsException.class,
                () -> questionService.add(EXAMPLE_Q5, EXAMPLE_A5));
    }

    @Test
    void shouldReturnWriteAddingQuestiongIfStrings() {
        Mockito.when(questionRepository.add(ADDING_QUESTION))
                .thenReturn(ADDING_QUESTION);
        Question result = questionService.add(EXAMPLE_Q5, EXAMPLE_A5);
        Assertions.assertEquals(ADDING_QUESTION, result);
    }

    @Test
    void shouldReturnWriteRemovingQuestiong() {
        Mockito.when(questionRepository.remove(REMOVING_QUESTION))
                .thenReturn(REMOVING_QUESTION);
        Question result = questionService.remove(EXAMPLE_Q1, EXAMPLE_A1);
        Assertions.assertEquals(REMOVING_QUESTION, result);
    }

    @Test
    void shouldReturnAllQuestiongs() {
        Mockito.when(questionRepository.getAll())
                .thenReturn(ALL_QUESTIONS_SET);
        Assertions.assertEquals(ALL_QUESTIONS_SET.size(), questionService.getAll().size());
        Collection<Question> results = questionService.getAll();
        Assertions.assertEquals(ALL_QUESTIONS_SET, results);
    }
}
