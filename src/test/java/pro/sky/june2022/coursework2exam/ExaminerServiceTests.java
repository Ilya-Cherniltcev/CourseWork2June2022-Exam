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

import java.util.List;

import static pro.sky.june2022.coursework2exam.QuestionServiceTestConstants.ADDING_QUESTION;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceTests {
    @Mock
    private JavaQuestionService questionService;
    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @Test
    public void shouldReturnQuestions() {
        questionService.add(ADDING_QUESTION);
        Mockito.when(questionService.getRandomQuestion())
                .thenReturn(ADDING_QUESTION);
        Mockito.when(questionService.getSizeOfMap())
                .thenReturn(1);
        List<Question> actualCollection = examinerService.getQuestion(1)
                .stream().toList();
        System.out.println(actualCollection);
        Question actual = actualCollection.get(0);
        Assertions.assertEquals(ADDING_QUESTION, actual);
    }
}
