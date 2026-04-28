package com.exerciciogpt3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exerciciogpt3.dto.MentoriaRequest;
import com.exerciciogpt3.dto.MentoriaResponse;
import com.exerciciogpt3.mapper.MentoriaMapper;
import com.exerciciogpt3.model.Mentoria;
import com.exerciciogpt3.service.MentoriaService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/mentorias")
@AllArgsConstructor
public class MentoriaController {

    @Autowired
    final MentoriaService mentoriaService;

    @Autowired
    final MentoriaMapper mentoriaMapper;

    @PostMapping
    public ResponseEntity<MentoriaResponse> criarMentoria(@RequestBody MentoriaRequest request) {

        Mentoria mentoria = mentoriaService.registrarMentoria(
                request.getTitulo(),
                request.getEspecialidade(),
                request.getDataHora(),
                request.getMentorId());

        MentoriaResponse response = mentoriaMapper.toResponse(mentoria);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<Mentoria>> listarMentorias(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String especialidade,
            @RequestParam(required = false) Boolean futuras) {

        if (nome != null) {
            List<Mentoria> mentoria = mentoriaService.buscarPorNomeMentor(nome);
            return ResponseEntity.ok(mentoria);
        }

        if (especialidade != null) {
            List<Mentoria> mentoria = mentoriaService.buscarPorEspecialidade(especialidade);
            return ResponseEntity.ok(mentoria);
        }

        if (Boolean.TRUE.equals(futuras)) {
            List<Mentoria> mentoria = mentoriaService.buscarFuturas();
            return ResponseEntity.ok(mentoria);
        }

        if (Boolean.FALSE.equals(futuras)) {
            List<Mentoria> mentoria = mentoriaService.buscarPassadas();
            return ResponseEntity.ok(mentoria);
        }

        List<Mentoria> mentoria = mentoriaService.buscarTodas();
        return ResponseEntity.ok(mentoria);
    }

    @GetMapping("/{id}")
    public MentoriaResponse buscar(@PathVariable Long id) {
        Mentoria mentoria = mentoriaService.buscarPorId(id);
        return mentoriaMapper.toResponse(mentoria);
    }

    @DeleteMapping("/{id}")
    public void deletarMentoriaPorId(@PathVariable Long id) {
        mentoriaService.deletarMentoria(id);
    }

}
