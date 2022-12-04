package br.com.diego.Lanchonete.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.diego.Lanchonete.domain.templates.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByCodigo(String codigo);
}