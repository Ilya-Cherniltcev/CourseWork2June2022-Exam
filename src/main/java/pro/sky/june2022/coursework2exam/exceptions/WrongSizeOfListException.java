package pro.sky.june2022.coursework2exam.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// =====   ошибка 400 Bad Request =======
@ResponseStatus(HttpStatus.BAD_REQUEST)

public class WrongSizeOfListException extends RuntimeException {
    public WrongSizeOfListException(String alert) {
    }
}
