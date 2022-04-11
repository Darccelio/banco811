package com.letscode.banco811.service.impl;

import com.letscode.banco811.dto.ContaRequest;
import com.letscode.banco811.dto.ContaResponse;
import com.letscode.banco811.model.Conta;
import com.letscode.banco811.model.TipoConta;
import com.letscode.banco811.model.Usuario;
import com.letscode.banco811.projections.ContaView;
import com.letscode.banco811.respository.ContaRepository;
import com.letscode.banco811.respository.UsuarioRepository;
import com.letscode.banco811.service.ContaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaServiceImpl implements ContaService {

    @Autowired
    ContaRepository contaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ContaResponse create(ContaRequest contaRequest, Integer usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow();

        Conta conta = new Conta();

        conta.setNumero(contaRequest.getNumero());
        conta.setAgencia(contaRequest.getAgencia());
        conta.setTipoConta(contaRequest.getTipoConta());
        conta.setSaldo(contaRequest.getSaldo());
        conta.setUsuario(usuario);

        return new ContaResponse(contaRepository.save(conta));
    }

    @Override
    public List<ContaResponse> getAll() {
        List<Conta> contas = contaRepository.findAll();
        ContaResponse contaResponse = new ContaResponse();
        return contaResponse.toResponse(contas);
    }

    @Override
    public List<ContaView> getAllViewByTipoConta(TipoConta tipoConta) {
        return contaRepository.findAllByTipoConta(tipoConta);
    }

}
