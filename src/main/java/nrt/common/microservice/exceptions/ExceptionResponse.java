package nrt.common.microservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Error object
 *
 * @author nahueltabasso
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExceptionResponse {

    private Integer httpStatus;
    private LocalDateTime timestamp;
    private String message;
    private String description;
}
