package com.letscode.banco811.controller;

import com.letscode.banco811.dto.TransacaoRequest;
import com.letscode.banco811.dto.TransacaoResponse;
import com.letscode.banco811.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

  @Autowired private TransacaoService transacaoService;

  @GetMapping()
  public Page<TransacaoResponse> getAllByContaAndAgencia(
      @RequestParam(required = true) Integer numero,
      @RequestParam(required = true) Integer agencia,
      @RequestParam(required = false, defaultValue = "0") int page,
      @RequestParam(required = false, defaultValue = "10") int size) {
    return transacaoService.getAllByContaAndAgencia(numero, agencia, page, size);
  }

  @PutMapping("/deposito")
  public void depositar(@RequestBody TransacaoRequest transacaoRequest) {
    transacaoService.depositar(transacaoRequest);
  }

  @PostMapping("/transferir/{id}")
  public void transferirPara(
      @PathVariable Integer id, @RequestBody TransacaoRequest transacaoRequest) {
    transacaoService.transferirPara(id, transacaoRequest);
  }
}
