package com.springboot.security.entity.vo;

import java.io.Serializable;

public class SysResourceVo implements Serializable{

    private Integer id;
    private String roleId;
    private String resourceId;
    private String name;//角色的名称
    private String resourceUrl;//url
    private String resourceName;//资源名称
    private String methodName;//资源所对应的方法名
    private String methodPath;//资源所对应的包路径

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getRoleId() {
        return roleId;
    }
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    public String getResourceId() {
        return resourceId;
    }
    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getResourceUrl() {
        return resourceUrl;
    }
    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }
    public String getResourceName() {
        return resourceName;
    }
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
    public String getMethodName() {
        return methodName;
    }
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
    public String getMethodPath() {
        return methodPath;
    }
    public void setMethodPath(String methodPath) {
        this.methodPath = methodPath;
    }
}
