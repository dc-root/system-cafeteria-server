package br.com.diego.Lanchonete.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.diego.Lanchonete.domain.repository.AtendenteRepository;
import br.com.diego.Lanchonete.domain.templates.Atendente;

import java.util.List;

@Service
public class AtendenteServiceImpl {
    @Autowired
    private AtendenteRepository repository;

    @Transactional
    public Atendente cadastrAtendente(Atendente atendente) {
        return repository.save(atendente);
    }

    public Page<Atendente> listarAtendentes(Pageable paginacao) {
        return repository.findAll(paginacao);
    }

    public Atendente pesquAtendentePorIdentificador(Long id) {
        return repository.getReferenceById(id);
    }

    public List<Atendente> pesquisarAtendentesPorNome(String nome) {
        return repository.findByNome(nome);
    }

    @Transactional
    public void removerAtendente(Atendente atendente) {
        repository.delete(atendente);
    }
}
