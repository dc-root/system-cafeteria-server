package br.com.diego.Lanchonete.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
public class ClientController {
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
    public ResponseEntity<Cliente> update(
        @RequestBody @Valid Cliente customeData,
        @PathVariable(value="id") Long clientId
    ) {
        Cliente OldClient = customerService.pesquisarClientePorIdentificador(clientId);

        if(OldClient != null) {
            Cliente dataCliente = checkNullFields(customeData, OldClient);

            BeanUtils.copyProperties(dataCliente, OldClient, "id", "cpf");
            Cliente saveClient = customerService.cadastrarCliente(OldClient);
            return ResponseEntity.ok(saveClient);
        }

        return ResponseEntity.notFound().build();
    }

    private Cliente checkNullFields(Cliente reqBody, Cliente old) {
        if(reqBody.getEmail() == null) {
            reqBody.setEmail(old.getEmail());

        } if(reqBody.getNome() == null) {
            reqBody.setNome(old.getNome());

        } if(reqBody.getTelefone() == null) {
            reqBody.setTelefone(old.getTelefone());
        }

        return reqBody;
    }

    @DeleteMapping("/{id}")
    public Cliente remove(@PathVariable(value="id") Long clientId) {
        Cliente client = customerService.pesquisarClientePorIdentificador(clientId);
        customerService.removerCliente(client);
        return client;
    }
}