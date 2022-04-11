package com.letscode.banco811.service.impl;

import com.letscode.banco811.dto.ContaResponse;
import com.letscode.banco811.dto.TransacaoRequest;
import com.letscode.banco811.model.Conta;
import com.letscode.banco811.respository.ContaRepository;
import com.letscode.banco811.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransacaoServiceImpl implements TransacaoService {


    @Autowired
    ContaRepository contaRepository;

    @Override
    public void depositar(TransacaoRequest transacaoRequest) {
        Conta contaDeposito = contaRepository.findContaByAgencia(transacaoRequest.getAgencia()).orElseThrow();
        contaDeposito.atualizarSaldo(transacaoRequest.getValor(), transacaoRequest.getTipoTransacao());
        contaRepository.save(contaDeposito);
    }

    @Override
    public void transferirPara(Integer idContaOrigem, TransacaoRequest transacaoRequest) {
        Conta contaOgigem = contaRepository.findById(idContaOrigem).orElseThrow();
        Conta contaDestino = contaRepository.findByNumeroAndAgencia(
                transacaoRequest.getNumero(),
                transacaoRequest.getAgencia()
        ).orElseThrow();

        contaDestino.atualizarSaldo(transacaoRequest.getValor(), transacaoRequest.getTipoTransacao());

        if(transacaoRequest.getTipoTransacao().equals("credito")) {
            contaOgigem.atualizarSaldo(transacaoRequest.getValor(), "debito");
        }
        else{
            contaOgigem.atualizarSaldo(transacaoRequest.getValor(), "credito");
        }
        contaRepository.save(contaOgigem);
        contaRepository.save(contaDestino);
    }
}
