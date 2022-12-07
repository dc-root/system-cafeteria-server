package br.com.diego.Lanchonete.domain.templates;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.com.diego.Lanchonete.domain.templates.Atendente;
import br.com.diego.Lanchonete.domain.templates.Cliente;

public class Pedido {
    @Column
    private int numeroDoPedido;

    @ManyToOne
    @JoinColumn
    private Atendente atendente;

    @ManyToOne
    @JoinColumn
    private Cliente cliente;

    @Column
    @JoinColumn
    private double valorTotal;

    @OneToMany
    @JoinColumn
    private List<Item> listaDeItem;

    public Pedido(
        Atendente atendente,
        Cliente cliente,
        List<Item> listaDeItem
    ) {
        this.atendente = atendente;
        this.cliente = cliente;
        this.listaDeItem = listaDeItem;
        
        listaDeItem.stream()
        .forEach(item -> {
            this.valorTotal += item.getValorSubTotal();
        });
    }
}