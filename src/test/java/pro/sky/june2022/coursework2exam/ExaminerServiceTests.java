package pro.sky.june2022.coursework2exam;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.june2022.coursework2exam.data.Question;
import pro.sky.june2022.coursework2exam.services.ExaminerServiceImpl;
import pro.sky.june2022.coursework2exam.services.JavaQuestionService;

import java.util.Collection;

import static pro.sky.june2022.coursework2exam.JavaQuestionServiceTestConstants.*;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceTests {
    @Mock
    private JavaQuestionService questionService;
    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @Test
    public void shouldReturnQuestions() {
        Mockito.when(questionService.getRandomQuestion())
                .thenReturn(EXAMPLE_QUESTION);
        Mockito.when(questionService.getAll())
                .thenReturn(EXAMPLE_COLLECTION);

        Collection<Question> actualCollection = examinerService.getQuestion(1);
        Assertions.assertEquals(EXAMPLE_COLLECTION, actualCollection);
    }
}
