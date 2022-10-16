package br.com.diego.Lanchonete.repositorys;

import org.springframework.stereotype.Repository;

import br.com.diego.Lanchonete.templates.Client;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {}