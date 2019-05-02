package com.school.estimate.service;

import com.school.estimate.domain.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    List<User> findByName(String name);

    User findUserById(Long id);

    List<User> findAllUser();

    List<User> findUserByPage(Integer start, Integer size);

    @Transactional
    Long saveUser(User user);

    Long updateUser(User user);

    @Transactional
    Long deleteUser(Long id) throws Exception;
}
