package com.letscode.banco811.service;

import com.letscode.banco811.dto.TransacaoRequest;
import com.letscode.banco811.dto.TransacaoResponse;
import org.springframework.data.domain.Page;

public interface TransacaoService {

  void depositar(TransacaoRequest transacaoRequest);

  void transferirPara(Integer idContaOrigem, TransacaoRequest transacaoRequest);

  Page<TransacaoResponse> getAllByContaAndAgencia(
      Integer numero, Integer agencia, int page, int size
  );
}
