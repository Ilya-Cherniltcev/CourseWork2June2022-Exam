package pro.sky.june2022.coursework2exam.controllers;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import pro.sky.june2022.coursework2exam.data.Question;
import pro.sky.june2022.coursework2exam.interfaces.QuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(path = "/exam/java")

public class JavaQuestionController {
    private final QuestionService questionService;

    public JavaQuestionController(@Qualifier("javaQuestionService") QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public Collection<Question> getAllQuestions() {
        return questionService.getAll();
    }

    @GetMapping(path = "/add")
    public Question addQuestionAndAnswer(@RequestParam("question")
                                                 String questionText, @RequestParam("answer") String answer) {
        return questionService.add(questionText, answer);
    }

    @GetMapping(path = "/remove")
    public Question removeQuestionAndAnswer(@RequestParam("question") String questionText,
                                            @RequestParam("answer") String answer) {
        return questionService.remove(questionText, answer);
    }
}

