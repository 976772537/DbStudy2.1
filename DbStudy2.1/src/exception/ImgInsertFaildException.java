package exception;

public class ImgInsertFaildException extends Exception {
    public ImgInsertFaildException() {
    }

    public ImgInsertFaildException(String message) {
        super (message);
    }

    public ImgInsertFaildException(String message, Throwable cause) {
        super (message, cause);
    }

    public ImgInsertFaildException(Throwable cause) {
        super (cause);
    }

    public ImgInsertFaildException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super (message, cause, enableSuppression, writableStackTrace);
    }
}
