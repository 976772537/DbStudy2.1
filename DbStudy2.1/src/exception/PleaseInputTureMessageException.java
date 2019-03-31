package exception;

public class PleaseInputTureMessageException extends Throwable {
    public PleaseInputTureMessageException() {
    }

    public PleaseInputTureMessageException(String message) {
        super (message);
    }

    public PleaseInputTureMessageException(String message, Throwable cause) {
        super (message, cause);
    }

    public PleaseInputTureMessageException(Throwable cause) {
        super (cause);
    }

    public PleaseInputTureMessageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super (message, cause, enableSuppression, writableStackTrace);
    }
}
