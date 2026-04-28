package com.exerciciogpt3.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exerciciogpt3.enums.TipoDeUsuario;
import com.exerciciogpt3.exceptions.ApenasMentorException;
import com.exerciciogpt3.exceptions.MentoriaNaoEncontradaException;
import com.exerciciogpt3.exceptions.UsuarioNaoEncontradoException;
import com.exerciciogpt3.model.Mentoria;
import com.exerciciogpt3.model.Usuario;
import com.exerciciogpt3.repository.MentoriaRepository;
import com.exerciciogpt3.repository.UsuarioRepository;

@Service
public class MentoriaService {

    @Autowired
    MentoriaRepository mentoriaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    public Mentoria registrarMentoria(String titulo, String especialidade, LocalDateTime dataHora,
            Long mentorId) {

        Usuario mentor = usuarioRepository.findById(mentorId)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Mentor não encontrado"));

        verificarMentor(mentor);

        Mentoria mentoria = new Mentoria();

        mentoria.setTitulo(titulo);
        mentoria.setEspecialidade(especialidade);
        mentoria.setDataHora(dataHora);
        mentoria.setMentor(mentor);

        return mentoriaRepository.save(mentoria);
    }

    public List<Mentoria> buscarTodas() {
        return mentoriaRepository.findAll();
    }

    public List<Mentoria> buscarPorNomeMentor(String nome) {
        return mentoriaRepository.findByMentorNome(nome);
    }

    public List<Mentoria> buscarPorEspecialidade(String especialidade) {
        return mentoriaRepository.findByEspecialidade(especialidade);
    }

    public List<Mentoria> buscarFuturas() {
        return mentoriaRepository.findByDataHoraAfter(LocalDateTime.now());
    }

    public List<Mentoria> buscarPassadas() {
        return mentoriaRepository.findByDataHoraBefore(LocalDateTime.now());
    }

    public Mentoria buscarPorId(Long id) {
        return mentoriaRepository.findById(id)
                .orElseThrow(() -> new MentoriaNaoEncontradaException("Mentoria não encontrada!"));
    }

    public void deletarMentoria(Long id) {
        if (!mentoriaRepository.existsById(id)) {
            throw new MentoriaNaoEncontradaException("Mentoria não encontrada!");
        }

        mentoriaRepository.deleteById(id);
    }

    private void verificarMentor(Usuario mentor) {
        if (!mentor.getTipo().equals(TipoDeUsuario.MENTOR)) {
            throw new ApenasMentorException("Apenas usuários do tipo mentor pode criar mentorias!");
        }
    }

}
