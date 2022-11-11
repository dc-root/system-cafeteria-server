package br.com.diego.Lanchonete.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import br.com.diego.Lanchonete.domain.repository.ClienteRepository;
import br.com.diego.Lanchonete.domain.templates.cliente.Cliente;
import br.com.diego.Lanchonete.domain.service.interfaces.GenericInterfaceService;

@Service
public class ClientServiceImpl implements GenericInterfaceService<Cliente> {
    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private ClienteRepository repository;

    @Override @Transactional
    public Cliente cadastrarCliente(Cliente cliente) {
        return repository.save(cliente);
    }

    @Override
    public List<Cliente> listarClientes() {
        return repository.findAll();
    }

    @Override
    public Cliente pesquisarClientePorIdentificador(Long id) {
        return manager.find(Cliente.class, id);
        // return repository.findById(id).orElseThrow(() -> new NonExistentEntityException("CLIENTE NÃO ENCONTRDO"));
    }

    @Override @Transactional
    public void removerCliente(Cliente client) {
        repository.delete(client);
    }
}
