package com.exerciciogpt3.model;

import java.util.List;

import com.exerciciogpt3.enums.TipoDeUsuario;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Table(name = "usuario")
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    @NotBlank
    private String nome;

    @Column(name = "email", unique = true)
    @NotBlank
    private String email;

    @Column(name = "tipo")
    @Enumerated(EnumType.STRING)
    private TipoDeUsuario tipo;

    @OneToMany(mappedBy = "mentor")
    @JsonIgnore
    private List<Mentoria> mentoria;

}
