package reclamo.mesmo.app.infra.exception;

public class ValidacaoException extends RuntimeException{
    public ValidacaoException(String message) {
        super(message);
    }
}