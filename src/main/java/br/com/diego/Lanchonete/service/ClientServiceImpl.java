package br.com.diego.Lanchonete.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import br.com.diego.Lanchonete.repositorys.ClientRepository;
import br.com.diego.Lanchonete.templates.Client;

@Service
public class ClientServiceImpl implements ServiceGenerics<Client> {
    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private ClientRepository repository;

    @Override
    public Client save(Client client) {
        return repository.save(client);
    }

    @Override
    public List<Client> list() {
        return repository.findAll();
    }

    @Override
    public Client search(Long id) {
        return manager.find(Client.class, id);
    }

    @Override @Transactional
    public void remove(Client client) {
        repository.delete(client);
    }
}
