package com.letscode.banco811.service.impl;

import com.letscode.banco811.dto.TransacaoRequest;
import com.letscode.banco811.dto.TransacaoResponse;
import com.letscode.banco811.model.Conta;
import com.letscode.banco811.model.Transacao;
import com.letscode.banco811.respository.ContaRepository;
import com.letscode.banco811.respository.TransacoesRepository;
import com.letscode.banco811.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TransacaoServiceImpl implements TransacaoService {


    @Autowired
    ContaRepository contaRepository;

    @Autowired
    TransacoesRepository transacoesRepository;

    @Override
    public void depositar(TransacaoRequest transacaoRequest) {
        Conta contaDeposito = contaRepository.findContaByAgencia(transacaoRequest.getAgencia()).orElseThrow();
        Transacao transacao = new Transacao(transacaoRequest);
        transacao.setConta(contaDeposito);
        contaDeposito.atualizarSaldo(transacaoRequest.getValor(), transacaoRequest.getTipoTransacao());
        contaDeposito = contaRepository.save(contaDeposito);
        System.out.println(transacao);
        transacao = transacoesRepository.save(transacao);
        System.out.println("TESTE");
        System.out.println();
    }

    @Override
    public void transferirPara(Integer idContaOrigem, TransacaoRequest transacaoRequest) {

        Conta contaOgigem = contaRepository.findById(idContaOrigem).orElseThrow();
        Conta contaDestino = contaRepository.findByNumeroAndAgencia(
                transacaoRequest.getNumero(),
                transacaoRequest.getAgencia()
        ).orElseThrow();

        String tipoOperacaoContaOrigem ="";
        contaDestino.atualizarSaldo(transacaoRequest.getValor(), transacaoRequest.getTipoTransacao());

        if(transacaoRequest.getTipoTransacao().equals("credito")) {
            tipoOperacaoContaOrigem = "debito";
            contaOgigem.atualizarSaldo(transacaoRequest.getValor(), tipoOperacaoContaOrigem);
        }
        else{
            tipoOperacaoContaOrigem = "credito";
            contaOgigem.atualizarSaldo(transacaoRequest.getValor(), tipoOperacaoContaOrigem);
        }

        contaRepository.save(contaOgigem);
        contaRepository.save(contaDestino);

        Transacao transacaoDestino = new Transacao(transacaoRequest);
        transacaoDestino = transacoesRepository.save(transacaoDestino);

        Transacao transacaoOrigem = new Transacao();
        transacaoOrigem.setTipoTransacao(tipoOperacaoContaOrigem);
        transacaoOrigem.setNumero(contaOgigem.getNumero());
        transacaoOrigem.setAgencia(contaOgigem.getAgencia());
        transacaoOrigem.setValor(transacaoRequest.getValor());

        transacaoOrigem = transacoesRepository.save(transacaoOrigem);
    }

    @Override
    public Page<TransacaoResponse> getAllByContaAndAgencia(Integer numero, Integer agencia, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.DESC, "dataCriacao");
        return transacoesRepository.findAllByNumeroAndAgencia(numero, agencia, pageRequest);
    }
}
