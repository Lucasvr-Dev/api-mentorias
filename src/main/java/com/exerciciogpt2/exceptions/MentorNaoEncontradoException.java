package com.exerciciogpt2.exceptions;

public class MentorNaoEncontradoException extends RuntimeException {
    public MentorNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
