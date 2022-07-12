package pro.sky.june2022.coursework2exam.services;

import org.springframework.beans.factory.annotation.Qualifier;
import pro.sky.june2022.coursework2exam.data.Question;
import pro.sky.june2022.coursework2exam.exceptions.WrongRequestOfQuestionsException;
import pro.sky.june2022.coursework2exam.interfaces.QuestionRepository;
import pro.sky.june2022.coursework2exam.interfaces.QuestionService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    private final QuestionRepository questionRepository;

    public JavaQuestionService(@Qualifier("javaQuestionRepository") QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question add(String question, String answer) {
        validateNull(question);
        validateNull(answer);
        Question tempQuestionObj = new Question(question, answer);
        questionRepository.add(tempQuestionObj);
        return tempQuestionObj;
    }

    @Override
    public Question remove(String question, String answer) {
        validateNull(question);
        validateNull(answer);
        Question tempQuestionObj = new Question(question, answer);
        questionRepository.remove(tempQuestionObj);
        return tempQuestionObj;
    }

    @Override
    public Collection<Question> getAll() {
        return questionRepository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        Random random = new Random();
        Collection<Question> tempCollection = questionRepository.getAll();
        return tempCollection.stream()
                .toList()
                .get(random.nextInt(tempCollection.size()));
    }

    // -------------------------------------------------------
    private void validateNull(String inputString) {
        if (inputString == null) {
            throw new WrongRequestOfQuestionsException("String is null");
        }
    }
}
