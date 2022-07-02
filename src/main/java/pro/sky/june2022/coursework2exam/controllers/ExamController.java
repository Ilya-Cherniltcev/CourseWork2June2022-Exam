package pro.sky.june2022.coursework2exam.controllers;


import org.springframework.web.bind.annotation.*;
import pro.sky.june2022.coursework2exam.data.Question;
import pro.sky.june2022.coursework2exam.interfaces.ExaminerService;

import java.util.Collection;

@RestController
@RequestMapping(path = "/exam/get")

public class ExamController {
    private final ExaminerService examinerService;

    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping(path = "/{amount}")
    public Collection<Question> getRandomQuestions(@PathVariable int amount) {
        return examinerService.getQuestion(amount);
    }
}

