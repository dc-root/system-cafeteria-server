package br.com.diego.Lanchonete.domain.modelos;

import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@JsonRootName("cliente")
public class Cliente extends Pessoa {
    private Long id;
}