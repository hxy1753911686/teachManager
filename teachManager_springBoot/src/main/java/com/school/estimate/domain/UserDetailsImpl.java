package com.school.estimate.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {

    private String username;
    private String password;
    private List<Role> roles;

    public UserDetailsImpl() {
    }

    public UserDetailsImpl(User user, List<Role> roles) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.roles = roles;
    }

    public UserDetailsImpl(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }


    @Override
    //返回用户所有角色的封装，一个Role对应一个GrantedAuthority
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleCode()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    //判断账号是否过期，from redis
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    //判断账号是否被锁定
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    //判断信用凭证是否过期
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    //判断账号是否可用
    public boolean isEnabled() {
        return true;
    }
}
