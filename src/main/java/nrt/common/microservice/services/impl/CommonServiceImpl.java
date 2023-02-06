package nrt.common.microservice.services.impl;

import lombok.extern.slf4j.Slf4j;
import nrt.common.microservice.exceptions.CommonBusinessException;
import nrt.common.microservice.exceptions.ResourceNotFoundException;
import nrt.common.microservice.models.dto.CommonDTO;
import nrt.common.microservice.models.entity.CommonEntity;
import nrt.common.microservice.models.repository.CommonEntityRepository;
import nrt.common.microservice.services.CommonService;
import nrt.common.microservice.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the Common Service Layer. This abstract class receive 2 parameters a DTO and an Entity
 *
 * @param <DTO>
 * @param <E>
 * @author nahueltabasso
 */
@Slf4j
public abstract class CommonServiceImpl<DTO extends CommonDTO, E extends CommonEntity> implements CommonService<DTO> {

    @Autowired
    protected abstract CommonEntityRepository<E> getCommonRepository();

    protected abstract E dtoToEntity(DTO dto);

    protected abstract DTO entityToDto(E entity);

    @Override
    public Page<DTO> searchCustom(Object filterDTO, Pageable pageable) {
        log.info("Enter to searchCustom");
        Page<DTO> page = this.getCommonRepository().findAll(pageable).map(this::entityToDto);
        return page;
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<DTO> findAll() {
        log.info("Enter to findAll()");
        List<E> entities = (List<E>) this.getCommonRepository().findAll();
        List<DTO> dtoList = new ArrayList<>();
        if (Utils.isNullOrEmpty(entities)) return dtoList;

        dtoList = entities.stream().map(e -> this.entityToDto(e)).collect(Collectors.toList());
        return dtoList;
    }

    @Override
    @Transactional(readOnly = true)
    public DTO findById(Long id) {
        log.info("Enter to findById()");
        E entity = this.getCommonRepository().findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found row with id = " + id));
        DTO dto = this.entityToDto(entity);
        return dto;
    }

    @Override
    @Transactional
    public DTO save(DTO dto) throws Exception {
        log.info("Enter to save()");
        try {
            E entity = this.dtoToEntity(dto);
            E entityDb = (E) this.getCommonRepository().save(entity);
            return this.entityToDto(entityDb);
        } catch (CommonBusinessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        log.info("Enter to deleteById()");
        this.getCommonRepository().deleteById(id);
    }
}
