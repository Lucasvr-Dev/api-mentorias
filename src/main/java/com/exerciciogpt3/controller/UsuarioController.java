package com.exerciciogpt3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exerciciogpt3.dto.UsuarioRequest;
import com.exerciciogpt3.dto.UsuarioResponse;
import com.exerciciogpt3.mapper.UsuarioMapper;
import com.exerciciogpt3.model.Usuario;
import com.exerciciogpt3.service.UsuarioService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    UsuarioMapper usuarioMapper;

    @PostMapping
    public ResponseEntity<UsuarioResponse> criarUsuario(@RequestBody @Valid UsuarioRequest request) {

        Usuario usuario = usuarioService.registrarUsuario(
                request.getNome(),
                request.getEmail(),
                request.getTipo());

        UsuarioResponse response = usuarioMapper.toResponse(usuario);

        return ResponseEntity.ok(response);

    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuario = usuarioService.buscarUsuarios();
        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> listarPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.buscarPorId(id);
        UsuarioResponse response = usuarioMapper.toResponse(usuario);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuarioPorId(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);

        return ResponseEntity.noContent().build();
    }

}
