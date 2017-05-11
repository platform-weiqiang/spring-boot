package com.shiro.controller;

import org.apache.shiro.authc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LogonController {

    private static final Logger log= LoggerFactory.getLogger(LogonController.class);

    @RequestMapping(value = "/logon",method = RequestMethod.POST)
    public String logon(@RequestParam("username")String username,@RequestParam("password")String password){
        //1.创建Subject实例
        Subject currentUser=SecurityUtils.getSubject();
        //2.判断当前用户是否登陆
        if (currentUser.isAuthenticated()==false){
            //3.将用户名以及密码封装UsernamePasswordToken
            UsernamePasswordToken token=new UsernamePasswordToken(username,password);
            try {
                currentUser.login(token);
                log.info("用户名和密码正确，登陆成功！");
            }catch (AuthenticationException e){
                log.info("登陆失败！");
                return "error";
            }/*catch (IncorrectCredentialsException e){
                log.info("密码错误！");
            }catch (LockedAccountException e){
                log.info("用户已经锁死");
            }catch (AuthenticationException e){
                log.info("认证异常");
            }*/
        }
       return "success";
    }

}
