package br.com.diego.Lanchonete.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.diego.Lanchonete.repositorys.ClienteRepository;
import br.com.diego.Lanchonete.templates.Cliente;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/")
    public List<Cliente> listar() {
        return clienteRepository.listar();
    }

    @GetMapping("/{clienteId}")
    public Cliente buscar(@PathVariable Long clienteId) {
        return clienteRepository.buscar(clienteId);
    }
}