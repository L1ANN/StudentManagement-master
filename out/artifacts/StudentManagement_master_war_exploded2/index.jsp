<%--
  Created by IntelliJ IDEA.
  User: L1ANN
  Date: 2017/10/17
  Time: 12:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
  <head>
    <title>首页</title>
  </head>
  <body>
  <h1>新生管理系统</h1>
  <hr/>
  <c:if test="${admin==null}">
    <a href="Login.do">登录</a>
    <a href="Register.do">注册</a>
  </c:if>
  <c:if test="${admin!=null}">
    欢迎您：${admin.ad_name}
    <input type="button" value="新生列表" onclick="jumpStudent()">
    <input type="button" value="退出登录" onclick="Logout()">
  </c:if>
  </body>
<script type="text/javascript">
  function Logout(){
      window.location.href="LogoutServlet.do";
  }
  function jumStudent(){
      window.location.href="SelectStudent.do";
  }
</script>
</html>
