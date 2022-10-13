package br.com.diego.Lanchonete.repositorys;

import br.com.diego.Lanchonete.templates.Cliente;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {}