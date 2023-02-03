package nrt.common.microservice.services;

import nrt.common.microservice.models.dto.BaseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Common Service Layer
 * This interface provides the signs to a common CRUD
 *
 * @param <DTO> (Data Transfer Object)
 * @author nahueltabasso
 */
public interface CommonService<DTO extends BaseDTO>  {

    public Page<DTO> searchCustom(Object filterDTO, Pageable pageable);
    public Iterable<DTO> findAll();
    public DTO findById(Long id);
    public DTO save(DTO dto) throws Exception;
    public void deleteById(Long id);
}
