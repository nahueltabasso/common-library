package nrt.common.microservice.controllers;

import nrt.common.microservice.services.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class CommonController<E, S extends CommonService<E>> {

    protected final static Logger logger = LoggerFactory.getLogger(CommonController.class);
    @Autowired
    protected S service;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping
    public ResponseEntity<?> getAll() {
        logger.info("Enter to getAll()");
        return ResponseEntity.ok().body(service.findAll());
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        logger.info("Enter to getOne()");
        Optional<E> opt = service.findById(id);
        if (!opt.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(opt.get());
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody E entity, BindingResult bindingResult) throws Exception {
        logger.info("Enter to create()");
        Map<String, Object> response = new HashMap<>();
        if (bindingResult.hasErrors()) {
            return this.validateBody(bindingResult);
        }
        E entityDB = null;
        try {
            entityDB = (E) service.save(entity);
        } catch (Exception e) {
            response.put("message", "Internar Server Error!");
            response.put("error", e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(entityDB);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        logger.info("Enter to delete()");
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    protected ResponseEntity<?> validateBody(BindingResult bindingResult) {
        Map<String, Object> errors = new HashMap<>();
        bindingResult.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "Field " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

}
