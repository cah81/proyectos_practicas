package com.formacion.BS7_2.person.infraestructure.repository;



import com.formacion.BS7_2.person.criteria.PersonPage;
import com.formacion.BS7_2.person.criteria.SearchFilterPerson;
import com.formacion.BS7_2.person.domain.model.Person;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;


import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.*;




@Repository
public class PersonCriteriaDao {

    @PersistenceContext
    private EntityManager entityManager;

    private final CriteriaBuilder criteriaBuilder;

    public PersonCriteriaDao(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Person> findAllPersonWithCriteria(PersonPage personPage, SearchFilterPerson searchFilterPerson){
        CriteriaQuery<Person> criteriaQuery = criteriaBuilder.createQuery(Person.class);
        Root<Person> personRoot = criteriaQuery.from(Person.class);
        Predicate predicate = getPredicate(searchFilterPerson,personRoot);
        criteriaQuery.where(predicate);
        setOrder(personPage,criteriaQuery,personRoot);
        TypedQuery<Person> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(personPage.getPageNumber()*personPage.getPageSize());
        typedQuery.setMaxResults(personPage.getPageSize());
        Pageable pageable = getPageable(personPage);
        long personCount = getPersonCount(predicate);
        return new PageImpl<>(typedQuery.getResultList(),pageable,personCount);
    }


    private Predicate getPredicate(SearchFilterPerson searchFilterPersonfilter, Root personRoot) {
        List<Predicate> predicates = new ArrayList<>();
        if(Objects.nonNull(searchFilterPersonfilter.getUsername())){
            predicates.add(criteriaBuilder.like(personRoot.get("username"),
                    "%" + searchFilterPersonfilter.getUsername()+ "%"));
        }
        if(Objects.nonNull(searchFilterPersonfilter.getName())){
            predicates.add(criteriaBuilder.like(personRoot.get("name"),
                    "%" + searchFilterPersonfilter.getName()+ "%"));
        }
        if(Objects.nonNull(searchFilterPersonfilter.getSurname())){
            predicates.add(criteriaBuilder.like(personRoot.get("surname"),
                    "%" + searchFilterPersonfilter.getSurname()+ "%"));
        }

        if (Objects.nonNull(searchFilterPersonfilter.getCreated_date())) {

            if(searchFilterPersonfilter.getDateCriteria() != null) {
                if (searchFilterPersonfilter.getDateCriteria().equals("prev"))
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(personRoot.get("created_date"), searchFilterPersonfilter.getCreated_date()));
            }
            else
                predicates.add(criteriaBuilder.lessThan(personRoot.get("created_date"), searchFilterPersonfilter.getCreated_date()));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(PersonPage personPage, CriteriaQuery criteriaQuery, Root personRoot) {
        if(personPage.getSortDirection().equals(Sort.Direction.ASC)){
            criteriaQuery.orderBy(criteriaBuilder.asc(personRoot.get(personPage.getSortBy())));
        }else{
            criteriaQuery.orderBy(criteriaBuilder.desc(personRoot.get(personPage.getSortBy())));
        }
    }
    private Pageable getPageable(PersonPage personPage) {
        Sort sort = Sort.by(personPage.getSortDirection(),personPage.getSortBy());
        return PageRequest.of(personPage.getPageNumber(),personPage.getPageSize(),sort);
    }

    private long getPersonCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Person> countRoot = countQuery.from(Person.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }
}



