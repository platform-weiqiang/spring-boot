package com.springboot.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.springboot.security.dao.SResourceVODao;
import com.springboot.security.security.support.Appctx;
import com.springboot.security.security.support.MySecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
@EnableAutoConfiguration(exclude = MySecurityFilter.class)
public class SpringbootSecurityDemoApplication {

	@Autowired
	private SResourceVODao sResourceVODao;

	private static final Logger log= LoggerFactory.getLogger(SpringbootSecurityDemoApplication.class);

	public void initApplication()throws IOException{
		log.info("Running with Spring profile(s) : {}");
	}

	public static void main(String[] args) {
		//SpringApplication.run(SpringbootSecurityDemoApplication.class, args);
		SpringApplication app = new SpringApplication(SpringbootSecurityDemoApplication.class);
		Appctx.ctx=app.run(args);
	    /*UserService suserService = (UserService) Appctx.ctx.getBean("userService");
        SysUser su= suserService.findByName("TEST");
        BCryptPasswordEncoder bc=new BCryptPasswordEncoder(4);//将密码加密 可以先设置初始密码：000000
        su.setPassword(bc.encode(su.getPassword()));//然后使用密码为key值进行加密，运行主类后，会自动加密密码，可连接数据库查看。
        System.out.println("密码"+su.getPassword());
        suserService.update(su);//运行一次后记得注释这段重复加密会无法匹配*/

	}
}
