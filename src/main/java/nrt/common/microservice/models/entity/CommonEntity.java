package nrt.common.microservice.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nrt.common.microservice.security.session.AppSessionUser;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Abstract base Common Entity persistence object. This object class provides and object identification and
 * commons auditory fields
 *
 * @author nahueltabasso
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class CommonEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;
    @Column(name = "creationdatetime", updatable = true)
    @CreatedDate
    protected LocalDateTime creationDateTime;
    @Column(name = "creationuser", updatable = true)
    @CreatedBy
    protected String creationUser;
    @Column(name = "modificationdatetime", updatable = true)
    @LastModifiedDate
    protected LocalDateTime modificationDateTime;
    @Column(name = "modificationuser", updatable = true)
    @LastModifiedBy
    protected String modificationUser;
    @Column(name = "version", updatable = true)
    protected Integer version;
    @Column(name = "deleted", updatable = true)
    protected Boolean deleted;

    @PrePersist
    public void prePersist() {
        // First time to persist in database (create operation)
        this.creationDateTime = LocalDateTime.now();
        this.creationUser = "admin";
        this.modificationDateTime = LocalDateTime.now();
        this.modificationUser = "admin";
        this.version = 0;
        this.deleted = false;
    }

    @PreUpdate
    public void preUpdate() {
        // Update operation
        this.creationDateTime = this.creationDateTime;
        this.creationUser = this.creationUser;
        this.modificationDateTime = LocalDateTime.now();
        if (AppSessionUser.getCurrentAppUser() != null) {
            this.modificationUser = AppSessionUser.getCurrentAppUser().getUsername();
        } else {
            this.modificationUser = "admin";
        }
        this.version = this.version + 1;
        this.deleted = this.deleted;
    }
}
