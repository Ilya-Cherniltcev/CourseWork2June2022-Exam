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
import java.util.List;

import static pro.sky.june2022.coursework2exam.QuestionServiceTestConstants.*;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceTests {
    @Mock
    private JavaQuestionService questionService;
    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @Test
    public void shouldReturnQuestions() {
     //   questionService.add(ADDING_QUESTION_QUESTION, ADDING_QUESTION_ANSWER);
        Mockito.when(questionService.getAll())
                .thenReturn(ALL_QUESTIONS);
        Mockito.when(examinerService.getQuestion(1))
                .thenReturn(EXAMPLE_COLLECTION);

        Collection<Question> actualCollection = examinerService.getQuestion(1);
     //  Question actual = actualCollection.get(0);
        Assertions.assertEquals(EXAMPLE_COLLECTION, actualCollection);
    }
}
