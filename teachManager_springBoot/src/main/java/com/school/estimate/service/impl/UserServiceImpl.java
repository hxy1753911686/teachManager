package com.school.estimate.service.impl;

import com.school.estimate.dao.UserDao;
import com.school.estimate.domain.User;
import com.school.estimate.domain.User_Role;
import com.school.estimate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> findByName(String name) {
        return userDao.findByName(name);
    }

    @Override
    public User findUserById(Long id) {
        return userDao.findUserById(id);
    }

    @Override
    public List<User> findAllUser() {
        return userDao.findAllUser();
    }

    @Override
    public List<User> findUserByPage(Integer start, Integer size) {
        return userDao.findUserByPage(start, size);
    }

    @Override
    public Long saveUser(User user, String roleIds) {
        Long line = null;
        //使用BCryptPasswordEncoder保存初始密码
        String encode = new BCryptPasswordEncoder().encode("666666");
        user.setPassword(encode);
        line = userDao.saveUser(user);
        if (line < 1) {
            return line;
        }
        Long newId = userDao.getNewId();
        User_Role user_role = new User_Role();
        user_role.setUserId(newId.intValue());
        user_role.setRoleIds(roleIds);
        line = userDao.saveUserRole(user_role);

        return line;
    }

    @Override
    public Long updateUser(User user, String roleIds, Integer userRoleId) {
        Long line = userDao.updateUser(user);
        if (line < 1) {
            return line;
        }

        User_Role user_role = new User_Role();
        user_role.setRoleIds(roleIds);
        user_role.setId(userRoleId);
        line = userDao.updateUserRole(user_role);
        return line;
    }

    @Override
    public Long deleteUser(Long id) {
        Long aLong = userDao.deleteUserRole(id);
        if (aLong < 1) {
            return aLong;
        }
        return userDao.deleteUser(id);
    }

    @Override
    public Long delMulUser(String idList) {
        String[] idArr = idList.split(",");
        int i = 0;
        for (String s : idArr) {
            deleteUser(Long.parseLong(s));
            i++;
        }
        return Long.valueOf(i);
    }

    @Override
    public User_Role findUserRoleOfUserId(Long id) {
        return userDao.findUserRoleOfUserId(id);
    }
}
