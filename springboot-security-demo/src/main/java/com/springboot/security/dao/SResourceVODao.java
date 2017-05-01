package com.springboot.security.dao;

import com.springboot.security.entity.vo.SysResourceVo;
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
public class SResourceVODao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String,Object>> findByRoleName(String auth) {
        String sql="SELECT s.id,s.role_id roleId,s.resource_id resourceId,r.name,c.resource_url resourceUrl," +
                " c.resource_name resourceName,c.method_name methodName,c.method_path methodPath FROM s_resource_role s" +
                " LEFT JOIN s_role r ON s.role_id=r.id LEFT JOIN s_resource c ON s.resource_id=c.resource_id" +
                " WHERE r.name=?";
        RowMapper<SysResourceVo> rowMapper=new BeanPropertyRowMapper<>(SysResourceVo.class);
        List<SysResourceVo> list=jdbcTemplate.query(sql,new Object[]{auth},rowMapper);
        List<Map<String,Object>> resourceList=new ArrayList<>();
        if (list != null && list.size()>0){
            for (SysResourceVo resourceVo:list){
                Map<String,Object> map=new HashMap<>();
                map.put("id",resourceVo.getId());
                map.put("roleId",resourceVo.getRoleId());
                map.put("resourceId",resourceVo.getResourceId());
                map.put("name",resourceVo.getName());
                map.put("resourceUrl",resourceVo.getResourceUrl());
                map.put("resourceName",resourceVo.getResourceName());
                map.put("methodName",resourceVo.getMethodName());
                map.put("methodPath",resourceVo.getMethodPath());
                resourceList.add(map);
            }
        }
        return resourceList;
    }
}
