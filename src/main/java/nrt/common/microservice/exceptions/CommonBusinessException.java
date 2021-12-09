package nrt.common.microservice.exceptions;

public class CommonBusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CommonBusinessException(String message) {
        super(message);
    }
}
