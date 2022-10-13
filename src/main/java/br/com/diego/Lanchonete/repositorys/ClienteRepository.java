package br.com.diego.Lanchonete.repositorys;

import br.com.diego.Lanchonete.repositorys.interfaces.ClienteRepositoryInterface;
import br.com.diego.Lanchonete.templates.Cliente;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;

@Component
public class ClienteRepository implements ClienteRepositoryInterface {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Cliente> listar() {
        return manager
            .createQuery("FROM cliente", Cliente.class)
            .getResultList();
    }

    @Override
    public Cliente buscar(Long id) {
        return manager.find(Cliente.class, id);
    }

    @Override
    @Transactional
    public Cliente salvar(Cliente cliente) {
        System.out.println("cliente: "+cliente.getId());
        return manager.merge(cliente);
    }
    
    @Override
    @Transactional
    public void remover(Cliente cliente) {
        System.out.println("cliente: "+cliente.getId());
        cliente = buscar(cliente.getId());
        manager.remove(cliente);
    }
}