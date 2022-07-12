package pro.sky.june2022.coursework2exam.interfaces;

import pro.sky.june2022.coursework2exam.data.Question;

import java.util.Collection;

public interface QuestionService {


    Question add(String question, String answer);

    Question remove(String question, String answer);


    Collection<Question> getAll();

    Question getRandomQuestion();

}
