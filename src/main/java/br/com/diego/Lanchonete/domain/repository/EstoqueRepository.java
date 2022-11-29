package br.com.diego.Lanchonete.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.diego.Lanchonete.domain.templates.Estoque;

//@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long> {}