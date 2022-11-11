package br.com.diego.Lanchonete.service.Exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
//    @ExceptionHandler(EntidadeNaoEncontradaException.class)
//    public ResponseEntity<?> tratarEntidadeNaoEncontradaException(
//            EntidadeNaoEncontradaException ex, WebRequest request
//    ) {
//        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(),
//                HttpStatus.NOT_FOUND, request);
//    }

//    @Override
//    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        if(body == null) {
//            body = Problema.builder()
//                    .dataHora(OffsetDataTime.now())
//                    .mesagem(status.getResponsePhrase())
//                    .build();
//        } else if (body instanceof String) {
//            body = Problema.builder()
//                    .dataHora(OffsetDateTime.now())
//                    .mesagem((String) body)
//                    .build();
//        }
//
//        return super.handleExceptionInternal(ex, body, headers, status, request);
//    }
}
