package br.com.diego.Lanchonete.domain.service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.diego.Lanchonete.domain.exceptions.NoDataFoundException;
import br.com.diego.Lanchonete.domain.repository.ItemRepository;
import br.com.diego.Lanchonete.domain.repository.ProdutoRepository;
import br.com.diego.Lanchonete.domain.templates.Item;

@Service
public class ItemServiceImpl {
    @Autowired
    private ItemRepository repositoryItem;
    
    @Autowired
    private ProdutoRepository repositoryProduto;

    @Transactional
    public Item cadastrarItem(Item item) throws IllegalArgumentException {
        if(repositoryItem.findByCodigo(item.getCodigo()).isPresent()) {

            throw new IllegalArgumentException("> item com codigo='"+item.getCodigo()+"' já existe!");
        } else if(repositoryProduto.findByCodigo(item.getProduto().getCodigo()).isPresent()) {
            
            throw new IllegalArgumentException("> produto com esse codigo='"+item.getProduto().getCodigo()+"' já existe!");
        } else if(repositoryProduto.findByNome(item.getProduto().getNome()).isPresent()) {
            
            throw new IllegalArgumentException("> produto com esse nome='"+item.getProduto().getNome()+"' já existe!");
        }

        return repositoryItem.save(item);
    }

    public Page<Item> listarItems(Pageable paginacao) {
        Page<Item> itens;

        try {
            itens = repositoryItem.findAll(paginacao);
        } catch(NoDataFoundException e) {
            
            throw new NoDataFoundException("> Nenhum dado de item encontrado");
        }

        return itens;
    }

    public Item pesquisarItemPorIdentificador(Long id) {
        return repositoryItem
            .findById(id)
            .orElseThrow(() ->
                new EntityNotFoundException("> Item com o codigo='"+id+"' não encontrado!")
            );
    }

    @Transactional
    public void removerItem(Long id) {
        try {
            repositoryItem.deleteById(id);
            repositoryProduto.deleteById(
                pesquisarItemPorIdentificador(id)
                .getProduto().getId()
            );
        } catch(EntityNotFoundException e) {
            throw new EntityNotFoundException("> Item ou produto não encontrado pelos respectivos identificadores");
        }
    }
}
