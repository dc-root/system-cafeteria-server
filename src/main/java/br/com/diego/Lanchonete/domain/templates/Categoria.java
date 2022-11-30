package br.com.diego.Lanchonete.domain.templates;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
// @MappedSuperclass
public abstract class Categoria {
    @EqualsAndHashCode.Include
    @Column(length=20, name="NOME_CATEGORIA") @NotBlank @JsonProperty("category")
    private String NomeCategoria;
}