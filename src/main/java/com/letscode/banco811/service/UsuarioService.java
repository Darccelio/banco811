package com.letscode.banco811.service;

import com.letscode.banco811.dto.UsuarioRequest;
import com.letscode.banco811.dto.UsuarioResponse;
import com.letscode.banco811.model.Usuario;


import java.util.List;

public interface UsuarioService {
    List<Usuario> getAll(String nome);
    UsuarioResponse create(UsuarioRequest usuarioRequest);
    UsuarioResponse getById(Integer id);
    Usuario update(UsuarioRequest usuarioRequest, Integer id);
    void delete(Integer id);
}
