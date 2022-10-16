package br.com.diego.Lanchonete.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.diego.Lanchonete.service.ServiceGenerics;
import br.com.diego.Lanchonete.templates.Client;

@RestController
@RequestMapping("/clientes")
@CrossOrigin
public class ClientController{
    @Autowired
    private ServiceGenerics<Client> customerService;

    @GetMapping
    public List<Client> list() {
        return customerService.list();
    }

    @GetMapping("/{id}")
    public Client search(@PathVariable(value = "id") Long clientId) {
        return customerService.search(clientId);
    }

    @PostMapping("/cadastro")
    public Client add(@RequestBody Client client) {
        customerService.save(client);
        return client;
    }

    @PutMapping("/{id}")
    public Client update(@RequestBody Client customeData, @PathVariable(value="id") Long clientId) {
        Client client = customerService.search(clientId);

        client.setCpf(customeData.getCpf());
        client.setNome(customeData.getNome());
        client.setTelefone(customeData.getTelefone());
        client.setEmail(customeData.getEmail());

        customerService.save(client);
        return client;
    }

    @DeleteMapping("/{id}")
    public Client remove(@PathVariable(value="id") Long clientId) {
        Client client = customerService.search(clientId);
        customerService.remove(client);
        return client;
    }
}