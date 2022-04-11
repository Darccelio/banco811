package com.letscode.banco811.service.impl;

import com.letscode.banco811.dto.UsuarioRequest;
import com.letscode.banco811.dto.UsuarioResponse;

import com.letscode.banco811.model.Usuario;
import com.letscode.banco811.respository.UsuarioRepository;
import com.letscode.banco811.service.UsuarioService;
import com.letscode.banco811.specification.UsuarioSpecification;
import com.letscode.banco811.specification.UsuarioSpecificationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public Page<Usuario> getAll(String nome, int page, int size) {
        //este método tem como forma facultativo o recebimento na assinatura do método a String Nome.
        //Ver como é realizado o envio no endpoint do UsuarioControler. @RequestParam(required = false)

        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.DESC, "nome");

        if(nome != null){
            return usuarioRepository.findByNome(nome, pageRequest);
        }
        else {
            return usuarioRepository.findAll(pageRequest);
        }

//        return nome != null ? usuarioRepository.findByNome(nome) : usuarioRepository.findAll();

    }

    @Override
    public List<Usuario> search(String search) { // TODO rever aula 05/04 próximo aos 44 minutos.
        UsuarioSpecificationBuilder builder = new UsuarioSpecificationBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");

        while(matcher.find()) {
            builder.with( matcher.group(1), matcher.group(2), matcher.group(3) );
        }
        Specification<Usuario> spec = builder.build();
        return usuarioRepository.findAll(spec);
    }

    @Override
    public Page<UsuarioResponse> getAllByCof(String cpf, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.DESC, "nome");
        return usuarioRepository.findByCpf(cpf, pageRequest);
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
