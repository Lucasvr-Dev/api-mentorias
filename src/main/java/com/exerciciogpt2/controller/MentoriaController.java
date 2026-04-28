package com.exerciciogpt2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exerciciogpt2.dto.MentoriaRequest;
import com.exerciciogpt2.model.Mentoria;
import com.exerciciogpt2.service.MentoriaService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/mentorias")
public class MentoriaController {

    @Autowired
    public MentoriaService mentoriaService;

    @PostMapping
    public Mentoria criarMentoria(@RequestBody @Valid MentoriaRequest request) {

        return mentoriaService.registrarMentoria(
                request.getTitulo(),
                request.getData(),
                request.getEspecialidade(),
                request.getMentorId());
    }

    @GetMapping
    public List<Mentoria> listarMentorias(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String especialidade,
            @RequestParam(required = false) Boolean futuras) {

        if (nome != null) {
            return mentoriaService.buscarPorNome(nome);
        } else if (especialidade != null) {
            return mentoriaService.buscarPorEspecialidade(especialidade);
        } else if (Boolean.TRUE.equals(futuras)) {
            return mentoriaService.buscaFuturas();
        }

        return mentoriaService.buscarMentorias();
    }

    @GetMapping("/{id}")
    public Mentoria listarPorId(@PathVariable Long id) {
        return mentoriaService.buscarMentoriaPorId(id);
    }

    @DeleteMapping("/{id}")
    public void deletarMentoria(@PathVariable Long id) {
        mentoriaService.deletarMentoriaPorId(id);
    }
}
