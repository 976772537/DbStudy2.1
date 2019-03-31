package exception;

public class EssayNotFoundException extends Exception {
    public EssayNotFoundException() {
    }

    public EssayNotFoundException(String message) {
        super (message);
    }

    public EssayNotFoundException(String message, Throwable cause) {
        super (message, cause);
    }

    public EssayNotFoundException(Throwable cause) {
        super (cause);
    }

    public EssayNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super (message, cause, enableSuppression, writableStackTrace);
    }
}
