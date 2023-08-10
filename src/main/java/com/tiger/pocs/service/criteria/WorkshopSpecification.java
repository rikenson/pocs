package com.tiger.pocs.service.criteria;

import com.tiger.pocs.domain.entity.Workshop;
import com.tiger.pocs.domain.filter.WorkshopFilter;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WorkshopSpecification implements Specification<Workshop> {

    private static final String NAME = "name";
    private static final String STATUS = "status";
    private static final String START_DATE_TIME = "startDateTime";
    private static final String END_DATE_TIME = "endDateTime";
    private final WorkshopFilter filter;

    public WorkshopSpecification(WorkshopFilter filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(@NonNull Root<Workshop> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        Predicate result = null;

        if (Objects.nonNull(filter.getName()))
            predicates.add(criteriaBuilder.equal(root.get(NAME), filter.getName()));
        if (Objects.nonNull(filter.getName()))
            predicates.add(criteriaBuilder.equal(root.get(STATUS), filter.getStatus()));
        if (Objects.nonNull(filter.getName()))
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(START_DATE_TIME), filter.getEndDateTime()));
        if (Objects.nonNull(filter.getName()))
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(END_DATE_TIME), filter.getEndDateTime()));
        if (!CollectionUtils.isEmpty(predicates))
            result = predicates.size() == 1 ? predicates.get(0) : criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        return result;
    }
}
