package com.letscode.banco811.respository;

import com.letscode.banco811.dto.UsuarioResponse;
import com.letscode.banco811.model.Conta;
import com.letscode.banco811.model.TipoConta;
import com.letscode.banco811.projections.ContaView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ContaRepository extends JpaRepository<Conta, Integer> {


    List<Conta> findBySaldoLessThan(BigDecimal saldo);
    List<Conta> findBySaldoLessThanEqual(BigDecimal saldo);
    List<Conta> findBySaldoGreaterThan(BigDecimal saldo);
    List<Conta> findBySaldoGreaterThanEqual(BigDecimal saldo);

    List<Conta> findBySaldoBetween(BigDecimal saldoInicial, BigDecimal saldoFinal);
    List<Conta> findBySaldoIn(List<BigDecimal> saldos);

    List<Conta> findByTipoContaAndSaldoBetweenOrderBySaldo(TipoConta tipoConta, BigDecimal saldoInicial, BigDecimal saldoFinal);

    List<Conta> findByUsuario_cpf(String cpf); // acessar através da conta, os dados do usuário utilizando Inner Join. Atenção ao Underline.

    Boolean existsByTipoConta(TipoConta tipoConta);

    @Query("select c from Conta c " +
            "where (c.tipoConta = :tipoConta and c.usuario.cpf = :cpf) " +
            "or (c.tipoConta = :tipoConta or c.saldo = :saldo)") // esse exemplo é utilizado formato JPQL
    List<Conta> findByTipoContaAndCpfOrTipoContaAndSaldo(
            @Param("tipoConta") TipoConta tipoConta,
            @Param("cpf") String cpf,
            @Param("saldo") BigDecimal saldo
    );

    @Query(value = "select * from conta c" +
            "where (c.tipo_conta = :tipoConta AND" +
            "c.data_criacao >= :dataCriacao)" +
            "OR c.saldo = :saldo ", nativeQuery = true) // nativeQuery permite escrever a query como é feito no banco de dados
    List<Conta> findByDataCriacaoAndTipoContaOrSaldo(
            @Param("dataCriacao") LocalDateTime dataCriacao,
            @Param("tipoConta") LocalDateTime tipoConta,
            @Param("saldo") BigDecimal saldo
    );

    List<ContaView> findAllByTipoConta(TipoConta tipoConta);

}
