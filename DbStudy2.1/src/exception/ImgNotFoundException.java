package exception;

public class ImgNotFoundException extends Exception{
    public ImgNotFoundException() {
    }

    public ImgNotFoundException(String message) {
        super (message);
    }

    public ImgNotFoundException(String message, Throwable cause) {
        super (message, cause);
    }

    public ImgNotFoundException(Throwable cause) {
        super (cause);
    }

    public ImgNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super (message, cause, enableSuppression, writableStackTrace);
    }
}
