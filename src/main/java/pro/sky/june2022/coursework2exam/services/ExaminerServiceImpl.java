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
    int countJavaQuestions;
    int countMathQuestions;

    public ExaminerServiceImpl(@Qualifier("javaQuestionService") QuestionService javaQuestionService,
                               @Qualifier("mathQuestionService") QuestionService mathQuestionService) {
        this.javaQuestionService = javaQuestionService;
        this.mathQuestionService = mathQuestionService;
    }

    @Override
    public Collection<Question> getQuestion(int amount) {
        validateNull(amount);
        int javaAllQuestions = javaQuestionService.getAll().size();
        int mathAllQuestions = mathQuestionService.getAll().size();
        int allQuestions = javaAllQuestions + mathAllQuestions;
        int leftQuestionsJava =javaAllQuestions - countJavaQuestions;
        int leftQuestionsMath =mathAllQuestions - countMathQuestions;
        if (allQuestions < amount) {
            throw new WrongSizeOfListException("Too many questions");
        }

        Random random = new Random();
        int randomNumbersQuestions = 0;
        int javaNum = 0;
        int mathNum = 0;
        while (randomNumbersQuestions < amount) {
            // --- определяем случайным образом количество вопросов из java
            javaNum = random.nextInt(leftQuestionsJava);
            // --- путем остатка находим количество вопросов math
            // --- и сравниваем его с числом оставшихся вопросов
            // --- При необходимости, определяем их кол-во случайным образом
            mathNum = amount - javaNum;
            if (mathNum > leftQuestionsMath) {
                mathNum = random.nextInt(leftQuestionsMath);
                javaNum = amount - mathNum;
            }
            if (javaNum > leftQuestionsJava) {
                javaNum = random.nextInt(leftQuestionsJava);
            }
            randomNumbersQuestions = javaNum + mathNum;
        }
        Collection<Question> tempSet = new HashSet<>();
        Collection<Question> tempSetMath = new HashSet<>();
        if (javaNum != 0) {
            tempSet = getRandomCollection(javaQuestionService, javaNum);
            countJavaQuestions += tempSet.size();
        }
        if (mathNum != 0) {
            tempSetMath = getRandomCollection(mathQuestionService, mathNum);
            countMathQuestions += tempSetMath.size();
        }
        tempSet.addAll(tempSetMath);
        questionSet.addAll(tempSet);
        return tempSet;
    }

    // -----------
    private Collection<Question> getRandomCollection (QuestionService qService, int amount) {
        int numbersOfQuestions = 0;
        Collection<Question> tempS = new HashSet<>();
        Question tempQuestion;

        while (numbersOfQuestions < amount) {
            tempQuestion = qService.getRandomQuestion();
            if (tempS.add(tempQuestion)) {
                numbersOfQuestions++;
            }
        }
        return tempS;
    }
    // -------------------------------------------------------
    private void validateNull(int inputString) {
        if (inputString < 1) {
            throw new WrongRequestOfQuestionsException("The number must be more than 0");
        }
    }
}
