package com.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.List;

//Realm 需要查询数据库，并且得到正确的数据
public class SecondShiroRealm extends AuthenticatingRealm {


    /**
     * @param token
     * @return
     * @throws AuthenticationException
     * 1.doGetAuthenticationInfo获取认证消息，如果数据库中没有数据，返回null，如果得到正确的用户名和密码，返回制定类型的对象
     * 2.AuthenticationInfo
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        System.out.println("The Second Real is working");

        SimpleAuthenticationInfo info=null;
        //1.将token转换成UsernamePasswordToken
        UsernamePasswordToken upToken= (UsernamePasswordToken) token;
        //2.获取用户名即可
        String userName=upToken.getUsername();
        //3.查询数据库，是否存在指定的用户名和密码
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/shiro";
            Connection conn=DriverManager.getConnection(url,"root","admin");
            PreparedStatement ps=conn.prepareStatement("SELECT * FROM users WHERE username=?");
            ps.setString(1,userName);
            ResultSet rs=ps.executeQuery();
            if (rs.next()){
                Object principal=userName;
                Object credentials=rs.getString(3);
                String realName=this.getName();
                ByteSource salt=ByteSource.Util.bytes(userName);
                //加密MD5以及盐值加密
                SimpleHash sh=new SimpleHash("SHA1",credentials,salt,1024);
                //System.out.println(sh);
                //4.如果查询到了，封装查询结果，返回给我们的调用
                //info = new SimpleAuthenticationInfo(principal,credentials,realName);
                info = new SimpleAuthenticationInfo(principal,sh,salt,realName);
            }else {
                //5.如果没有查询到，抛出一个异常
               throw new AuthenticationException("用户名或密码错误！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }

    public boolean isPermitted(PrincipalCollection principalCollection, String s) {

        return false;
    }

    public boolean isPermitted(PrincipalCollection principalCollection, Permission permission) {

        return false;
    }

    public boolean[] isPermitted(PrincipalCollection principalCollection, String... strings) {
        return new boolean[0];
    }

    public boolean[] isPermitted(PrincipalCollection principalCollection, List<Permission> list) {
        return new boolean[0];
    }

    public boolean isPermittedAll(PrincipalCollection principalCollection, String... strings) {
        return false;
    }

    public boolean isPermittedAll(PrincipalCollection principalCollection, Collection<Permission> collection) {
        return false;
    }

    public void checkPermission(PrincipalCollection principalCollection, String s) throws AuthorizationException {

    }

    public void checkPermission(PrincipalCollection principalCollection, Permission permission) throws AuthorizationException {

    }

    public void checkPermissions(PrincipalCollection principalCollection, String... strings) throws AuthorizationException {

    }

    public void checkPermissions(PrincipalCollection principalCollection, Collection<Permission> collection) throws AuthorizationException {

    }

    public boolean hasRole(PrincipalCollection principalCollection, String s) {
        return false;
    }

    public boolean[] hasRoles(PrincipalCollection principalCollection, List<String> list) {
        return new boolean[0];
    }

    public boolean hasAllRoles(PrincipalCollection principalCollection, Collection<String> collection) {
        return false;
    }

    public void checkRole(PrincipalCollection principalCollection, String s) throws AuthorizationException {

    }

    public void checkRoles(PrincipalCollection principalCollection, Collection<String> collection) throws AuthorizationException {

    }

    public void checkRoles(PrincipalCollection principalCollection, String... strings) throws AuthorizationException {

    }
}
