package exception;

public class SavePersonInfoFiledException extends  Exception {
    public SavePersonInfoFiledException() {
    }

    public SavePersonInfoFiledException(String message) {
        super (message);
    }

    public SavePersonInfoFiledException(String message, Throwable cause) {
        super (message, cause);
    }

    public SavePersonInfoFiledException(Throwable cause) {
        super (cause);
    }

    public SavePersonInfoFiledException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super (message, cause, enableSuppression, writableStackTrace);
    }
}
