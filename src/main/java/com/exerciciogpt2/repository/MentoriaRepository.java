package com.exerciciogpt2.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exerciciogpt2.model.Mentoria;

public interface MentoriaRepository extends JpaRepository<Mentoria, Long> {
    public List<Mentoria> findByEspecialidade(String especialidade);

    public List<Mentoria> findByMentorNome(String nome);

    public List<Mentoria> findByDataHoraAfter(LocalDateTime now);
}
