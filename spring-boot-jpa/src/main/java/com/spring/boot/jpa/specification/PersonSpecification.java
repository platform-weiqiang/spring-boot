package com.spring.boot.jpa.specification;

import com.spring.boot.jpa.entity.Person;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Specification构造查询条件
 */
public class PersonSpecification {

    public static Specification<Person> personFromByName(String name) {
        return new Specification<Person>() {
            @Override
            public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                return cb.equal(root.get("name"),name);
            }
        };
    }

}
