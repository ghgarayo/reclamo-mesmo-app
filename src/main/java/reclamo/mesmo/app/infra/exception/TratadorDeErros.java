package reclamo.mesmo.app.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.hibernate.query.QueryArgumentException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> tratarErro404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> tratarErro400(MethodArgumentNotValidException e) {
        var erros = e.getFieldErrors();

        return ResponseEntity.badRequest().body(
                erros.stream().map(
                        erro -> new dadosErrosValidacao(erro.getField(), erro.getDefaultMessage())
                )
        );
    }

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity<?> tratarErro400(ValidacaoException e) {
        return ResponseEntity.badRequest().body(new DTOValidationExceptionError(e.getMessage()));
    }

    @ExceptionHandler(NullPointerException.class)   
    public ResponseEntity<?> tratarErro500(NullPointerException e) {
        return ResponseEntity.internalServerError().body(new DTOValidationExceptionError(e.getMessage()));
    }

    @ExceptionHandler(QueryArgumentException.class)
    public ResponseEntity<?> tratarErro(QueryArgumentException e) {
        return ResponseEntity.internalServerError().body(new DTOValidationExceptionError(e.getMessage()));
    }

    private record dadosErrosValidacao(String campo, String mensagem) {

    }

    private record DTOValidationExceptionError(String mensagem) {

    }

}

