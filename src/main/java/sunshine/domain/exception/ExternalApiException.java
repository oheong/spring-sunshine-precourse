package sunshine.domain.exception;

public class ExternalApiException extends RuntimeException {
    public ExternalApiException() {
        super("External API call failed.");
    }

    public ExternalApiException(String message) {
        super(message);
    }

    public ExternalApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
