package com.exerciciogpt2.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exerciciogpt2.enums.TipoDeUsuario;
import com.exerciciogpt2.exceptions.ApenasMentorException;
import com.exerciciogpt2.exceptions.MentorNaoEncontradoException;
import com.exerciciogpt2.model.Mentoria;
import com.exerciciogpt2.model.Usuario;
import com.exerciciogpt2.repository.MentoriaRepository;
import com.exerciciogpt2.repository.UsuarioRepository;

@Service
public class MentoriaService {

    @Autowired
    public MentoriaRepository mentoriaRepository;
    @Autowired
    public UsuarioRepository usuarioRepository;

    public Mentoria registrarMentoria(String titulo, LocalDateTime data, String especialidade, Long mentorId) {

        Usuario mentor = usuarioRepository.findById(mentorId)
                .orElseThrow(() -> new MentorNaoEncontradoException("Mentor não encontrado"));

        verificarMentor(mentor);

        Mentoria mentoria = new Mentoria();

        mentoria.setTitulo(titulo);
        mentoria.setDataHora(data);
        mentoria.setEspecialidade(especialidade);
        mentoria.setMentor(mentor);

        return mentoriaRepository.save(mentoria);

    }

    public List<Mentoria> buscarMentorias() {
        return mentoriaRepository.findAll();
    }

    public Mentoria buscarMentoriaPorId(Long id) {
        return mentoriaRepository.findById(id).orElseThrow(() -> new RuntimeException("Mentoria não encontrada"));
    }

    public List<Mentoria> buscarPorEspecialidade(String especialidade) {
        return mentoriaRepository.findByEspecialidade(especialidade);
    }

    public List<Mentoria> buscarPorNome(String nome) {
        return mentoriaRepository.findByMentorNome(nome);
    }

    public List<Mentoria> buscaFuturas() {
        return mentoriaRepository.findByDataHoraAfter(LocalDateTime.now());
    }

    public void deletarMentoriaPorId(Long id) {
        mentoriaRepository.deleteById(id);
    }

    private void verificarMentor(Usuario usuario) {
        if (usuario.getTipo() != TipoDeUsuario.Mentor) {
            throw new ApenasMentorException("Apenas mentores podem criar mentorias!");
        }
    }

}
