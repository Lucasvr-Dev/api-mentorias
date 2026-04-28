package com.exerciciogpt3.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.exerciciogpt3.dto.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApenasMentorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleApenasMentor(ApenasMentorException ex, HttpServletRequest request) {

        ErrorResponse erro = new ErrorResponse(
                LocalDateTime.now(),
                409,
                "Conflict",
                ex.getMessage(),
                request.getRequestURI());

        return ResponseEntity.badRequest().body(erro);
    }

    @ExceptionHandler(MentoriaNaoEncontradaException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleMentoriaNaoEncontrada(MentoriaNaoEncontradaException ex,
            HttpServletRequest request) {

        ErrorResponse erro = new ErrorResponse(
                LocalDateTime.now(),
                404,
                "Not Found",
                ex.getMessage(),
                request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);

    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleUsuarioNaoEncontrado(UsuarioNaoEncontradoException ex,
            HttpServletRequest request) {

        ErrorResponse erro = new ErrorResponse(
                LocalDateTime.now(),
                404,
                "Not Found",
                ex.getMessage(),
                request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

}
