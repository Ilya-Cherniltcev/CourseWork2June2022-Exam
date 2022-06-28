package pro.sky.june2022.coursework2exam.interfaces;

import pro.sky.june2022.coursework2exam.data.Question;

import java.util.Collection;

public interface QuestionRepository {

    Question add(Question question);

    Question remove(Question question);

    Collection<Question> getAll();

}
