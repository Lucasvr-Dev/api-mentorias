package com.exerciciogpt3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exerciciogpt3.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
