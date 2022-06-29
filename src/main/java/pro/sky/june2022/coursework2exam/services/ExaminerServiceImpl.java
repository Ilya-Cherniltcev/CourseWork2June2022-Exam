package pro.sky.june2022.coursework2exam.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pro.sky.june2022.coursework2exam.data.Question;
import pro.sky.june2022.coursework2exam.exceptions.WrongRequestOfQuestionsException;
import pro.sky.june2022.coursework2exam.exceptions.WrongSizeOfListException;
import pro.sky.june2022.coursework2exam.interfaces.ExaminerService;
import pro.sky.june2022.coursework2exam.interfaces.QuestionService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService javaQuestionService;
    private final QuestionService mathQuestionService;
    Set<Question> questionSet = new HashSet<>();
    int count;

    public ExaminerServiceImpl(@Qualifier("javaQuestionService") QuestionService javaQuestionService,
                               @Qualifier("mathQuestionService") QuestionService mathQuestionService) {
        this.javaQuestionService = javaQuestionService;
        this.mathQuestionService = mathQuestionService;
    }

    @Override
    public Collection<Question> getQuestion(int amount) {
        validateNull(amount);
        int allQuestions = javaQuestionService.getAll().size()
                + mathQuestionService.getAll().size();
        int leftNumbers = allQuestions - count;
        if (leftNumbers < amount) {
            throw new WrongSizeOfListException("Too many questions");
        }
        // --- определяем случайным образом количество вопросов из java
        Random random = new Random();
        int javaNum = random.nextInt(leftNumbers);
        // --- оставшееся количество вопросов выбираем из math
        int mathNum = leftNumbers - javaNum;
        Collection<Question> tempSet = new HashSet<>();
        Collection<Question> tempSet1 = new HashSet<>();
        if (javaNum != 0) {
            tempSet = getRandomCollection(javaQuestionService, javaNum);
        }
        if (mathNum != 0) {
            tempSet1 = getRandomCollection(mathQuestionService, mathNum);
        }
        tempSet.addAll(tempSet1);

        return tempSet;
    }

    // -----------
    private Collection<Question> getRandomCollection (QuestionService qService, int amount) {
        int numbersOfQuestions = 0;
        Collection<Question> tempSet = new HashSet<>();
        Question tempQuestion;

        while (numbersOfQuestions < amount) {
            tempQuestion = qService.getRandomQuestion();
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
