package nrt.common.microservice.exceptions;

/**
 * Common Exception Class. This class allows to handle custom system exceptions
 *
 * @author nahueltabasso
 */
public class CommonBusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CommonBusinessException(String message) {
        super(message);
    }
}
