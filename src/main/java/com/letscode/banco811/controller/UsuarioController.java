package com.letscode.banco811.controller;

import com.letscode.banco811.dto.UsuarioRequest;
import com.letscode.banco811.dto.UsuarioResponse;
import com.letscode.banco811.model.Usuario;
import com.letscode.banco811.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping()
    public List<Usuario> getAll(@RequestParam(required = false) String nome) {
        return usuarioService.getAll(nome);
    }

    @PostMapping
    public UsuarioResponse create(@RequestBody UsuarioRequest usuarioRequest) {
        return usuarioService.create(usuarioRequest);
    }

    @GetMapping("/{id}")
    public UsuarioResponse getById(@PathVariable Integer id) {
        return usuarioService.getById(id);
    }

    @PutMapping("/{id}")
    public Usuario updateById(@RequestBody UsuarioRequest usuarioRequest, @PathVariable Integer id) {
        return usuarioService.update(usuarioRequest, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        usuarioService.delete(id);
    }



}
