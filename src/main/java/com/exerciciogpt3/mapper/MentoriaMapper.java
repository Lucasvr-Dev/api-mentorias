package com.exerciciogpt3.mapper;

import org.springframework.stereotype.Component;

import com.exerciciogpt3.dto.MentoriaResponse;
import com.exerciciogpt3.model.Mentoria;

@Component
public class MentoriaMapper {
    public MentoriaResponse toResponse(Mentoria mentoria) {
        return new MentoriaResponse(
                mentoria.getId(),
                mentoria.getTitulo(),
                mentoria.getEspecialidade(),
                mentoria.getDataHora(),
                mentoria.getMentor().getNome());

    }
}
