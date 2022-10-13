package br.com.diego.Lanchonete.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import br.com.diego.Lanchonete.repositorys.ClienteRepository;
import br.com.diego.Lanchonete.service.interfaces.ClienteServico;
import br.com.diego.Lanchonete.templates.Cliente;

@Service
public class ClienteServicoImpl implements ClienteServico {
    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente salvarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscaCliente(Long id) {
        return manager.find(Cliente.class, id);
    }

    @Override
    @Transactional
    public void removerCliente(Cliente cliente) {
        clienteRepository.delete(cliente);
    }
}
