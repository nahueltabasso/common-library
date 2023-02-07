package nrt.common.microservice.specification;

import nrt.common.microservice.models.entity.CommonEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * This class provide an abstract filter specification for customs queries. This class receive 2 parameters
 * an Entity class and a Filter class
 *
 * @param <E>
 * @param <F>
 * @author nahueltabasso
 */
public abstract class CommonSpecification<E extends CommonEntity, F extends Object> {

    public Specification<E> getCustomSpecification(F filter) {
        return new Specification<E>() {
            @Override
            public Predicate toPredicate(Root<E> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (filter != null) return buildFilterConditions(filter, root, query, criteriaBuilder);

                return null;
            }
        };
    }

    protected abstract Predicate buildFilterConditions(F filter, Root<E> root, CriteriaQuery<?> criteriaQuery,
                                                       CriteriaBuilder criteriaBuilder);
}
