package br.com.diego.Lanchonete.api.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.diego.Lanchonete.domain.service.ClientServiceImpl;
import br.com.diego.Lanchonete.domain.templates.Cliente;

@RestController
@RequestMapping("/clientes")
@CrossOrigin
public class ClienteController {
    @Autowired
    private ClientServiceImpl customerService;

    @PostMapping
    public Cliente cadastrar(@RequestBody @Valid Cliente dadosCadastroCliente) {
        customerService.cadastrarCliente(dadosCadastroCliente);
        return dadosCadastroCliente;
    }

    @GetMapping
    public Page<Cliente> list(@PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
        return customerService.listarClientes(paginacao);
    }

    @GetMapping("/{id}")
    public Cliente search(@PathVariable(value = "id") Long clientId) {
        return customerService.pesquisarClientePorIdentificador(clientId);
    }

    @PutMapping("/{id}")
    public Cliente update(
        @RequestBody @Valid Cliente clientData,
        @PathVariable(value="id") Long clientId
    ) {
        return customerService.atualizarCliente(clientData, clientId);
    }
    
    @DeleteMapping("/{id}")
    public void remove(
        @PathVariable(value="id") Long clientId
    ) {
        customerService.removerCliente(
            customerService.pesquisarClientePorIdentificador(clientId).getId()
        );
    }
}