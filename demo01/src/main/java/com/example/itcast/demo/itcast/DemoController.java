package com.example.itcast.demo.itcast;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @RequestMapping("/hello")
    @ResponseBody
    public String demo(){
        String sql="sddd";
       String sqlq="dwd,weldkewdlwed";
        return sql+sqlq;
    }
}
