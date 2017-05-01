package com.springboot.security.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "s_resource")
public class SysResource implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 11)
    private Integer id;

    @Column(name = "resourceUrl",length = 1000)
    private String resourceUrl;//url
    @Column(name = "resourceId",length = 50)
    private String resourceId;//资源ID
    @Column(name = "remark",length = 300)
    private String remark;//备注
    @Column(name = "resourceName",length = 400)
    private String resourceName;//资源名称
    @Column(name = "methodName",length = 400)
    private String methodName;//资源所对应的方法名
    @Column(name = "methodPath",length = 1000)
    private String methodPath;//资源所对应的包路径

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
