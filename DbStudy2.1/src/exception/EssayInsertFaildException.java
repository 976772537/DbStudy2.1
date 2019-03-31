package exception;

public class EssayInsertFaildException extends Exception {
    public EssayInsertFaildException() {
    }

    public EssayInsertFaildException(String message) {
        super (message);
    }

    public EssayInsertFaildException(String message, Throwable cause) {
        super (message, cause);
    }

    public EssayInsertFaildException(Throwable cause) {
        super (cause);
    }

    public EssayInsertFaildException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super (message, cause, enableSuppression, writableStackTrace);
    }
}
