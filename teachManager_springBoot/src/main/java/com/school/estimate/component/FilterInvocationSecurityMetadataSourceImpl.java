package com.school.estimate.component;

import com.school.estimate.domain.Permission;
import com.school.estimate.domain.Role;
import com.school.estimate.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
//用来接收用户请求的地址，返回访问该地址需要的权限
public class FilterInvocationSecurityMetadataSourceImpl implements FilterInvocationSecurityMetadataSource {
    @Autowired
    private PermissionService permissionService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) o).getRequestUrl();

        //判断用户访问的地址是否为登录页面
        if("/login".equals(requestUrl)){
            return null;
        }

        Permission permission = permissionService.getPermissionByUrl(requestUrl);

        //如果permission说明未加权限，所有人都可以访问(给页面加LOGIN的权限)
        if(permission == null){
            return SecurityConfig.createList("ROLE_LOGIN");
        }

        List<Role> roles = permissionService.getRolesByPermissionId(permission.getId().longValue());
        int size = roles.size();
        String[] values = new String[size];
        for (int i = 0; i < size; i++) {
            values[i] = roles.get(i).getRoleCode();
        }
        return SecurityConfig.createList(values);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
