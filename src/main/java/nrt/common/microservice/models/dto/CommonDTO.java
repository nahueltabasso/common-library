package nrt.common.microservice.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Abstract base Common DTO (Data Transfer Object) object. This object class provides object identification and
 *
 * @author nahueltabasso
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class CommonDTO implements BaseDTO {

    protected Long id;
}
