package br.com.diego.Lanchonete.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface GenericRepository<T> extends JpaRepository<T, Long> {
    List<T> findByNome(String nome);
    Optional<T> findByCpf(String cpf);
}