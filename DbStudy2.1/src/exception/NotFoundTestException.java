package exception;

public class NotFoundTestException extends Throwable {
    public NotFoundTestException() {
    }

    public NotFoundTestException(String message) {
        super (message);
    }

    public NotFoundTestException(String message, Throwable cause) {
        super (message, cause);
    }

    public NotFoundTestException(Throwable cause) {
        super (cause);
    }

    public NotFoundTestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super (message, cause, enableSuppression, writableStackTrace);
    }
}
