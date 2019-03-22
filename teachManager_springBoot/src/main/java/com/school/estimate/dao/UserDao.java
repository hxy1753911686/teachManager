package com.school.estimate.dao;

import com.school.estimate.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    List<User> findByName(@Param("name") String name);
    User findUserById(@Param("id") Long id);
    List<User> findAllUser();
    Long saveUser(User user);
    Long updateUser(User user);
    Long deleteUser(@Param("id") Long id);
}
