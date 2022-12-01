package br.com.diego.Lanchonete.api.controller;

import javax.validation.Valid;

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

import br.com.diego.Lanchonete.domain.service.ProdutoServiceImpl;
import br.com.diego.Lanchonete.domain.templates.Produto;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    
    @Autowired
    private ProdutoServiceImpl serviceProduto;

    @PostMapping
    public Produto register(@RequestBody @Valid Produto dadosProduto) {
        return serviceProduto.cadastrarProduto(dadosProduto);
    }

    @GetMapping
    public Page<Produto> list(@PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
        return serviceProduto.listarProdutos(paginacao);
    }

    @GetMapping("/{id}")
    public Produto search(@PathVariable(value="id") Long produtoId) {
        return serviceProduto.pesquisarProdutoPeloCodigo(produtoId);
    }

    @PutMapping("/{id}")
    public Produto update(
        @RequestBody @Valid Produto produtoData,
        @PathVariable(value="id") Long produtoId
    ) {
        return serviceProduto.atualizarProduto(produtoData, produtoId);
    }

    @DeleteMapping("/{id}")
    public void remove(
        @PathVariable(value="id") Long produtoId
    ) {
        serviceProduto.removerProduto(produtoId);
    }
}