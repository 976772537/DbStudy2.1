package exception;

public class AddAnnunciateFaildException extends Exception {
    public AddAnnunciateFaildException() {
    }

    public AddAnnunciateFaildException(String message) {
        super (message);
    }

    public AddAnnunciateFaildException(String message, Throwable cause) {
        super (message, cause);
    }

    public AddAnnunciateFaildException(Throwable cause) {
        super (cause);
    }

    public AddAnnunciateFaildException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super (message, cause, enableSuppression, writableStackTrace);
    }
}
