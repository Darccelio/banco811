package com.letscode.banco811.projections;

import com.letscode.banco811.model.TipoConta;
import org.springframework.beans.factory.annotation.Value;

public interface ContaView {

    Integer getSaldo();

    TipoConta getTipoConta();

    @Value("#{target.numero +' - '+ target.agencia}")
    String getNumeroAgencia();

    UsuarioView getUsuario();

}


