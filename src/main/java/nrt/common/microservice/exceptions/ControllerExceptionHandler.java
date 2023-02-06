package nrt.common.microservice.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.Locale;

/**
 * This class provides a global exception handling for the web system
 *
 * @author nahueltabasso
 */
@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    protected ExceptionResponse handleResourceNotFoundExceptionBusinessException(ResourceNotFoundException ex,
                                                                                 WebRequest request) {
        ex.printStackTrace();
        ExceptionResponse error = new ExceptionResponse(
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),
                ex.getMessage(),
                messageSource.getMessage(ex.getMessage(), null, Locale.US),
                request.getDescription(false)
        );
        log.error("An error of the type " + ResourceNotFoundException.class.getName() + " ocurred!");
        log.info(error.toString());
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
                messageSource.getMessage(ex.getMessage(), null, Locale.US),
                request.getDescription(false)
        );
        log.error("An error of the type " + CommonBusinessException.class.getName() + " ocurred!");
        log.info(error.toString());
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
                messageSource.getMessage(ex.getMessage(), null, Locale.US),
                request.getDescription(false)
        );
        log.error("An error of the type " + Exception.class.getName() + " ocurred!");
        log.info(error.toString());
        return error;
    }

}
