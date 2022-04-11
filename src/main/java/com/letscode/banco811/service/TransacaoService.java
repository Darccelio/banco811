package com.letscode.banco811.service;

import com.letscode.banco811.dto.TransacaoRequest;

public interface TransacaoService {

    void depositar(TransacaoRequest transacaoRequest);

    void transferirPara(Integer idContaOrigem, TransacaoRequest transacaoRequest);
}
