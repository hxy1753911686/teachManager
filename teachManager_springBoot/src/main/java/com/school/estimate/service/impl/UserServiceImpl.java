package com.school.estimate.service.impl;

import com.school.estimate.dao.UserDao;
import com.school.estimate.domain.User;
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
    public Long saveUser(User user) {
        Long line = null;
        try {
            //使用BCryptPasswordEncoder保存初始密码
            String encode = new BCryptPasswordEncoder().encode("666666");
            user.setPassword(encode);
            line = userDao.saveUser(user);
        } catch (Exception e) {
            //已添加事务注解，输出日志
            System.err.println(e);
            return new Long(0);
        }
        return line;
    }

    @Override
    public Long updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public Long deleteUser(Long id) throws Exception {
        if (id.intValue() == 8) {
            throw new Exception("不能删除");
        }
        return userDao.deleteUser(id);
    }
}
