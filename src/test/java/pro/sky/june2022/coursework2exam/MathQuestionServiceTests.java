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
import pro.sky.june2022.coursework2exam.services.MathQuestionService;

import java.util.Collection;

import static pro.sky.june2022.coursework2exam.MathQuestionServiceTestConstants.*;

@ExtendWith(MockitoExtension.class)
class MathQuestionServiceTests {
    @Mock
    private QuestionRepository questionRepository;
    @InjectMocks
    private MathQuestionService questionService;

    @Test
    void shouldReturnExceptionIfTheQuestionsStringIsNull() {
        Assertions.assertThrows(WrongRequestOfQuestionsException.class,
                () -> questionService.add(MATH_EXAMPLE_NULL, "100"));
    }

    @Test
    void shouldReturnExceptionIfTheAnswersStringIsNull() {
        Assertions.assertThrows(WrongRequestOfQuestionsException.class,
                () -> questionService.add("100", MATH_EXAMPLE_NULL));
    }

    @Test
    void shouldReturnExceptionIfTheQuestionIsExist() {
        Mockito.when(questionRepository.add(MATH_ADDING_QUESTION))
                .thenThrow(new WrongRequestOfQuestionsException("Alert"));
        Assertions.assertThrows(WrongRequestOfQuestionsException.class,
                () -> questionService.add(MATH_EXAMPLE_Q5, MATH_EXAMPLE_A5));
    }

    @Test
    void shouldReturnWriteAddingQuestiongIfStrings() {
        Mockito.when(questionRepository.add(MATH_ADDING_QUESTION))
                .thenReturn(MATH_ADDING_QUESTION);
        Question result = questionService.add(MATH_EXAMPLE_Q5, MATH_EXAMPLE_A5);
        Assertions.assertEquals(MATH_ADDING_QUESTION, result);
    }

    @Test
    void shouldReturnWriteRemovingQuestiong() {
        Mockito.when(questionRepository.remove(MATH_REMOVING_QUESTION))
                .thenReturn(MATH_REMOVING_QUESTION);
        Question result = questionService.remove(MATH_EXAMPLE_Q1, MATH_EXAMPLE_A1);
        Assertions.assertEquals(MATH_REMOVING_QUESTION, result);
    }

    @Test
    void shouldReturnAllQuestiongs() {
        Mockito.when(questionRepository.getAll())
                .thenReturn(MATH_ALL_QUESTIONS_SET);
        Collection<Question> results = questionService.getAll();
        Assertions.assertEquals(MATH_ALL_QUESTIONS_SET, results);
    }
}
