package com.letscode.banco811.dto;

import com.letscode.banco811.model.Conta;
import com.letscode.banco811.model.TipoConta;
import com.letscode.banco811.model.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter @Setter
public class ContaResponse {

    private Integer id;
    private UsuarioResponse usuario;
    private Integer numero;
    private Integer agencia;
    private TipoConta tipoConta;
    private BigDecimal saldo;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    public ContaResponse(Conta conta){
        this.id = conta.getId();
        this.usuario = new UsuarioResponse(conta.getUsuario());
        this.numero = conta.getNumero();
        this.agencia = conta.getAgencia();
        this.tipoConta = conta.getTipoConta();
        this.saldo = conta.getSaldo();
        this.dataCriacao = conta.getDataCriacao();
        this.dataAtualizacao = conta.getDataAtualizacao();
    }

    public static List<ContaResponse> toResponse(List<Conta> contas){
        return contas.stream().map(ContaResponse::new).collect(Collectors.toList());
    }

}
