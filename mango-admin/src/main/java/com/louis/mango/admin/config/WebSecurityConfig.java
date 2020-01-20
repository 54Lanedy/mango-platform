package com.louis.mango.admin.config;

import com.louis.mango.admin.security.JwtAuthenticationFilter;
import com.louis.mango.admin.security.JwtAuthenticationProvider;
import io.jsonwebtoken.JwtHandlerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

/**
 * 系统安全框架配置
 * Created by liyue
 * Time 2019-09-18 18:16
 */
@Configuration
@EnableWebSecurity //开启spring security
@EnableGlobalMethodSecurity(prePostEnabled = true)  //开启权限注释，如：@PreAuthorize注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //spring security 版本在5.0后就要加个PasswordEncoder了
        //There is no PasswordEncoder mapped for the id "null"解决方法
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //禁用csrf，由于使用的是jwt，这里不需要csrf
        http.cors().and().csrf().disable().authorizeRequests()
            //跨域预检请求
            .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
            //web jars
            .antMatchers("/webjars/**").permitAll()
            //查看SQL监控（druid）
            .antMatchers("/druid/**").permitAll()
            //首页和登陆页面
            .antMatchers("/").permitAll()
            .antMatchers("/login").permitAll()
            //swagger
            .antMatchers("/swagger-ui.html").permitAll()
            .antMatchers("/swagger-resources/**").permitAll()
            .antMatchers("/v2/api-docs").permitAll()
            .antMatchers("/webjars/springfox-swagger-ui/**").permitAll()
            //验证码
            .antMatchers("/captcha.jpg**").permitAll()
            //服务监控
            .antMatchers("/actuator/**").permitAll()
            //其他所有请求需要身份认证
            .anyRequest().authenticated();
        //Spring Security默认时不允许页面被嵌套的，所以X-Frame-Options默认为DENY，设置disable禁用默认值
        http.headers().frameOptions().disable();
        //退出处理器
        http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
        //开启登录认证流程过滤器，如果使用LoginController的login接口, 需要注释掉此过滤器，根据使用习惯二选一即可
//        http.addFilterBefore(new JwtLoginFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);
        // 访问控制时登录状态检查过滤器
        http.addFilterBefore(new JwtAuthenticationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
