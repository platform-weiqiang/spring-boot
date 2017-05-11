package com.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

//Realm 需要查询数据库，并且得到正确的数据
public class ShiroRealm extends AuthorizingRealm {

    /**
     * @param token
     * @return
     * @throws AuthenticationException
     * 1.doGetAuthenticationInfo获取认证消息，如果数据库中没有数据，返回null，如果得到正确的用户名和密码，返回制定类型的对象
     * 2.AuthenticationInfo
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        System.out.println("The First Real is working");
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
                SimpleHash sh=new SimpleHash("MD5",credentials,salt,1024);
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

    /**
     * 获取用户所用的角色
     * @param principals
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //返回值：AuthorizationInfo,封装获取用户对应的角色 ，SimpleAuthorizationInfo(Set<String>)
        SimpleAuthorizationInfo info=null;
        //参数列表PrincipalCollection用户名即身份
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/shiro";
            Connection conn=DriverManager.getConnection(url,"root","admin");
            PreparedStatement ps=conn.prepareStatement("SELECT * FROM users WHERE username=?");
            ps.setString(1,principals.toString());
            ResultSet rs=ps.executeQuery();
            if (rs.next()){
                Set<String> roles=new HashSet<String>();
                roles.add(rs.getString(4));
                info = new SimpleAuthorizationInfo(roles);
            }else {
                //5.如果没有查询到，抛出一个异常
                throw new AuthenticationException();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }
}
