package com.springboot.security.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户表
 */
@Entity
@Table(name = "s_user")
public class SysUser implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",unique = true,nullable = false)
    private Integer id;
    @Column(name = "name",length = 120)
    private String name;//用户名
    @Column(name = "email",length = 100)
    private String email;//用户邮箱
    @Column(name = "password", length = 225)
    private String password;//用户密码
    @Temporal(TemporalType.DATE)
    @Column(name = "dob",length = 20)
    private Date dob;//时间

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "SUser")
    private Set<SysRole> sysRoles=new HashSet<>();// 所对应的角色集合

    public SysUser(){}

    public SysUser(String name, String email, String password, Date dob, Set<SysRole> sysRoles) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.sysRoles = sysRoles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "SUser")
    public Set<SysRole> getSysRoles() {
        return sysRoles;
    }

    public void setSysRoles(Set<SysRole> sysRoles) {
        this.sysRoles = sysRoles;
    }
}
