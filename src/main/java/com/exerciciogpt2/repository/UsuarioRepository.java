package com.exerciciogpt2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exerciciogpt2.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Boolean existsByEmail(String email);

}
