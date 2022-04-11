package com.letscode.banco811.service;

import com.letscode.banco811.dto.ContaRequest;
import com.letscode.banco811.dto.ContaResponse;
import com.letscode.banco811.model.TipoConta;
import com.letscode.banco811.projections.ContaView;

import java.util.List;

public interface ContaService {

  ContaResponse create(ContaRequest contaRequest, Integer usuarioId);
  List<ContaResponse> getAll();
  List<ContaView> getAllViewByTipoConta(TipoConta tipoConta);

}
