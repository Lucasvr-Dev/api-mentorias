package com.exerciciogpt2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exerciciogpt2.enums.TipoDeUsuario;
import com.exerciciogpt2.exceptions.JaExisteException;
import com.exerciciogpt2.model.Usuario;
import com.exerciciogpt2.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    public UsuarioRepository usuarioRepository;

    public Usuario criarUsuario(String nome, String email, TipoDeUsuario tipo) {

        Usuario usuario = new Usuario();

        usuario.setNome(nome);
        verificadorDeEmail(email);
        usuario.setEmail(email);

        usuario.setTipo(tipo);

        return usuarioRepository.save(usuario);
    }

    public List<Usuario> buscarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id) {

        return usuarioRepository.findById(id);

    }

    public void deletarUsuarioPorId(Long id) {
        usuarioRepository.deleteById(id);
    }

    private void verificadorDeEmail(String email) {
        if (usuarioRepository.existsByEmail(email)) {
            throw new JaExisteException("O email inserido já existe!");
        }
    }

}
