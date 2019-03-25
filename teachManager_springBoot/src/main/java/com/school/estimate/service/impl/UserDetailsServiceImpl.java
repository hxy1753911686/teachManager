package com.school.estimate.service.impl;

import com.school.estimate.dao.UserDao;
import com.school.estimate.domain.User;
import com.school.estimate.domain.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl  implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleServiceImpl roleService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.findByName(s).get(0);
        if(user == null){
            throw  new UsernameNotFoundException("没有该用户");
        }

        return new UserDetailsImpl(user,roleService.findRolesOfUserId(user.getId().longValue()));
    }
}
