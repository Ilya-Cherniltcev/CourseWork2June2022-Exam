package pro.sky.june2022.coursework2exam.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pro.sky.june2022.coursework2exam.data.Question;
import pro.sky.june2022.coursework2exam.exceptions.WrongRequestOfQuestionsException;
import pro.sky.june2022.coursework2exam.exceptions.WrongSizeOfListException;
import pro.sky.june2022.coursework2exam.interfaces.ExaminerService;
import pro.sky.june2022.coursework2exam.interfaces.QuestionService;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService javaQuestionService;
    private final QuestionService mathQuestionService;
    Set<Question> questionSet = new HashSet<>();
    private int countQuestions; // счетчик запрошенных вопросов
    private Random random;

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

        // ====== число "оставшихся" вопросов
        int leftQuestions = allQuestions - countQuestions;
        if (leftQuestions < amount) {
            throw new WrongSizeOfListException("Too many questions");
        }

        // ******* объединяем 2 сервиса: java и math *******
        List<QuestionService> unionService = List.of(javaQuestionService, mathQuestionService);
        Collection<Question> tempSet;
        tempSet = getRandomCollection(unionService, amount);
        countQuestions += tempSet.size();
        questionSet.addAll(tempSet);
        return tempSet;
    }

    // -----------  добавляем в коллекцию случайные вопросы
    private Collection<Question> getRandomCollection(List<QuestionService> qService, int countQuestions) {
        int numbersOfQuestions = 0;
        Collection<Question> tempS = new HashSet<>();
        random = new Random();
        Question tempQuestion;
        while (numbersOfQuestions < countQuestions) {
            // ---- рандомно выбираем сервис: java или math -----
            QuestionService tempService = qService.get(random.nextInt(qService.size()));
            // ---- у выбранного сервиса запускаем метод рандомного поиска вопроса -----
            tempQuestion = tempService.getRandomQuestion();
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
