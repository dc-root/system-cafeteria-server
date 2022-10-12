package br.com.diego.Lanchonete.repositorys;

import br.com.diego.Lanchonete.repositorys.interfaces.ClienteRepositoryInterface;
import br.com.diego.Lanchonete.modelos.Cliente;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public abstract class ClienteRepository implements ClienteRepositoryInterface {
    private Cliente cliente = new Cliente();

    public List<Cliente> listar() {
        
    }
    
    public Cliente buscar(Long id) {

    }

    public Cliente salvar(Cliente cliente) {
        
    }
    
    public void remover(Cliente cliente) {

    }
}