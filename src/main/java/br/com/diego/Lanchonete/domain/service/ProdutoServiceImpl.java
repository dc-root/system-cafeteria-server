package br.com.diego.Lanchonete.domain.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.diego.Lanchonete.domain.exceptions.NoDataFoundException;
import br.com.diego.Lanchonete.domain.repository.ProdutoRepository;
import br.com.diego.Lanchonete.domain.templates.Produto;

@Service
public class ProdutoServiceImpl {
    @Autowired
    private ProdutoRepository repository;

    @Transactional
    public Produto cadastrarProduto(Produto produto) {
        if(repository.findById(produto.getId()).isPresent()) {
            throw new IllegalArgumentException("> produto com esse codigo='"+produto.getCodigo()+"' já existe!");
        }

        return repository.save(produto);
    }

    public Page<Produto> listarProdutos(Pageable paginacao) {
        Page<Produto> produtos;

        try {
            produtos = repository.findAll(paginacao);
        } catch(NoDataFoundException e) {
            throw new NoDataFoundException("> Nenhum dado de produto encontrado");
        }

        return produtos;    
    }

    public Produto pesquisarProdutoPeloCodigo(Long id) {
        return repository
            .findById(id)
            .orElseThrow(() ->
                new EntityNotFoundException("> Produto com o codigo='"+id+"' não encontrado!")
            );
    }

    @Transactional
    public Produto atualizarProduto(Produto produtoInput, Long id) throws EntityNotFoundException {
        Optional<Produto> produtoSearch = repository.findByCodigo(produtoInput.getCodigo());

        if(produtoSearch.isPresent()) {
            pesquisarProdutoPeloCodigo(id);
            produtoInput.setCodigo(produtoSearch.get().getCodigo());
        } else {
            throw new IllegalArgumentException("> Nenhum prroduto com esse codigo='"+produtoInput.getCodigo()+"' cadastrado");
        }

        return repository.save(produtoInput);
    }
}