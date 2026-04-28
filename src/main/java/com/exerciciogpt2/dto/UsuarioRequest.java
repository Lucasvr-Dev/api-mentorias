package com.exerciciogpt2.dto;

import com.exerciciogpt2.enums.TipoDeUsuario;

import lombok.Data;

@Data
public class UsuarioRequest {

    private String nome;
    private String email;
    private TipoDeUsuario tipo;

}
