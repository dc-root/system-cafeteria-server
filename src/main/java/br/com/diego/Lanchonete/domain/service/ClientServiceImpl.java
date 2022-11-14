package br.com.diego.Lanchonete.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

import javax.transaction.Transactional;

import br.com.diego.Lanchonete.domain.repository.ClienteRepository;
import br.com.diego.Lanchonete.domain.templates.Cliente;

@Service
public class ClientServiceImpl {
    @Autowired
    private ClienteRepository repository;

    @Transactional
    public Cliente cadastrarCliente(Cliente cliente) {
        return repository.save(cliente);
    }

    public Page<Cliente> listarClientes(Pageable paginacao) {
        return repository.findAll(paginacao);
    }

    public Cliente pesquisarClientePorIdentificador(Long id) {
        return repository.getReferenceById(id);
    }

    public List<Cliente> pesquisarClientePorNome(String nome) {
        return repository.findByNome(nome);
    }

    @Transactional
    public void removerCliente(Cliente client) {
        repository.delete(client);
    }
}
