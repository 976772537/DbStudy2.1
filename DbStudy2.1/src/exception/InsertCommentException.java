package exception;

public class InsertCommentException extends Exception{
    public InsertCommentException() {
    }

    public InsertCommentException(String message) {
        super (message);
    }

    public InsertCommentException(String message, Throwable cause) {
        super (message, cause);
    }

    public InsertCommentException(Throwable cause) {
        super (cause);
    }

    public InsertCommentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super (message, cause, enableSuppression, writableStackTrace);
    }
}
