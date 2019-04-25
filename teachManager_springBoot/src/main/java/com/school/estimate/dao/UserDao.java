package com.school.estimate.dao;

import com.school.estimate.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserDao {
    List<User> findByName(@Param("name") String name);

    User findUserById(@Param("id") Long id);

    List<User> findAllUser();

    List<User> findUserByPage(@Param("start")Integer start,@Param("size")Integer size);

    Long saveUser(User user);

    Long updateUser(User user);

    Long deleteUser(@Param("id") Long id);
}
