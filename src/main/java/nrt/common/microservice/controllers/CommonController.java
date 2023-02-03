package nrt.common.microservice.controllers;

import lombok.extern.slf4j.Slf4j;
import nrt.common.microservice.exceptions.CommonBusinessException;
import nrt.common.microservice.models.dto.BaseDTO;
import nrt.common.microservice.services.CommonService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Common Controller Layer. This abstract class provides the basics endpoints to do a CRUD
 * This class receive 2 parameters a FilterDTO and a DTO
 *
 * @param <F>
 * @param <DTO>
 * @author nahueltabasso
 */
@Slf4j
public abstract class CommonController<F extends Object, DTO extends BaseDTO> {

    protected abstract CommonService getCommonService();

    @PostMapping("/search")
    public ResponseEntity<?> search(@RequestBody F filterDTO) {
        log.info("Enter to search()");
        Page<DTO> page = this.getCommonService().searchCustom(filterDTO, null);
        return ResponseEntity.ok().body(page);
    }

//    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping
    public ResponseEntity<?> getAll() {
        log.info("Enter to getAll()");
        return ResponseEntity.ok().body(this.getCommonService().findAll());
    }

//    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        log.info("Enter to getOne()");
        DTO dto = (DTO) this.getCommonService().findById(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(dto);
    }

//    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    public ResponseEntity<?> create(@Validated @RequestBody DTO dto, BindingResult bindingResult) throws Exception {
        log.info("Enter to create()");
        Map<String, Object> response = new HashMap<>();
        if (bindingResult.hasErrors()) {
            return this.validateBody(bindingResult);
        }
        DTO dtoResponse = null;
        try {
            dtoResponse = (DTO) this.getCommonService().save(dto);
        } catch (CommonBusinessException e) {
            response.put("message", "Internar Server Error!");
            response.put("error", e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        } catch (Exception e) {
            response.put("message", "Internar Server Error!");
            response.put("error", e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoResponse);
    }

//    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        log.info("Enter to delete()");
        try {
            this.getCommonService().deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (CommonBusinessException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    protected ResponseEntity<?> validateBody(BindingResult bindingResult) {
        Map<String, Object> errors = new HashMap<>();
        bindingResult.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "Field " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

}
