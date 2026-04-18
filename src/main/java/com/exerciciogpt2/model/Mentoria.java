package com.exerciciogpt2.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Table(name = "mentoria")
@Entity
public class Mentoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "titulo")
    private String titulo;

    @Future(message = "A data da mentoria deve ser no futuro!")
    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    @NotBlank
    @Column(name = "especialidade")
    private String especialidade;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario mentor;
}
