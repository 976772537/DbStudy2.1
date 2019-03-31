package exception;

public class insertTestFailedException extends Exception {
    public insertTestFailedException() {
    }

    public insertTestFailedException(String message) {
        super (message);
    }

    public insertTestFailedException(String message, Throwable cause) {
        super (message, cause);
    }

    public insertTestFailedException(Throwable cause) {
        super (cause);
    }

    public insertTestFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super (message, cause, enableSuppression, writableStackTrace);
    }
}
