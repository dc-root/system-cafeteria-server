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
        Optional<Atendente> atendenteSearchByCpf = repository.findByCpf(atendenteInput.getCpf());

        if(atendenteSearchByCpf.isPresent()) {
            if(atendenteSearchByCpf.get().getId() == id) {
                atendenteInput.setId(atendenteSearchByCpf.get().getId());
            } else {
                throw new IllegalArgumentException("> CPF='"+atendenteInput.getCpf()+"' não corresponde ao id='"+id+"' do produto a ser atualizado");
            }
        } else {
            throw new IllegalArgumentException("> Nenhum atendente com o CPF='"+atendenteInput.getCpf()+"' cadastrado");
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
                new EntityNotFoundException("> Atendente não encontrado pelo id='"+id+"' especificado!")
            );
    }

    @Transactional
    public void removerAtendente(Long id) {
        try {
            repository.deleteById(id);

        } catch(EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("> Atendente não encontrado pelo id='"+id+"' especificado!");
        }
    }
}