package com.exerciciogpt2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(JaExisteException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleJaExiste(JaExisteException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(ApenasMentorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleApenasMentor(ApenasMentorException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(MentorNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleMentorNaoEncontrado(MentorNaoEncontradoException ex) {
        return ex.getMessage();
    }

}
