package nrt.common.microservice.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    @Column(name = "creationdatetime")
    @CreatedDate
    protected LocalDateTime creationDateTime;
    @Column(name = "creationuser")
    @CreatedBy
    protected String creationUser;
    @Column(name = "modificationdatetime")
    @LastModifiedDate
    protected LocalDateTime modificationDateTime;
    @Column(name = "modificationuser")
    @LastModifiedBy
    protected String modificationUser;
    @Column(name = "version")
    protected Boolean version;
    @Column(name = "deleted")
    protected Boolean deleted;

}
