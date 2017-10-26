<%--
  Created by IntelliJ IDEA.
  User: L1ANN
  Date: 2017/10/17
  Time: 18:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登录</title>
</head>
<body>
<form action="LoginServlet.do" method="post">
    用户名：<input name="name" type="text">
    密码：<input name="password" type="password">
    <input type="submit" value="登录">
</form>
</body>
</html>
