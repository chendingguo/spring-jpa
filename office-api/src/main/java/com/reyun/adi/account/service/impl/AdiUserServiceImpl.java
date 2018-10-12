package com.reyun.adi.account.service.impl;

import com.reyun.adi.account.model.User;
import com.reyun.adi.account.repository.AdiUserRepository;
import com.reyun.adi.account.service.AdiUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


@Service
public class AdiUserServiceImpl implements AdiUserService {
    @Autowired
    private AdiUserRepository userRepository;


    @Override
    public Long createUser(User user) {

        return userRepository.save(user).getId();
    }

    @Override
    public Long updateUser(User user) {

        return null;
    }


    @Override
    public Page<User> listUsers(int pageIndex, int pageSize) {
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = new PageRequest(pageIndex - 1, pageSize, sort);


        Specification<User> specification = getSpecification("cdg", 1);
       return userRepository.findAll(specification, pageable);
    }

    public Specification<User> getSpecification(String keyword, Integer status) {
        Specification<User> specification = (Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

//            List<Predicate> predicates = new ArrayList<>();
//            if (!keyword.isEmpty()) {
//                Predicate pn = cb.like(root.get("name"), keyword);
//                Predicate pe = cb.like(root.get("email"), keyword);
//                predicates.add(pn);
//                predicates.add(pe);
//            }
//
//            Predicate[] likeArray = new Predicate[predicates.size()];
//            Predicate or = cb.or(predicates.toArray(likeArray));
//
//            List<Predicate> predicateList = new ArrayList<>();
//            if (status != -1) {
//                Predicate statusPre = cb.equal(root.get("status"), status);
//                predicateList.add(statusPre);
//            }
//            Predicate[] andArray = new Predicate[predicateList.size()];
//            Predicate and = cb.and(predicateList.toArray(andArray));


            Predicate predicate = cb.conjunction();
            if (status > -1) {
                predicate.getExpressions().add(
                        cb.equal(root.get("status"), status));
            }

            List<Predicate> predicates = new ArrayList<>();
            if (!keyword.isEmpty()) {
                Predicate pn = cb.like(root.get("name"), keyword);
                Predicate pe = cb.like(root.get("email"), keyword);
                predicates.add(pn);
                predicates.add(pe);
                Predicate[] array = new Predicate[predicates.size()];
                Predicate orPredicate = cb.or(predicates.toArray(array));
                predicate.getExpressions().add(orPredicate);
            }


            return predicate;


        };


        return specification;
    }

}
