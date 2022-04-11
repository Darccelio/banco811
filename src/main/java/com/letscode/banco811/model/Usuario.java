package com.letscode.banco811.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.letscode.banco811.dto.UsuarioRequest;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //
    private Integer id;

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Column(name = "senha", nullable = false)
    private String senha;

    @Column(name = "nome")
    private String nome;

    @Column(name="data_criacao") //para deixar o JPA fazer o papel de atualizar sozinho é necessário adicionar @CreateDate e @EntityListeners(AuditingEntityListener.class) na mainApplication é preciso colocar @EnableJpaAuditing
    @CreatedDate
    private LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao")  // para deixar o JPA fazer o papel de atualizar sozinho é necessário adicionar @LastModifiedDate e @EntityListeners(AuditingEntityListener.class)
    @LastModifiedDate
    private LocalDateTime dataAtualizacao;

    @JsonIgnore
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // anotation para informar que a lista é para apenas ser escrita e não lido, fazendo com que as query não seja retornadas. Ou então, melhor, criar o DTO para escolher o que é retornado
    private List<Conta> contas;

    public Usuario(UsuarioRequest usuarioRequest) {
        this.cpf = usuarioRequest.getCpf();
        this.nome = usuarioRequest.getNome();
        this.senha = usuarioRequest.getSenha();
    }

}
