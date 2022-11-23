package br.com.diego.Lanchonete.domain.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.diego.Lanchonete.domain.exceptions.NoDataFoundException;
import br.com.diego.Lanchonete.domain.repository.AtendenteRepository;
import br.com.diego.Lanchonete.domain.templates.Atendente;

@Service
public class AtendenteServiceImpl {
    @Autowired
    private AtendenteRepository repository;

    @Transactional
    public Atendente cadastrarAtendente(Atendente atendente) {
        if(repository.findByCpf(atendente.getCpf()).isPresent()) {
            throw new IllegalArgumentException("> CPF de atendente já cadastrada");
        }

        return repository.save(atendente);
    }

    @Transactional
    public Atendente atualizarAtendente(Atendente atendenteInput, Long id) {
        Optional<Atendente> atendenteSearch = repository.findByCpf(atendenteInput.getCpf());

        if(atendenteSearch.isPresent()) {
            atendenteInput.setId(atendenteSearch.get().getId());
        } else {
            throw new IllegalArgumentException("> Nenhum atendente com este CPF cadastrado"); 
        }

        return repository.save(atendenteInput);
    }

    public Page<Atendente> listarAtendentes(Pageable paginacao) {
        Page<Atendente> atendentes; 
        
        try {
            atendentes = repository.findAll(paginacao);
        } catch(NoDataFoundException e) {
            throw new NoDataFoundException("> Nenhum dado de atendente encontrado!");
        }

        return atendentes;
    }

    public Atendente pesquisarAtendentePorIdentificador(Long id) {
        return repository
            .findById(id)
            .orElseThrow(() -> 
                new EntityNotFoundException("> Atendente não encontrado pelo identificador especificado!")
            );
    }

    @Transactional
    public void removerAtendente(Long id) {
        try {
            repository.deleteById(id);
        } catch(EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("> Atendente não encontrado pelo identificador especificado!");
        }
    }
}
