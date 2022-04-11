package com.letscode.banco811.respository;

import com.letscode.banco811.dto.UsuarioResponse;
import com.letscode.banco811.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>, JpaSpecificationExecutor<Usuario> { //JpaRepository<entidade gerenciada, tipo do id>

    Page<Usuario> findByNome(String nome, Pageable pageable);

    @Query("select new com.letscode.banco811.dto.UsuarioResponse(u.id, u.cpf, u.nome, u.dataCriacao, u.dataCriacao) from Usuario u" +
    " where u.cpf = :cpf")
    Page<UsuarioResponse> findByCpf(@Param("cpf") String cpf, Pageable pageable);

    List<Usuario> findByNomeAndCpf(String nome, String cpf);

    List<Usuario> findByNomeIs(String nome);
    List<Usuario> findByNomeNot(String nome);

    List<Usuario> findByNomeIsNull();
    List<Usuario> findByNomeIsNotNull();

    List<Usuario> findByCpfStartingWith(String cpf);
    List<Usuario> findByCpfEndingWith(String cpf);
    List<Usuario> findByCpfContaining(String cpf);

    List<Usuario> findByNomeLike(String pattern);

    List<Usuario> findByDataCriacaoAfterAndNomeAndCpf(LocalDateTime dataCriacao, String nome, String cpf);
    List<Usuario> findByDataCriacao(LocalDateTime dataCriacao);

    List<Usuario> findByNomeAndDataCriacaoOrderByNomeAsc(String nome, LocalDateTime dataCriacao);
}
