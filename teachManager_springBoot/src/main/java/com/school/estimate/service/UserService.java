package com.school.estimate.service;

import com.school.estimate.domain.User;

import java.util.List;

public interface UserService {
    List<User> findByName(String name);

    User findUserById(Long id);

    List<User> findAllUser();

    List<User> findUserByPage(Integer start, Integer size);

    Long saveUser(User user);

    Long updateUser(User user);

    Long deleteUser(Long id);
}
