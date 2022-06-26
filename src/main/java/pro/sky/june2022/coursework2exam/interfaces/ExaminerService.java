package pro.sky.june2022.coursework2exam.interfaces;

import pro.sky.june2022.coursework2exam.data.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestion(int amount);
}
