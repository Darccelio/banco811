package com.letscode.banco811.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Table(name = "transacao")
@Entity
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "valor", nullable = false, precision = 2)
    private BigDecimal valor;

    @Column(name = "tipo_transacao", nullable = false)
    private String tipoTransacao;

    @Column(name = "numero", nullable = false)
    private Integer numero;

    @Column(name = "agencia", nullable = false)
    private Integer agencia;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "conta_id", referencedColumnName = "id")
    private Conta conta;

    @Column(name = "data_criacao", nullable = false)
    @CreatedDate
    private LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao", nullable = false)
    @LastModifiedDate
    private LocalDateTime dataAtualizacao;
}