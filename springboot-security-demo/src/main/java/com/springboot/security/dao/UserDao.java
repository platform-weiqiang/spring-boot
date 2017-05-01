package com.springboot.security.dao;

import com.springboot.security.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void update(SysUser su) {
        String sql="UPDATE s_user SET password = '"+su.getPassword()+"' WHERE id = "+su.getId();
        jdbcTemplate.update(sql);
    }
}
