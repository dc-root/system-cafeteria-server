package br.com.diego.Lanchonete.repositorys.interfaces;

import br.com.diego.Lanchonete.modelos.Cliente;

import java.util.List;

public interface ClienteRepositoryInterface {
    List<Cliente> listar();
    Cliente buscar(Long id);
    Cliente salvar(Cliente cliente);
    void remover(Cliente cliente);
}
