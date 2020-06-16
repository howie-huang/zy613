package com.exam.zy613.util;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.exam.zy613.entity.User;
import com.exam.zy613.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义authReaml类
 * 进行认证和授权
 * @author 31515
 */
public class AuthReaml extends AuthorizingRealm {
    @Autowired
    private UserService service;
    /**
     * 授权方法
     * @param principalCollection
     * @return AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权");
        //实际找数据库获取角色列表和权限列表
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
//        User accountEntity = (User) principalCollection.getPrimaryPrincipal();
//        info.addStringPermissions(service.selectFunByUserId(accountEntity.getUserId()));
//        info.addRoles(service.selectRoleByUserId(accountEntity.getUserId()));
        return info;
    }

    /**
     * 认证方法：subject.login()会自动调用这个realm方法
     * @param authenticationToken
     * @return AuthenticationInfo
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("认证");
        UsernamePasswordToken token= (UsernamePasswordToken) authenticationToken;
        /*
                getPassword获取的是一个char数组
                token.getUsername():去数据库查有没有，如果没有抛出没有的异常
                ——根据用户名找密码，密码对比，如果密码不对抛出密码不对异常》。。。
         */
        Wrapper<User> wrapper = new EntityWrapper<>();
        wrapper.eq("login_name",token.getUsername());
        User accountEntity=service.selectOne(wrapper);
        if(accountEntity==null){
            throw new UnknownAccountException();
        }else{
            /*
                第一个参数可以是用户名也可以是用户对象 getPrincipal获取的和这对应
                第二个传入是数据库查询的密码
                第三个自定义realm是谁
                这个方法会自动触发比较器，token中的密码以及你自己查询出来的密码进行对比
             */

            SimpleAuthenticationInfo info
                    =new SimpleAuthenticationInfo(accountEntity,accountEntity.getPassword(),this.getName());
            return info;
        }
    }
}
