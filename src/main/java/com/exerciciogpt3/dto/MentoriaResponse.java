package com.exerciciogpt3.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MentoriaResponse {

    private Long id;
    private String titulo;
    private String especialidade;
    private LocalDateTime dataHora;
    private String nomeMentor;

}
