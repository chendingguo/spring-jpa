package com.reyun.adi.account.service;

import com.reyun.adi.account.model.Media;
import com.reyun.adi.account.model.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface AdiUserService {
     Page<User> listUsers(int pageIndex, int pageSize);

    /**
     * 创建用户
     * @param user
     * @return
     */
    Long createUser(User user);

    /**
     * 更新
     * @param user
     * @return
     */
    Long updateUser(User user);


}
