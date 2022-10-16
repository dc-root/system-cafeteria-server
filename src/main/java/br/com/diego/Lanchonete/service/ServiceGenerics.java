package br.com.diego.Lanchonete.service;

import java.util.List;

public interface ServiceGenerics<E> {
    E save(E entity);
    List<E> list();
    E search(Long id);
    void remove(E entity);
}
