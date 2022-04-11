package com.letscode.banco811.respository;

import com.letscode.banco811.dto.TransacaoResponse;
import com.letscode.banco811.model.Conta;
import com.letscode.banco811.model.Transacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TransacoesRepository extends JpaRepository<Transacao, Integer> {



    @Query("select new com.letscode.banco811.dto.TransacaoResponse( t.valor, t.tipoTransacao, t.numero, t.agencia, t.dataCriacao) from Transacao t where t.numero = :numero AND t.agencia = :agencia")
    Page<TransacaoResponse> findAllByNumeroAndAgencia(
            @Param("numero") Integer numero,
            @Param("agencia") Integer agencia,
            Pageable pageable
    );


}
