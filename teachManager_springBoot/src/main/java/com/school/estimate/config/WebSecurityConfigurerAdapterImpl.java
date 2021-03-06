package com.school.estimate.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.ui.ModelMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class WebSecurityConfigurerAdapterImpl extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private FilterInvocationSecurityMetadataSource fise;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private AccessDecisionManager accessDecisionManager;

    @Override
    /*
     *  定义认证用户信息获取来源，密码校验规则...
     */
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //从内存中获取，测试的时候用,预先设置好用户名密码及其权限
        //auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("root").password(new BCryptPasswordEncoder().encode("123123")).roles("USER");
        //如果要继续添加，则继续跟.and().withUser().....

        //另一种方式为通过userDetailsService接口获取
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());

    }

    @Override
    //配置哪些页面不需要认证
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**").antMatchers("/layui/**").antMatchers("/fonts/**").antMatchers("/js/**").antMatchers("/images/**").antMatchers("/font-awesome-4.7.0/**").antMatchers("/module/**");
        //antMatchers("/")
    }

    @Override
    /*
     * 安全定义策略
     */
    protected void configure(HttpSecurity http) throws Exception {
        /*
            解决：
            Refused to display 'http://127.0.0.1:8070/default_sso_heartbeat.html'
            in a frame because it set 'X-Frame-Options' to 'DENY'.
         */
        http.headers().frameOptions().disable().and()
                .authorizeRequests()    //标准开头,以下每个小模块之间使用.and()分割
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setSecurityMetadataSource(fise);
                        o.setAccessDecisionManager(accessDecisionManager);
                        return o;
                    }
                })
                .and()
                .formLogin()            //配置登录页面，用户名及密码参数
                .loginPage("/login")        //登录页面，不配置则为security默认页面
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()        //所有人可以访问

                //登录成功及失败处理
//                .failureHandler(new AuthenticationFailureHandler() {
//                    @Override
//                    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
//                        ModelMap map = new ModelMap();
//                        map.put("loginError","用户名或密码错误");
//                    }
//                })
//                .successHandler(new AuthenticationSuccessHandler() {
//                    @Override
//                    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
//                        //登录成功跳转
//                        httpServletResponse.setContentType("application/json;charset=utf-8");
//                        PrintWriter out = httpServletResponse.getWriter();
//                        ObjectMapper objectMapper = new ObjectMapper();
//                        String s = "{\"status\":\"success\",\"msg\":"  + "}";
//                        out.write(s);
//                        out.flush();
//                        out.close();
//                    }
//                })
                .failureForwardUrl("/loginError")
                .successForwardUrl("/loginSuccess")       //如果用successForwardUrl,要求跳转的请求为post请求
                .and()
                .logout()
                .permitAll()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler);
    }
}
