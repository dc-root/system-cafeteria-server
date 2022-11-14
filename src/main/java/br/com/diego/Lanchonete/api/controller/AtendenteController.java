package br.com.diego.Lanchonete.api.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
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
        serviceAtendente.cadastrAtendente(dadosCadastroAtendente);
        return dadosCadastroAtendente;
    }

    @GetMapping
    public Page<Atendente> list(@PageableDefault(size = 10, sort = { "nome" })Pageable paginacao) {
        return serviceAtendente.listarAtendentes(paginacao);
    }

    @GetMapping("/{id}")
    public Atendente search(@PathVariable(value = "id") Long atendenteId) {
        return serviceAtendente.pesquAtendentePorIdentificador(atendenteId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Atendente> update(
        @RequestBody @Valid Atendente atendenteData,
        @PathVariable(value="id") Long atendenteId
    ) {
        Atendente OldAtendente = serviceAtendente.pesquAtendentePorIdentificador(atendenteId);

        changeNullFields(atendenteData, OldAtendente);

        if(OldAtendente != null) {
            BeanUtils.copyProperties(atendenteData, OldAtendente, "id", "cpf");
            Atendente saveAtendente = serviceAtendente.cadastrAtendente(OldAtendente);
            return ResponseEntity.ok(saveAtendente);
        }

        return ResponseEntity.notFound().build();
    }

    private void changeNullFields(Atendente reqBody, Atendente old) {
        if(reqBody.getCpf() == null) {
            reqBody.setCpf(old.getCpf());
        } if(reqBody.getEmail() == null) {
            reqBody.setEmail(old.getEmail());
        } if(reqBody.getNome() == null) {
            reqBody.setNome(old.getNome());
        } if(reqBody.getTelefone() == null) {
            reqBody.setTelefone(old.getTelefone());
        } if (reqBody.getMetaDiaria() == 0) {
            reqBody.setMetaDiaria(old.getMetaDiaria());
        } if (reqBody.getId() == null) {
            reqBody.setId(old.getId());
        }
    }

    @DeleteMapping("/{id}")
    public Atendente remove(@PathVariable(value="id") Long atendenteId) {
        Atendente atendente = serviceAtendente.pesquAtendentePorIdentificador(atendenteId);
        serviceAtendente.removerAtendente(atendente);
        return  atendente;
    }
}
