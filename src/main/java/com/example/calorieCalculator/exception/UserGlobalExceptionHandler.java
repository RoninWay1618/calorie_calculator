package com.example.calorieCalculator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserGlobalExceptionHandler {

    @ExceptionHandler()
    public ResponseEntity<UserDoesNotExist> handlerException(
            UserException ex){
        UserDoesNotExist userDoesNotExist = new UserDoesNotExist();
        userDoesNotExist.setInfo(ex.getMessage());
        return new ResponseEntity<>(userDoesNotExist, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler()
    public ResponseEntity<UserDoesNotExist> handlerException(
            Exception ex){
        UserDoesNotExist userDoesNotExist = new UserDoesNotExist();
        userDoesNotExist.setInfo(ex.getMessage());

        return new ResponseEntity<>(userDoesNotExist, HttpStatus.NOT_FOUND);
    }
}
