package com.springboot.security.security.support;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Appctx {

    public static ApplicationContext ctx=null;

    public static Object getObject(String string){
        return ctx.getBean(string);
    }
}
