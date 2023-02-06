package nrt.common.microservice.exceptions;

/**
 * Custom Exception to not found object
 *
 * @author nahueltabasso
 */
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
