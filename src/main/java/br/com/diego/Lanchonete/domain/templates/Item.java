package br.com.diego.Lanchonete.domain.templates;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@JsonRootName(value="itens")
@Table(name="ITENS")
@Entity
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @EqualsAndHashCode.Include @NotNull
    @Column(length=10, name="CODIGO") @JsonProperty("code") @NotBlank @Size(min=8, max=8)
    private String codigo;

    @Column(length=100, name="QNT_DE_PRODUTOS") @JsonProperty("qntItems") @NotNull
    private int quantidadeItem=1;
    
    @Column(length=999, name="VALOR_TOTAL") @JsonProperty("subtotalPrice") @NotNull
    private double valorSubTotal;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="PRODUTO", nullable=false)
    @JsonProperty("product") @NotNull
    private Produto produto;

    public Item(
        String codigo,
        int quantidadeItem,
        Produto produto
    ) {
        this.produto = produto;
        this.quantidadeItem = quantidadeItem;
        this.valorSubTotal = (double)(produto.getQuantidadeProduto() * produto.getValorUnitario());
    }

    public Item(
        String codigo,
        Produto produto
    ) {
        this.produto = produto;
        this.valorSubTotal = (double)(produto.getQuantidadeProduto() * produto.getValorUnitario());
    }
}