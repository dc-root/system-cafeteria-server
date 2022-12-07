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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.diego.Lanchonete.domain.service.ItemServiceImpl;
import br.com.diego.Lanchonete.domain.templates.Item;

@RestController
@RequestMapping("/itens")
public class ItemController {
    
    @Autowired
    private ItemServiceImpl serviceItem;

    @PostMapping
    public Item register(@RequestBody @Valid Item dadosItem) {
        return serviceItem.cadastrarItem(dadosItem);
    }

    @GetMapping
    public Page<Item> list(@PageableDefault(size = 10, sort = { "id" }) Pageable paginacao) {
        return serviceItem.listarItems(paginacao);
    }

    @GetMapping("/{id}")
    public Item search(@PathVariable(value="id") Long itemId) {
        return serviceItem.pesquisarItemPorIdentificador(itemId);
    }

    @DeleteMapping("/{id}")
    public void remove(
        @PathVariable(value="id") Long itemId
    ) {
        serviceItem.removerItem(itemId);
    }
}