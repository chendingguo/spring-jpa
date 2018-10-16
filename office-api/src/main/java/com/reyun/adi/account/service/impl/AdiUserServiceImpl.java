package com.reyun.adi.account.service.impl;

import com.reyun.adi.account.dic.AdiErrorCodeEnum;
import com.reyun.adi.account.dic.PackageEnum;
import com.reyun.adi.account.dic.StatusEnum;
import com.reyun.adi.account.dic.TypeEnum;
import com.reyun.adi.account.model.User;
import com.reyun.adi.account.repository.AdiUserRepository;
import com.reyun.adi.account.service.AdiUserService;
import com.reyun.util.CipherUtil;
import org.apache.commons.lang.StringUtils;
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
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class AdiUserServiceImpl implements AdiUserService {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private AdiUserRepository userRepository;


    @Override
    public Long createUser(User user) {
        long result = checkCreateParamter(user);
        if (result != 0) {
            return result;
        }
        user.setCreateTime(new Date());

        user.setPassword(CipherUtil.generatePassword(user.getPassword()));
        user.setRegIpAddr(WebUtils.getIpAddr(request));
        //user.setRegIpLocation(IPAddrUtil.getLocationFromIpAddr(user.getRegIpAddr()));
        return userRepository.save(user).getId();
    }

    @Override
    public Long updateUser(User user) {

        long id = user.getId();
        User userBean = userRepository.findOne(id);

        if (StringUtils.isNotEmpty(user.getCompany())) {
            userBean.setCompany(user.getCompany());

        }
        if (user.getCreateAccount() != null && user.getCreateAccount() > 0) {

            userBean.setCreateAccount(user.getCreateAccount());
        }

        if (user.getDelFlag() != null) {
            userBean.setDelFlag(user.getDelFlag());
        }
        if (user.getEmail() != null) {
            userBean.setEmail(user.getEmail());
        }
        if (StringUtils.isNotEmpty(user.getPhone())) {
            userBean.setPhone(user.getPhone());
        }
        if (user.getExpriedTime() != null) {
            userBean.setExpriedTime(user.getExpriedTime());
        }
        if (user.getModifyAccount() != null) {
            userBean.setModifyAccount(user.getModifyAccount());
        }

        if (StringUtils.isNotEmpty(user.getPassword())) {
            userBean.setPassword(user.getPassword());
        }
        if (user.getWhetherCompany() != null) {
            userBean.setWhetherCompany(user.getWhetherCompany());
        }

        if (user.getOnTrial() != null) {
            userBean.setOnTrial(user.getOnTrial());
        }
        if (user.getStatus() != null) {
            userBean.setStatus(user.getStatus());
        }
        if (user.getZoneId() != null) {
            userBean.setZoneId(user.getZoneId());
        }
        userBean.setModifyTime(new Date());

        return userRepository.save(userBean).getId();
    }


    @Override
    public Page<User> listUsers(int pageIndex, int pageSize, String keyword, int status, int type, int packageValue) {
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = new PageRequest(pageIndex - 1, pageSize, sort);


        Specification<User> specification = getSpecification(keyword, status, type, packageValue);
        Page<User> userResult = userRepository.findAll(specification, pageable);
        List<User> users = userResult.getContent();
        for (User user : users) {
            user.setPassword("");
            user.setStatusStr(StatusEnum.valueOf(user.getStatus()).getValue());
            if (user.getWhetherCompany()) {
                user.setWheterCompanyStr("企业账号");
            } else {
                user.setWheterCompanyStr("普通账号");
            }
            user.setOnTrialStr(PackageEnum.valueOf(user.getOnTrial()).getValue());
        }




        return userResult;

    }

    public Specification<User> getSpecification(String keyword, Integer status, int type, int packageValue) {
        Specification<User> specification = (Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

            Predicate predicate = cb.conjunction();
            if (status > -1) {
                predicate.getExpressions().add(
                        cb.equal(root.get("status"), status));
            }

            if (type > -1) {
                //普通用户
                if (type == 0) {
                    predicate.getExpressions().add(
                            cb.equal(root.get("whetherCompany"), false));
                } else {
                    //企业用户
                    predicate.getExpressions().add(
                            cb.equal(root.get("whetherCompany"), true));
                }

            }
            if (packageValue > -1) {
                predicate.getExpressions().add(
                        cb.equal(root.get("onTrial"), packageValue));
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

    /**
     * 参数 检查
     *
     * @param user
     * @return
     */
    public long checkCreateParamter(User user) {
        String email = user.getEmail();

        Specification<User> specification = (Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            Predicate predicate = cb.conjunction();
            predicate.getExpressions().add(
                    cb.equal(root.get("email"), email));
            return predicate;
        };
        User userBean = userRepository.findOne(specification);
        if (userBean != null) {
            return AdiErrorCodeEnum.EMAIL_REPEATED_ERROR.getCode();
        }

        return 0;
    }


}
