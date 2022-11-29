package br.com.diego.Lanchonete.domain.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class SucessMessageException extends RuntimeException {
    public SucessMessageException(String mensagem){
        super(mensagem);
    }
}
