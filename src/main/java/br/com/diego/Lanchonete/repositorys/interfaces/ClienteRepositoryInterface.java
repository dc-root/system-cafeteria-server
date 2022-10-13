package br.com.diego.Lanchonete.repositorys.interfaces;

import java.util.List;

import br.com.diego.Lanchonete.templates.Cliente;

public interface ClienteRepositoryInterface {
    List<Cliente> listar();
    Cliente buscar(Long id);
    Cliente salvar(Cliente cliente);
    void remover(Cliente cliente);
}
