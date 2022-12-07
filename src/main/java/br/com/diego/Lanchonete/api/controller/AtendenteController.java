package br.com.diego.Lanchonete.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.diego.Lanchonete.domain.service.AtendenteServiceImpl;
import br.com.diego.Lanchonete.domain.templates.Atendente;

import java.lang.reflect.Field;
import java.util.Map;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping("/atendentes")
public class AtendenteController {
    @Autowired
    private AtendenteServiceImpl serviceAtendente;

    @PostMapping
    public Atendente register(
        @RequestBody @Valid Atendente dadosCadastroAtendente
    ) throws IllegalArgumentException {
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
    ) throws EntityNotFoundException {
        return serviceAtendente.pesquisarAtendentePorIdentificador(atendenteId);
    }

    @PutMapping("/{id}")
    public Atendente update(
        @RequestBody @Valid Atendente atendenteData,
        @PathVariable(value="id") Long atendenteId
    ) throws IllegalArgumentException {
        return serviceAtendente.atualizarAtendente(atendenteData, atendenteId);
    }

    // @PatchMapping("/{id}")
    // public Atendente partialUpdate(
    //     @PathVariable(value="id") Long atendenteId,
    //     @RequestBody @Valid Map<String, Object> fields
    // ) {
    //     Atendente currentAtendente = serviceAtendente.pesquisarAtendentePorIdentificador(atendenteId);

    //     merge(fields, currentAtendente);
    //     return update(currentAtendente, atendenteId);
    // }

    // private void merge(Map<String, Object> sourceData, Atendente targetItem) {
    //     ObjectMapper objectMapper = new ObjectMapper();
    //     Atendente sourceItem = objectMapper.convertValue(sourceData, Atendente.class);

    //     sourceData.forEach((nomePropriedade, valorPropriedade) -> {
    //         Field field = ReflectionUtils.findField(Atendente.class, nomePropriedade);
    //         field.setAccessible(true);

    //         Object newValue = ReflectionUtils.getField(field, sourceItem);

    //         ReflectionUtils.setField(field, targetItem, newValue);
    //     });
    // }

    @DeleteMapping("/{id}")
    public void remove(
        @PathVariable(value="id") Long atendenteId
    ) throws EntityNotFoundException {
        serviceAtendente.removerAtendente(
            serviceAtendente
            .pesquisarAtendentePorIdentificador(atendenteId).getId()
        );
    }
}
