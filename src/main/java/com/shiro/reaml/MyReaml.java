package com.shiro.reaml;

import com.dao.UserDao;
import com.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义Reaml
 * Create by HP on 2020/6/2
 * 游魂
 */
public class MyReaml extends AuthorizingRealm {
    @Autowired
    UserDao userDao;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(userDao.queryRoleByLoginname(principalCollection.toString()));
        simpleAuthorizationInfo.setStringPermissions(userDao.queryPresByLoginname(principalCollection.toString()));
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String loginname = authenticationToken.getPrincipal().toString();
        User user = userDao.queryByLoginname(loginname);
        if(null == user){
            throw new UnknownAccountException("用户名不存在！");
        } else{
            SecurityUtils.getSubject().getSession().setAttribute("user", user);
            return new SimpleAuthenticationInfo(loginname, user.getPassword(), this.getName());
        }
    }
}
