package com.letscode.banco811.service.impl;

import com.letscode.banco811.dto.ContaResponse;
import com.letscode.banco811.dto.TransacaoRequest;
import com.letscode.banco811.model.Conta;
import com.letscode.banco811.respository.ContaRepository;
import com.letscode.banco811.respository.TransacoesRepository;
import com.letscode.banco811.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransacaoServiceImpl implements TransacaoService {


    @Autowired
    ContaRepository contaRepository;

    @Autowired
    TransacoesRepository transacoesRepository;

    @Override
    public void depositar(TransacaoRequest transacaoRequest) {
        Conta contaDeposito = transacoesRepository.findContaByAgencia(transacaoRequest.getAgencia()).orElseThrow();
        contaDeposito.atualizarSaldo(transacaoRequest.getValor(), transacaoRequest.getTipoTransacao());
        transacoesRepository.save(contaDeposito);
    }

    @Override
    public void transferirPara(Integer idContaOrigem, TransacaoRequest transacaoRequest) {
        Conta contaOgigem = contaRepository.findById(idContaOrigem).orElseThrow();
        Conta contaDestino = transacoesRepository.findByNumeroAndAgencia(
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
        transacoesRepository.save(contaOgigem);
        transacoesRepository.save(contaDestino);
    }
}
