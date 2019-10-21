package com.school.estimate.dao;

import com.school.estimate.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    List<User> findByName(@Param("name") String name);

    User findUserById(@Param("id") Long id);

    List<User> findAllUser();

    List<User> findUserByPage(@Param("start")Integer start,@Param("size")Integer size);

    Long saveUser(User user);
    Long saveUserRole(@Param("userId")Long userId,List<String> roleList);

    Long updateUser(User user);

    Long deleteUser(List userList);
    Long deleteUserRole(List userList);

    List<Integer> findRoleIdByUserId(@Param("id")Long id);
}
