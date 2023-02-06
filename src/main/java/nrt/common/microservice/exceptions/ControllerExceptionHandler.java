package nrt.common.microservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

/**
 * This class provides a global exception handling for the web system
 *
 * @author nahueltabasso
 */
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    protected ExceptionResponse handleResourceNotFoundExceptionBusinessException(ResourceNotFoundException ex,
                                                                                 WebRequest request) {
        ex.printStackTrace();
        ExceptionResponse error = new ExceptionResponse(
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return error;
    }

    @ExceptionHandler(CommonBusinessException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected ExceptionResponse handleCommonBusinessException(CommonBusinessException ex, WebRequest request) {
        ex.printStackTrace();
        ExceptionResponse error = new ExceptionResponse(
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return error;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    protected ExceptionResponse handleException(Exception ex, WebRequest request) {
        ex.printStackTrace();
        ExceptionResponse error = new ExceptionResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return error;
    }

}
