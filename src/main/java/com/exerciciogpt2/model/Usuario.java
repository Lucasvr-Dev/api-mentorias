package com.exerciciogpt2.model;

import java.util.List;

import com.exerciciogpt2.enums.TipoDeUsuario;
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
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    @NotBlank
    private String nome;

    @Column(name = "email")
    @NotBlank
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_usuario")
    private TipoDeUsuario tipo;

    @OneToMany(mappedBy = "mentor")
    @JsonIgnore
    private List<Mentoria> mentorias;

}
