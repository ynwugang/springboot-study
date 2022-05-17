package com.example.config;

import com.example.pojo.User;
import com.example.service.impl.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义的UserRealm   extends AuthorizingRealm
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    UserServiceImpl userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了=>授权doGetAuthorizationInfo");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        info.addStringPermission("user:add");

        //拿到当前登陆的这个对象
        Subject subject = SecurityUtils.getSubject();
        //拿到user对象
        User currentUser = (User) subject.getPrincipal();

        //设置当前用户权限
        info.addStringPermission(currentUser.getPerms());

        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了=>认证doGetAuthenticationInfo");

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        //连接真实数据库
        User user = userService.queryUserByName(token.getUsername());
        if (user == null){ //没有这个人
            return null; //此处return null 会抛出一个UnknownAccountException异常
        }

        //保存user
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("loginUser", user);

        //可以加密：MD5，MD5盐值加密
        //密码认证，shiro做
        return new SimpleAuthenticationInfo(user, user.getPwd(), "");
    }
}
