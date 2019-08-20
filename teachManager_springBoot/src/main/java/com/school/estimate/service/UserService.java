package com.school.estimate.service;

import com.school.estimate.domain.User;
import com.school.estimate.domain.User_Role;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    List<User> findByName(String name);

    User findUserById(Long id);

    List<User> findAllUser();

    List<User> findUserByPage(Integer start, Integer size);

    @Transactional
    Long saveUser(User user,String roleIds);

    @Transactional
    Long updateUser(User user,String roleIds,Integer userRoleId);

    @Transactional
    Long deleteUser(Long id);
    @Transactional
    Long delMulUser(String idList);

    User_Role findUserRoleOfUserId(Long id);
}
