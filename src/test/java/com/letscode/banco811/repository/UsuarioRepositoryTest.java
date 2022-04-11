package com.letscode.banco811.repository;

import com.letscode.banco811.model.Usuario;
import com.letscode.banco811.respository.UsuarioRepository;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

@ActiveProfiles("test")
@DataJpaTest
public class UsuarioRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void validar_findAll_vazio_se_repository_em_branco() {
        var usuarios = usuarioRepository.findAll();
        Assertions.assertEquals(Arrays.asList(), usuarios);
    }

    @Test
    public void trazer_usuario_cadastrados_no_find_all() {
        Usuario usuario1 = new Usuario();
        usuario1.setNome("Maria");
        usuario1.setSenha("12345678910");
        usuario1.setCpf("12345678910");

        Usuario usuario2 = new Usuario();
        usuario2.setNome("Anderson");
        usuario2.setSenha("12345678910");
        usuario2.setCpf("12345678911");

        testEntityManager.persist(usuario1);
        testEntityManager.persist(usuario2);
        List<Usuario> usuariosFindAll = usuarioRepository.findAll();

        Assertions.assertEquals(Arrays.asList(usuario1, usuario2), usuariosFindAll);
    }


    @Test
    public void trazer_usuarios_pelo_nome_com_sucesso() {
        Usuario usuario1 = new Usuario();
        usuario1.setNome("Maria");
        usuario1.setSenha("12345678910");
        usuario1.setCpf("12345678910");

        Usuario usuario2 = new Usuario();
        usuario2.setNome("Anderson");
        usuario2.setSenha("12345678910");
        usuario2.setCpf("12345678911");

        testEntityManager.persist(usuario1);
        testEntityManager.persist(usuario2);

        PageRequest pageRequest = PageRequest.of(0,3);
        Page<Usuario> usuarioProcurado = usuarioRepository.findByNome("Maria", pageRequest);

        Assertions.assertEquals(usuario1, usuarioProcurado.stream().findFirst().get());
    }

}

