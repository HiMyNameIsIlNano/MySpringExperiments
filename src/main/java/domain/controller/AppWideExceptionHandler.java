package domain.controller;

import domain.exceptions.AppWideDuplicateSpitterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppWideExceptionHandler {

    /**
     * The exception handler defined in a Controller Advice is global for the whole application.
     * */
    @ExceptionHandler(value = AppWideDuplicateSpitterException.class)
    public String handleDuplicateSpitter() {
        return "error/appWideDuplicate";
    }

}
