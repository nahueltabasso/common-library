package nrt.common.microservice.models.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Common Repository for entities with a relational schema
 * @param <E>
 * @author nahueltabasso
 */
@NoRepositoryBean
public interface CommonEntityRepository<E> extends PagingAndSortingRepository<E, Long>, JpaSpecificationExecutor<E> {
}
