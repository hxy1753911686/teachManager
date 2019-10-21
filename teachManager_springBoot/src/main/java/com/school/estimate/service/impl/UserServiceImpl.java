package com.school.estimate.service.impl;

import com.school.estimate.dao.UserDao;
import com.school.estimate.domain.User;
import com.school.estimate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
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
        Integer newId = user.getId();
        String[] roleArr = roleIds.split(",");
        List<String> roleList = Arrays.asList(roleArr);
        line = userDao.saveUserRole(newId.longValue(),roleList);

        return line;
    }

    @Override
    public Long updateUser(User user, String roleIds) {
        Long line = userDao.updateUser(user);
        if (line < 1) {
            return line;
        }

        List<Integer> userList = new ArrayList<>();
        userList.add(user.getId());
        userDao.deleteUserRole(userList);

        Integer updateId = user.getId();
        String[] roleArr = roleIds.split(",");
        List<String> roleList = Arrays.asList(roleArr);
        line = userDao.saveUserRole(updateId.longValue(),roleList);
        return line;
    }

    @Override
    public Long deleteUser(Long id) {
        List<Integer> userList = new ArrayList<>();
        userList.add(id.intValue());
        Long aLong = userDao.deleteUserRole(userList);
        if (aLong < 1) {
            return aLong;
        }
        return userDao.deleteUser(userList);
    }

    @Override
    public Long delMulUser(String idList) {
        List<Integer> userList = new ArrayList<>();
        String[] idArr = idList.split(",");
        for (String s : idArr) {
            userList.add(Integer.valueOf(s));
        }

        Long aLong = userDao.deleteUserRole(userList);
        if (aLong < 1) {
            return aLong;
        }

        return userDao.deleteUser(userList);
    }

    @Override
    public List<Integer> findRoleIdByUserId(Long id) {
        return userDao.findRoleIdByUserId(id);
    }
}
