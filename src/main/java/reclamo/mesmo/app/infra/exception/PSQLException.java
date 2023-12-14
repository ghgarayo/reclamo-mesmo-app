package reclamo.mesmo.app.infra.exception;

public class PSQLException extends Throwable {
    public PSQLException(String message) {
        super(message);
    }
}
