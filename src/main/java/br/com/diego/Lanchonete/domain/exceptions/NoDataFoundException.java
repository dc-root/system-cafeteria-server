package br.com.diego.Lanchonete.domain.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class NoDataFoundException extends RuntimeException {
    public NoDataFoundException(String mensagem){
        super(mensagem);
    }
}
