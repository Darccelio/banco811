package com.letscode.banco811.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Table(name = "conta")
@Entity
@Getter @Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Conta {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "numero")
  private Integer numero;

  @Column(name = "agencia")
  private Integer agencia;

  @Column(name = "data_criacao")
  @CreatedDate
  // para deixar o JPA fazer o papel de atualizar sozinho é necessário adicionar @CreateDate e
  // @EntityListeners(AuditingEntityListener.class) na mainApplication é preciso colocar
  // @EnableJpaAuditing
  private LocalDateTime dataCriacao; // jpa exige LocalDate ou LocalDateTime

  @Column(name = "data_atualizacao")
  @LastModifiedDate
  // para deixar o JPA fazer o papel de atualizar sozinho é necessário adicionar @LastModifiedDate e
  // @EntityListeners(AuditingEntityListener.class) na mainApplication é preciso colocar
  // @EnableJpaAuditing
  private LocalDateTime dataAtualizacao;

  @Column(name = "saldo")
  private BigDecimal saldo;

  @Column(name = "tipo_conta")
  @Enumerated(EnumType.STRING) //atraves do JPA @Enumerated é utilizado para obter em formato de String ou Ordinal (tipo 0 PF, 1 PJ, etc..)
  private TipoConta tipoConta;


  @ManyToOne(cascade = CascadeType.ALL) //atraves do cascade, é possível que a cada alteração que impacte o usuário, também seja realizado na entiddade usuario com segurança.
  @JoinColumn(name = "usuario_id", referencedColumnName = "id") //especificando em quais colunas as duas contas se relacionam (PK e FK) ->> usuario_id está dentro de conta e id está dentro de usuario
  private Usuario usuario; // mapeando ao Relacionamento entre Usuário e Conta


  public void atualizarSaldo(BigDecimal valor, String tipoOperacao) {
    if (tipoOperacao.equals("credito")) this.saldo = this.saldo.add(valor);
    else {
      this.saldo = this.saldo.subtract(valor);
    }
  }


}