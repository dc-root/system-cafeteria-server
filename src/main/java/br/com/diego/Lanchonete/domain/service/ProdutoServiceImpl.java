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

    /*@Transactional
    public Produto cadastrarProduto(Produto produto) {
        if(repository.findByCode(produto.getCode()).isPresent()) {
            throw new IllegalArgumentException("> produto com esse codigo='"+produto.getCode()+"' já existe!");
        } else if(repository.findByNome(produto.getNome()).isPresent()) {
            throw new IllegalArgumentException("> produto com esse nome='"+produto.getNome()+"' já existe!");
        }

        return repository.save(produto);
    }*/

    public Page<Produto> listarProdutos(Pageable paginacao) {
        Page<Produto> produtos;

        try {
            produtos = repository.findAll(paginacao);
        } catch(NoDataFoundException e) {
            throw new NoDataFoundException("> Nenhum dado de produto encontrado");
        }

        return produtos;
    }

    public Produto pesquisarProdutoPorIdentificador(Long id) {
        return repository
            .findById(id)
            .orElseThrow(() ->
                new EntityNotFoundException("> Produto com o codigo='"+id+"' não encontrado!")
            );
    }

    @Transactional
    public Produto atualizarProduto(Produto produtoInput, Long id) throws EntityNotFoundException {
        Optional<Produto> produtoSearchByCodigo = repository.findByCodigo(produtoInput.getCodigo());

        if(produtoSearchByCodigo.isPresent()) {
            if(pesquisarProdutoPorIdentificador(id).equals(produtoSearchByCodigo.get())) {
                produtoInput.setId(produtoSearchByCodigo.get().getId());
            } else {
                throw new IllegalArgumentException("> codigo='"+produtoInput.getCodigo()+"' ou nome='"+produtoInput.getNome()+"' não corresponde ao id='"+id+"' do produto a ser atualizado");
            }
        } else {
            throw new IllegalArgumentException("> Nenhum produto com code='"+produtoInput.getCodigo()+"' cadastrado!");
        }

        return repository.save(produtoInput);
    }

    @Transactional
    public void removerProduto(Long id) {
        try {
            repository.deleteById(id);
        } catch(EntityNotFoundException e) {
            throw new EntityNotFoundException("> Produto não encontrado pelo id='"+id+"'");
        }
    }
}