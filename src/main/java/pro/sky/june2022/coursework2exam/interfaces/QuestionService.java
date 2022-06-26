package pro.sky.june2022.coursework2exam.interfaces;

import pro.sky.june2022.coursework2exam.data.Question;

import java.util.Collection;

public interface QuestionService {


    Question add(String question, String answer);

    Question find(String question, String answer);

    Question add(Question question);

    Question remove(Question question);

    Collection<Question> getAll();

    Question getRandomQuestion();

    int getSizeOfMap();
}
