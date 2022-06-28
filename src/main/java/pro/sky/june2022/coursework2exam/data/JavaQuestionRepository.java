package pro.sky.june2022.coursework2exam.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import pro.sky.june2022.coursework2exam.exceptions.WrongRequestOfQuestionsException;
import pro.sky.june2022.coursework2exam.exceptions.WrongSizeOfListException;
import pro.sky.june2022.coursework2exam.interfaces.QuestionRepository;
import pro.sky.june2022.coursework2exam.interfaces.QuestionService;

import java.util.*;

@Repository
public class JavaQuestionRepository implements QuestionRepository {
    private final Set<Question> questions = new HashSet<>();

    @Override
    public Question add(Question question) {
        if (questions.add(question)) {
            return question;
        }
        throw new WrongRequestOfQuestionsException("This question/answer is already exist");
    }

    @Override
    public Question remove(Question question) {
        if (questions.remove(question)) {
            return question;
        }
        throw new WrongRequestOfQuestionsException("The question doesn't exist");
    }

    @Override
    public Collection<Question> getAll() {
        return questions;
    }

}
