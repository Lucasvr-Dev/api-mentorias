package com.exerciciogpt3.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "mentoria")
public class Mentoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo")
    @NotBlank
    private String titulo;

    @Column(name = "especialidade")
    @NotBlank
    private String especialidade;

    @Future
    @NotNull
    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario mentor;
}
