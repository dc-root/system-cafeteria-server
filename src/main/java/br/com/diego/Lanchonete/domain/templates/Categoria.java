package br.com.diego.Lanchonete.domain.templates;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode
@JsonRootName(value = "clients")
@MappedSuperclass
public class Categoria {
    @Column(length=10, name="NOME_CATEGORIA") @NotBlank @JsonProperty("category")
    private String nome;
}