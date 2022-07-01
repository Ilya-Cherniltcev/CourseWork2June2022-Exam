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
    int countJavaQuestions; // счетчик запрошенных вопросов по java
    int countMathQuestions; // счетчик запрошенных вопросов по math

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
        // ====== число "оставшихся" вопросов по каждой теме
        int leftQuestionsJava = javaAllQuestions - countJavaQuestions;
        int leftQuestionsMath = mathAllQuestions - countMathQuestions;
        if (allQuestions < amount) {
            throw new WrongSizeOfListException("Too many questions");
        }
        int javaNum = 0;
        int mathNum = 0;
        // ======  если запрошенное число вопросов равно общему кол-ву
        // ======  оставшихся вопросов, то число отобранных вопросов
        // ======  по каждой теме равно оставшемуся числу вопросов по данной теме
        if (amount == (leftQuestionsJava + leftQuestionsMath)) {
            javaNum = leftQuestionsJava;
            mathNum = leftQuestionsMath;
        }
        // ======   Иначе - вызываем метод, генерирующий случайное число вопросов
        if (leftQuestionsJava < leftQuestionsMath) {
            javaNum = getNumbersOfQuestions(leftQuestionsJava, leftQuestionsMath, amount);
            mathNum = amount - javaNum;
        } else {
            mathNum = getNumbersOfQuestions(leftQuestionsMath, leftQuestionsJava, amount);
            javaNum = amount - mathNum;
        }

        Collection<Question> tempSet = new HashSet<>();
        Collection<Question> tempSetMath = new HashSet<>();
        if (javaNum != 0) {
            tempSet = getRandomCollection(javaQuestionService, javaNum);
            countJavaQuestions += javaNum;
        }
        if (mathNum != 0) {
            tempSetMath = getRandomCollection(mathQuestionService, mathNum);
            countMathQuestions += mathNum;
        }
        tempSet.addAll(tempSetMath);
        questionSet.addAll(tempSet);
        return tempSet;
    }

    // =========  Определяем случайное число запрашиваемых вопросов по теме
    private int getNumbersOfQuestions(int min, int max, int amount) {
        Random random = new Random();
        int randomNumbersQuestions = 0;
        while ((amount - randomNumbersQuestions) > max) {
            randomNumbersQuestions = random.nextInt(min) + 1;
        }
        return randomNumbersQuestions;
    }

    // -----------  добавляем в коллекцию случайные вопросы
    private Collection<Question> getRandomCollection(QuestionService qService, int countQuestions) {
        int numbersOfQuestions = 0;
        Collection<Question> tempS = new HashSet<>();
        Question tempQuestion;
        while (numbersOfQuestions < countQuestions) {
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
