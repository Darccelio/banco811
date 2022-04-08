package com.letscode.banco811.service;

import com.letscode.banco811.dto.ContaRequest;
import com.letscode.banco811.dto.ContaResponse;
import org.apache.catalina.LifecycleState;

import java.util.List;

public interface ContaService {

  ContaResponse create(ContaRequest contaRequest, Integer usuarioId);
  List<ContaResponse> getAll();
}
