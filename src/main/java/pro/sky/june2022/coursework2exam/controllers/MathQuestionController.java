package pro.sky.june2022.coursework2exam.controllers;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.june2022.coursework2exam.data.Question;
import pro.sky.june2022.coursework2exam.interfaces.QuestionService;

import java.util.Collection;

@RestController
@RequestMapping(path = "/exam/java")

public class MathQuestionController {
    private final QuestionService questionService;

    public MathQuestionController(@Qualifier("mathQuestionService") QuestionService questionService) {
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

