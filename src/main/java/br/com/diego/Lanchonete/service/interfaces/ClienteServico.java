package br.com.diego.Lanchonete.service.interfaces;

import java.util.List;

import br.com.diego.Lanchonete.templates.Cliente;

public interface ClienteServico {
    public Cliente salvarCliente(Cliente cliente);
    public List<Cliente> listarClientes();
    public Cliente buscaCliente(Long id);
    public void removerCliente(Cliente cliente);
}
