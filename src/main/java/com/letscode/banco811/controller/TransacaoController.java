package com.letscode.banco811.controller;


import com.letscode.banco811.dto.TransacaoRequest;
import com.letscode.banco811.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping("/deposito")
    public void depositar(@RequestBody TransacaoRequest transacaoRequest) {
        transacaoService.depositar(transacaoRequest);
    }

}
