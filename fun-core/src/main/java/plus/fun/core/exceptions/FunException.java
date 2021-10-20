package plus.fun.core.exceptions;

public class FunException extends RuntimeException {

    private ErrorDetails errorDetails;

    public FunException() {
        super();
        this.errorDetails = new ErrorDetails(0, null);
    }

    public FunException(String message) {
        super(message);
        this.errorDetails = new ErrorDetails(0, message);
    }

    public FunException(String message, Throwable throwable) {
        super(message, throwable);
        this.errorDetails = new ErrorDetails(0, message);
    }

    public FunException(ErrorDetails errorDetails) {
        super(errorDetails.getMessage());
        this.errorDetails = errorDetails;
    }

    public FunException(ErrorDetails errorDetails, Throwable throwable) {
        super(errorDetails.getMessage(), throwable);
        this.errorDetails = errorDetails;
    }

    public ErrorDetails getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(ErrorDetails errorDetails) {
        this.errorDetails = errorDetails;
    }
}
