package com.letscode.banco811.respository;

import com.letscode.banco811.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransacoesRepository extends JpaRepository<Conta, Integer> {

    Optional<Conta> findByNumeroAndAgencia(Integer numero, Integer agencia);
    Optional<Conta> findContaByAgencia(Integer agencia);


}
