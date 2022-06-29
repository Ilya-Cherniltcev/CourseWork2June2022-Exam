package pro.sky.june2022.coursework2exam.services;

import org.springframework.stereotype.Service;
import pro.sky.june2022.coursework2exam.data.JavaQuestionRepository;
import pro.sky.june2022.coursework2exam.data.MathQuestionRepository;
import pro.sky.june2022.coursework2exam.data.Question;
import pro.sky.june2022.coursework2exam.exceptions.WrongRequestOfQuestionsException;
import pro.sky.june2022.coursework2exam.interfaces.QuestionRepository;
import pro.sky.june2022.coursework2exam.interfaces.QuestionService;

import java.util.Collection;
import java.util.Random;

@Service
public class MathQuestionService implements QuestionService {
    private final QuestionRepository questionRepository = new MathQuestionRepository();


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
