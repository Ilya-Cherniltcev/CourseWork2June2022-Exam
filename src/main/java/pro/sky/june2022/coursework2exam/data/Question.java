package pro.sky.june2022.coursework2exam.data;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class Question {
    private final String question;
    private final String answer;

    public Question(String question, String answer) {
        this.question = StringUtils.capitalize(StringUtils.lowerCase(question));
        this.answer = StringUtils.capitalize(StringUtils.lowerCase(answer));
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question1 = (Question) o;
        return Objects.equals(question, question1.question) && Objects.equals(answer, question1.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, answer);
    }

    @Override
    public String toString() {
        return "Вопрос:" + question + ", ответ=" + answer;
    }
}
