package com.exerciciogpt2.dto;

import java.time.LocalDateTime;

import com.exerciciogpt2.model.Usuario;

import lombok.Data;

@Data
public class MentoriaRequest {

    private String titulo;
    private String especialidade;
    private LocalDateTime data;
    private Long mentorId;

}
