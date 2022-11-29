package br.com.diego.Lanchonete.domain.templates;

import java.util.List;

import br.com.diego.Lanchonete.domain.templates.Atendente;
import br.com.diego.Lanchonete.domain.templates.Cliente;

public class Pedido {
    private int numeroDoPedido;

    private Atendente atendente;
    private Cliente cliente;

    private List<Produto> listaDeProdutos;
}