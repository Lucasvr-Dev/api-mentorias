package com.exerciciogpt3.mapper;

import org.springframework.stereotype.Component;

import com.exerciciogpt3.dto.UsuarioResponse;
import com.exerciciogpt3.model.Usuario;

@Component
public class UsuarioMapper {
    public UsuarioResponse toResponse(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTipo().name());

    }
}
