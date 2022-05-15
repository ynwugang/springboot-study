package com.wugang.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //首页所有人可以访问，功能页只有对应有权限的人能访问

        //请求授权的规则
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("vip1")
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3");


        //没有权限默认到登录页,需要开启登陆的页面
            //  /login
        http.formLogin().loginPage("/toLogin").usernameParameter("user").passwordParameter("pwd").loginProcessingUrl("/login");

        //注销，开启注销功能，跳到首页
        http.logout().logoutSuccessUrl("/");

        //防止网站攻击：
        http.csrf().disable(); //关闭csrf功能，默认是开启的，登出失败的原因

        //开启记住我功能  cookie  默认保存两周
        //自定义接收前段参数
        http.rememberMe().rememberMeParameter("remember");
    }

    //认证
    //密码编码：PasswordEncoder
    //在Spring Security 5.0+ 新增了很多的加密方法~
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //正常应该从数据库读取
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("zhangs").password(new BCryptPasswordEncoder().encode("123456")).roles("vip2", "vip3")
                .and()
                .withUser("root").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1", "vip2", "vip3")
                .and()
                .withUser("guest").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1");

    }
}
