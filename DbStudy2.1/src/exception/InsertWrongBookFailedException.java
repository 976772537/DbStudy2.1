package exception;

public class InsertWrongBookFailedException extends Exception {
    public InsertWrongBookFailedException() {
    }

    public InsertWrongBookFailedException(String message) {
        super (message);
    }

    public InsertWrongBookFailedException(String message, Throwable cause) {
        super (message, cause);
    }

    public InsertWrongBookFailedException(Throwable cause) {
        super (cause);
    }

    public InsertWrongBookFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super (message, cause, enableSuppression, writableStackTrace);
    }
}
