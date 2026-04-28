package com.exerciciogpt3.dto;

import com.exerciciogpt3.enums.TipoDeUsuario;

import lombok.Data;

@Data
public class UsuarioRequest {

    private String nome;
    private String email;
    private TipoDeUsuario tipo;

}
