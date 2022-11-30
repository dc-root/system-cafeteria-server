package br.com.diego.Lanchonete.domain.templates;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded=true, callSuper=false)
@JsonRootName(value = "products")
@Table(name = "PRODUTOS")
@Entity
public class Produto {
    @Column(length=20, name="NOME") @JsonProperty("productName")
    private String nome;

    @Id @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @NotNull(message = "O campo 'quantidade' não pode ser nulo")
    @Column(length = 100, name="QNT_DE_PRODUTOS") @JsonProperty("qntProducts")
    private Long quantidade;

    @NotNull(message = "O campo 'valorUnitario' não pode ser nulo")
    @Column(length = 999, name="VALOR_UNITARIO") @JsonProperty("unitPrice")
    private double valorUnitario;
}