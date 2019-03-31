package exception;

public class FindTestFailedException extends Exception {
    public FindTestFailedException() {
    }

    public FindTestFailedException(String message) {
        super (message);
    }

    public FindTestFailedException(String message, Throwable cause) {
        super (message, cause);
    }

    public FindTestFailedException(Throwable cause) {
        super (cause);
    }

    public FindTestFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super (message, cause, enableSuppression, writableStackTrace);
    }
}
