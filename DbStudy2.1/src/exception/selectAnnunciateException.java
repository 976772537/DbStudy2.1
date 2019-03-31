package exception;

public class selectAnnunciateException extends Exception{
    public selectAnnunciateException() {
    }

    public selectAnnunciateException(String message) {
        super (message);
    }

    public selectAnnunciateException(String message, Throwable cause) {
        super (message, cause);
    }

    public selectAnnunciateException(Throwable cause) {
        super (cause);
    }

    public selectAnnunciateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super (message, cause, enableSuppression, writableStackTrace);
    }
}
