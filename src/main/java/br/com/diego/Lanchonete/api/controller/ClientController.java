package br.com.diego.Lanchonete.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.catalina.connector.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.diego.Lanchonete.domain.service.ClientServiceImpl;
import br.com.diego.Lanchonete.domain.templates.cliente.Cliente;

@RestController
@RequestMapping("/clientes")
@CrossOrigin
public class ClientController {
    @Autowired
    private ClientServiceImpl customerService;

    @GetMapping
    public List<Cliente> list() {
        return customerService.listarClientes();
    }

    @GetMapping("/{id}")
    public Cliente search(@PathVariable(value = "id") Long clientId) {
        return customerService.pesquisarClientePorIdentificador(clientId);
    }

    @PostMapping("/cadastro")
    public Cliente cadastrar(@RequestBody Cliente dadosCadastroCliente) {
        customerService.cadastrarCliente(dadosCadastroCliente);
        return dadosCadastroCliente;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(
        @RequestBody Cliente customeData,
        @PathVariable(value="id") Long clientId
    ) {
        Cliente OldClient = customerService.pesquisarClientePorIdentificador(clientId);

        if(OldClient != null) {
            BeanUtils.copyProperties(clientId, OldClient, "id");
            Cliente saveClient = customerService.cadastrarCliente(OldClient);
            return ResponseEntity.ok(saveClient);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public Cliente remove(@PathVariable(value="id") Long clientId) {
        Cliente client = customerService.pesquisarClientePorIdentificador(clientId);
        customerService.removerCliente(client);
        return client;
    }
}