package com.school.estimate.dao;

import com.school.estimate.domain.User;
import com.school.estimate.domain.User_Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserDao {
    List<User> findByName(@Param("name") String name);

    User findUserById(@Param("id") Long id);

    List<User> findAllUser();

    List<User> findUserByPage(@Param("start")Integer start,@Param("size")Integer size);

    Long saveUser(User user);
    Long saveUserRole(User_Role user_role);

    Long updateUser(User user);
    Long updateUserRole(User_Role user_role);

    Long deleteUser(@Param("id") Long id);
    Long deleteUserRole(@Param("id")Long userId);

    Long getNewId();

    User_Role findUserRoleOfUserId(@Param("id")Long id);
}
