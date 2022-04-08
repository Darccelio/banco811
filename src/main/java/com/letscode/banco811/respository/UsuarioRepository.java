package com.letscode.banco811.respository;

import com.letscode.banco811.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> { //JpaRepository<entidade gerenciada, tipo do id>
    List<Usuario> findByNome(String nome);
//    List<Usuario> findByNomeAndCpf(String nome, String cpf);
//
//    List<Usuario> findByNomeIs(String nome);
//    List<Usuario> findByNomeNot(String nome);

//    List<Usuario> findByNomeIsNull();
//    List<Usuario> findByNomeIsNotNullNull();

//    List<Usuario> findByCpfStartingWith(String cpf);
//    List<Usuario> findByCpfEndingWith(String cpf);
//    List<Usuario> findByCpfContaining(String cpf);
//
//    List<Usuario> findByNomeLike(String pattern);
//
//    List<Usuario> findByDataCriacaoAfterAndNomeAndCpf(LocalDateTime dataCriacao, String nome, String cpf);
//    List<Usuario> findByDataCriacao(LocalDateTime dataCriacao);

//    List<Usuario> findByNomeAndDataCriacaoOrderByNomeAsc(String nome, LocalDateTime dataCriacao);
}
