package br.com.diego.Lanchonete.api.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.diego.Lanchonete.domain.service.AtendenteServiceImpl;
import br.com.diego.Lanchonete.domain.templates.Atendente;

import javax.validation.Valid;

@RestController
@RequestMapping("/atendentes")
public class AtendenteController {
    @Autowired
    private AtendenteServiceImpl serviceAtendente;

    @PostMapping
    public Atendente cadastrar(@RequestBody @Valid Atendente dadosCadastroAtendente) {
        return serviceAtendente.cadastrarAtendente(dadosCadastroAtendente);
    }

    @GetMapping
    public Page<Atendente> list(
        @PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao
    ) {
        return serviceAtendente.listarAtendentes(paginacao);
    }

    @GetMapping("/{id}")
    public Atendente search(
        @PathVariable(value = "id") Long atendenteId
    ) {
        return serviceAtendente.pesquisarAtendentePorIdentificador(atendenteId);
    }

    @PutMapping("/{id}")
    public Atendente update(
        @RequestBody @Valid Atendente atendenteData,
        @PathVariable(value="id") Long atendenteId
    ) {
        return serviceAtendente.atualizarAtendente(atendenteData, atendenteId);
    }

    @DeleteMapping("/{id}")
    public void remove(
        @PathVariable(value="id") Long atendenteId
    ) {
        serviceAtendente.removerAtendente(
            serviceAtendente
            .pesquisarAtendentePorIdentificador(atendenteId).getId()
        );
    }
}
