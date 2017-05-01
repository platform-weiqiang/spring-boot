package com.springboot.security.dao;

import com.springboot.security.entity.vo.SysRoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SRoleVODao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String,Object>> findAll() {
        String sql="SELECT r.id,r.name,r.uid,u.name AS userName,u.email,u.password,u.dob FROM s_role r " +
                   "LEFT JOIN s_user u ON r.uid=u.id";
        RowMapper<SysRoleVO> rowMapper=new BeanPropertyRowMapper<>(SysRoleVO.class);
        List<SysRoleVO> list=jdbcTemplate.query(sql,new Object[]{},rowMapper);
        List<Map<String,Object>> roleList=new ArrayList<>();
        if (list.size()>0 && list != null){
            for (SysRoleVO role:list){
                Map<String,Object> map=new HashMap<>();
                map.put("id",role.getId());
                map.put("name",role.getName());
                map.put("uid",role.getUid());
                map.put("userName",role.getUserName());
                map.put("email",role.getEmail());
                map.put("password",role.getPassword());
                map.put("dob",role.getDob());
                roleList.add(map);
            }
        }
        return roleList;
    }
}
