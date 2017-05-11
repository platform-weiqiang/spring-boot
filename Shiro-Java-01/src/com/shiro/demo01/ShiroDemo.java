package com.shiro.demo01;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShiroDemo {

    private static final Logger log=LoggerFactory.getLogger(ShiroDemo.class);

    public static void main(String[] args){
        /**
         * 1.获取安全管理器
         * 2.获取用户
         * 3.用户登陆验证
         * 4.权限管理
         * 5.角色管理
         * 6.session :用户登陆到用户的退出。
         */

        //1.获取安全管理器
        Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager=factory.getInstance();

        //2.设置安全管理器
        SecurityUtils.setSecurityManager(securityManager);

        //3.获取Subject对象，即将登陆的用户
        Subject currentUser=SecurityUtils.getSubject();

        Session session=currentUser.getSession();

        session.setAttribute("name","朋伟强");

        String name= (String) session.getAttribute("name");
        if (StringUtils.isNotBlank(name)){
            log.info(name);
        }else{
            log.info("无用户的信息");
        }

        //验证用户是否登陆过
        if (currentUser.isAuthenticated()==false){
            UsernamePasswordToken token=new UsernamePasswordToken("lonestarr","vespa");
            token.setRememberMe(true);
            try {
               currentUser.login(token);
               log.info("用户名和密码正确，登陆成功！");
            }catch (UnknownAccountException e){
                log.info("账户不存在！");
            }catch (IncorrectCredentialsException e){
                log.info("密码错误！");
            }catch (LockedAccountException e){
                log.info("用户已经锁死");
            }catch (AuthenticationException e){
                log.info("认证异常");
            }
        }

        //判断用户是否拥有制定的角色
        if (currentUser.hasRole("goodguy")){
            log.info("拥有制定的角色");
        }else {
            log.info("不拥有指定的角色");
        };

        //是否拥有指定的权限
        if (currentUser.isPermitted("lightsaber:*")){
            log.info("拥有指定的权限");
        }else{
            log.info("不拥有指定的权限");
        }

    }
}
