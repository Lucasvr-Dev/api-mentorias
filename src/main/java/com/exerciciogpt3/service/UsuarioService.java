package com.exerciciogpt3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exerciciogpt3.enums.TipoDeUsuario;
import com.exerciciogpt3.exceptions.UsuarioNaoEncontradoException;
import com.exerciciogpt3.model.Usuario;
import com.exerciciogpt3.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario registrarUsuario(String nome, String email, TipoDeUsuario tipo) {

        Usuario usuario = new Usuario();

        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setTipo(tipo);

        return usuarioRepository.save(usuario);

    }

    public List<Usuario> buscarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado"));
    }

    public void deletarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new UsuarioNaoEncontradoException("Usuário não existe ou foi deletado!");
        }
        usuarioRepository.deleteById(id);
    }

}
