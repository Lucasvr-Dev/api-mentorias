package com.exerciciogpt3.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MentoriaRequest {

    private String titulo;
    private String especialidade;
    private LocalDateTime dataHora;

    @NotNull(message = "Id do mentor não pode ser nulo!")
    private Long mentorId;

}
