package com.reyun.adi.account.service;

import com.reyun.adi.account.model.Media;
import com.reyun.adi.account.model.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface AdiUserService {
     Page<User> listUsers(int pageIndex, int pageSize);

    Long createUser(User user);
    Long updateUser(User user);


}
