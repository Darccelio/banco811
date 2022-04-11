package com.letscode.banco811.service.impl;

import com.letscode.banco811.dto.TransacaoRequest;
import com.letscode.banco811.model.Conta;
import com.letscode.banco811.respository.ContaRepository;
import com.letscode.banco811.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.math.BigDecimal;

@Service
public class TransacaoServiceImpl implements TransacaoService {


    @Autowired
    ContaRepository contaRepository;

    @Override
    public void depositar(TransacaoRequest transacaoRequest) {
        Conta contaDeposito = contaRepository.findContaByAgencia(transacaoRequest.getAgencia()).orElseThrow();

        contaDeposito.atualizarSaldo(transacaoRequest.getValor(), transacaoRequest.getTipoTransacao().toString());
        contaRepository.save(contaDeposito);
    }
}
