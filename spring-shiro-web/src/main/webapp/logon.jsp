<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Shiro登陆页面</title>
</head>
<body>
    <h3>logon page</h3>
    <form action="/logon" method="post">
        username:<input type="text" name="username"/>
        <br>
        password:<input type="password" name="password"/>
        <br>
        <input type="submit" value="logon"/>
    </form>
</body>
</html>
