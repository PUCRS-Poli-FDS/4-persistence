package br.pucrs.persistence.persistenceapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Contato {
    String nome;
    String telefone;
}
