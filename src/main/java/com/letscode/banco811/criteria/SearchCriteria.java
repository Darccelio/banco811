package com.letscode.banco811.criteria;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class SearchCriteria {
  private String Key; // atributo da classe que se deseja pesquisar (nome, cpf,)
  private String operation; // se havera operacao matematica >, < = etc..
  private Object value; // value seria o criterio a ser pesquisado, como por exemplo encontrar os usuarios com
             // o nome maria
}
