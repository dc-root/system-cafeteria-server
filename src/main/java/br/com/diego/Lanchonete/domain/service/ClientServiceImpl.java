package br.com.diego.Lanchonete.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import br.com.diego.Lanchonete.domain.repository.ClienteRepository;
import br.com.diego.Lanchonete.domain.templates.Cliente;

@Service
public class ClientServiceImpl {
    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private ClienteRepository repository;

    @Transactional
    public Cliente cadastrarCliente(Cliente cliente) {
        return repository.save(cliente);
    }

    public List<Cliente> listarClientes() {
        return repository.findAll();
    }

    public Cliente pesquisarClientePorIdentificador(Long id) {
        return manager.find(Cliente.class, id);
        // return repository.findById(id).orElseThrow(() -> new NonExistentEntityException("CLIENTE NÃO ENCONTRDO"));
    }

    public List<Cliente> pesquisarClientePorNome(String nome) {
        return repository.findByNome(nome);
    }

    @Transactional
    public void removerCliente(Cliente client) {
        repository.delete(client);
    }
}
