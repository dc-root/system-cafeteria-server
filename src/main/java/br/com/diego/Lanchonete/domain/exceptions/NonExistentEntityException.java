package br.com.diego.Lanchonete.domain.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

// @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "> Entidade não encontrada!")
@ResponseStatus
public class NonExistentEntityException extends RuntimeException {
    
    public NonExistentEntityException(String mensagem){
        super(mensagem);
    }
}