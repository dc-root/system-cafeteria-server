package br.com.diego.Lanchonete.modelos;

import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded=true, callSuper=false)
@JsonRootName("cliente")
public class Cliente extends Pessoa {
    private Long id;
}