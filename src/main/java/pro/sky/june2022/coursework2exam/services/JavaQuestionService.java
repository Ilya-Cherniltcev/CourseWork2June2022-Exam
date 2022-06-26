package pro.sky.june2022.coursework2exam.services;

import pro.sky.june2022.coursework2exam.data.Question;
import pro.sky.june2022.coursework2exam.exceptions.WrongRequestOfQuestionsException;
import pro.sky.june2022.coursework2exam.exceptions.WrongSizeOfListException;
import pro.sky.june2022.coursework2exam.interfaces.QuestionService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    private final Map<Integer, Question> questions = new HashMap<>();
    private int countQuestions;

    @Override
    public Question add(String question, String answer) {
        validateNull(question);
        validateNull(answer);
        Question tempQuestionObj = new Question(question, answer);
        validateExistQuestion(tempQuestionObj);
        countQuestions++;
        questions.put(countQuestions, tempQuestionObj);
        return tempQuestionObj;
    }

    @Override
    public Question add(Question question) {
        validateExistQuestion(question);
        countQuestions++;
        questions.put(countQuestions, question);
        return question;
    }

    @Override
    public Question find(String question, String answer) {
        validateEmpty();
        validateNull(question);
        validateNull(answer);
        Question tempQuestionObj = new Question(question, answer);
        if (!questions.containsValue(tempQuestionObj)) {
            throw new WrongRequestOfQuestionsException("The question/answer does not exist");
        }
        return tempQuestionObj;
    }

    @Override
    public Question remove(Question question) {
        validateEmpty();
        questions.values().removeIf(value -> value.equals(question));
        countQuestions--;
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return questions.values();
    }

    @Override
    public Question getRandomQuestion() {
        Random random = new Random();
        int randomInt;
        Question tempQuestionObj = null;
        do {
            randomInt = 1 + random.nextInt(countQuestions);
            if (questions.containsKey(randomInt)) {
                tempQuestionObj = questions.get(randomInt);
            }
        }
        while (tempQuestionObj == null);
        return tempQuestionObj;
    }

    // ---------------------------------------------------
    private void validateExistQuestion(Question tempQuestionObj) {
        if (!questions.isEmpty() && questions.containsValue(tempQuestionObj)) {
            throw new WrongRequestOfQuestionsException("This question/answer is already exist");
        }
    }

    // -------------------------------------------------------
    private void validateNull(String inputString) {
        if (inputString == null) {
            throw new WrongRequestOfQuestionsException("String is null");
        }
    }

    // -------------------------------------------------------
    private void validateEmpty() {
        if (countQuestions == 0) {
            throw new WrongSizeOfListException("The list of questions/answers is empty");
        }
    }
    // -------------------------------------------------------

    @Override
    public int getSizeOfMap() {
        return countQuestions;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JavaQuestionService that = (JavaQuestionService) o;
        return countQuestions == that.countQuestions && Objects.equals(questions, that.questions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questions, countQuestions);
    }
}
