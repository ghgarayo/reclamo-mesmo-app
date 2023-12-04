package reclamo.mesmo.app.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException e) {
        var erros = e.getFieldErrors();

        return ResponseEntity.badRequest().body(
                erros.stream().map(
                        erro -> new dadosErrosValidacao(erro.getField(), erro.getDefaultMessage())
                )
        );
    }

    private record dadosErrosValidacao(String campo, String mensagem) {

    }

}

