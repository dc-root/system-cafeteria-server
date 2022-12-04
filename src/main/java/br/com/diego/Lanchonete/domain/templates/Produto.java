package br.com.diego.Lanchonete.domain.templates;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded=true, callSuper=false)
@JsonRootName(value = "product")
@Table(name = "PRODUTOS")
@Entity
public class Produto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @EqualsAndHashCode.Include
    @Column(length=20, name="NOME") @JsonProperty("productName") @NotBlank
    private String nome;

    @EqualsAndHashCode.Include
    @Column(length = 10) @JsonProperty("code") @NotBlank @Size(min=8, max=8)
    private String codigo;

    @Column(name="QNT_DE_PRODUTOS") @JsonProperty("qntProducts") @NotNull
    private int quantidadeProduto=1;
    
    @Column(name="VALOR_UNITARIO") @JsonProperty("unitPrice") @NotNull
    private double valorUnitario;

    public Produto(
        String nome,
        String codigo,
        int quantidadeProduto,
        double valorUnitario
    ) {
        this.nome = nome;
        this.codigo = codigo;
        this.quantidadeProduto = quantidadeProduto;
        this.valorUnitario = valorUnitario;
    }

    public Produto(
        String nome,
        String codigo,
        double valorUnitario
    ) {
        this.nome = nome;
        this.codigo = codigo;
        this.valorUnitario = valorUnitario;
    }
}