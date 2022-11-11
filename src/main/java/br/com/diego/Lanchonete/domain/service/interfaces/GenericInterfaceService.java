package br.com.diego.Lanchonete.domain.service.interfaces;

import java.util.List;

public interface GenericInterfaceService<T> {
    T cadastrarCliente(T entity);
    List<T> listarClientes();
    T pesquisarClientePorIdentificador(Long id);
    void removerCliente(T entity);
}