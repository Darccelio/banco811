package com.letscode.banco811.service.impl;

import com.letscode.banco811.dto.UsuarioRequest;
import com.letscode.banco811.dto.UsuarioResponse;

import com.letscode.banco811.model.Usuario;
import com.letscode.banco811.respository.UsuarioRepository;
import com.letscode.banco811.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> getAll(String nome) {
        if(nome != null){
            return usuarioRepository.findByNome(nome);
        }
        else {
            return usuarioRepository.findAll();
        }

//        return nome != null ? usuarioRepository.findByNome(nome) : usuarioRepository.findAll();

    }

    @Override
    public UsuarioResponse create(UsuarioRequest usuarioRequest) {
        Usuario usuario = new Usuario(usuarioRequest);
        usuarioRepository.save(usuario);
        return new UsuarioResponse(usuario);
    }

    @Override
    public UsuarioResponse getById(Integer id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(); // validação sem utilizar o IF. Caso não tenha o usuário, uma exceção é lançada.
        return  new UsuarioResponse(usuario); // converter o tipo de Usuario para UsuarioResponse
    }

    @Override
    public Usuario update(UsuarioRequest usuarioRequest, Integer id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();
        usuario.setNome(usuarioRequest.getNome());
        usuario.setCpf(usuarioRequest.getCpf());
        usuario.setSenha(usuarioRequest.getSenha());
        return usuarioRepository.save(usuario);

    }

    @Override
    public void delete(Integer id) {
        var usuario = usuarioRepository.findById(id).orElseThrow();
        usuarioRepository.delete(usuario);
    }
}
