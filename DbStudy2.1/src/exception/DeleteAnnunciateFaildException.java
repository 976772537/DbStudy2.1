package exception;

public class DeleteAnnunciateFaildException extends Exception {
    public DeleteAnnunciateFaildException() {
    }

    public DeleteAnnunciateFaildException(String message) {
        super (message);
    }

    public DeleteAnnunciateFaildException(String message, Throwable cause) {
        super (message, cause);
    }

    public DeleteAnnunciateFaildException(Throwable cause) {
        super (cause);
    }

    public DeleteAnnunciateFaildException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super (message, cause, enableSuppression, writableStackTrace);
    }
}
