package br.com.diego.Lanchonete.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.diego.Lanchonete.domain.templates.cliente.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {}
