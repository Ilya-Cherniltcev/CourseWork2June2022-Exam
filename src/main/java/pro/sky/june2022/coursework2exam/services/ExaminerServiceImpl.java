package pro.sky.june2022.coursework2exam.services;

import org.springframework.stereotype.Service;
import pro.sky.june2022.coursework2exam.data.Question;
import pro.sky.june2022.coursework2exam.exceptions.WrongRequestOfQuestionsException;
import pro.sky.june2022.coursework2exam.exceptions.WrongSizeOfListException;
import pro.sky.june2022.coursework2exam.interfaces.ExaminerService;
import pro.sky.june2022.coursework2exam.interfaces.QuestionService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    QuestionService questionService;
    Set<Question> questionSet = new HashSet<>();
    int count;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestion(int amount) {
        validateNull(amount);
        int questNumbers = questionService.getAll().size();
        int leftNumbers = questNumbers - count;
        if (leftNumbers < amount) {
            throw new WrongSizeOfListException("Too many questions");
        }
        int numbersOfQuestions = 0;
        Set<Question> tempSet = new HashSet<>();
        Question tempQuestion;
        while (numbersOfQuestions < amount) {
            tempQuestion = questionService.getRandomQuestion();
            if (!questionSet.isEmpty() && questionSet.contains(tempQuestion)) {
                continue;
            }
            questionSet.add(tempQuestion);
            tempSet.add(tempQuestion);
            numbersOfQuestions++;
        }
        count += amount;
        return tempSet;
    }

    // -------------------------------------------------------
    private void validateNull(int inputString) {
        if (inputString < 1) {
            throw new WrongRequestOfQuestionsException("The number must be more than 0");
        }
    }
}
