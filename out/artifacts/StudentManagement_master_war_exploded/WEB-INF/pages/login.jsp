<%--
  Created by IntelliJ IDEA.
  User: L1ANN
  Date: 2017/10/17
  Time: 18:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet" href="./homepage.css">
<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<style>
    .mycenter{
        margin-top: 100px;
        margin-left: auto;
        margin-right: auto;
        height: 350px;
        width:630px;
        padding: 5%;
        padding-left: 5%;
        padding-right: 5%;
    }
    .mycenter mysign{
        width: 460px;
    }
    .mycenter input,checkbox,button{
        margin-top:2%;
        margin-left: 10%;
        margin-right: 10%;
    }

</style>
<head>
    <title>用户登录</title>
</head>
<body>
<form action="LoginServlet" method="post">
    <div class="mycenter">
        <div class="mysign">
            <div class="col-lg-11 text-center text-info">
                <h2>华中科技大学新生管理系统</h2>
            </div>
            <div class="col-lg-10">
                <input type="text" class="form-control" name="name" placeholder="请输入账户名" required autofocus/>
            </div>
            <div class="col-lg-10"></div>
            <div class="col-lg-10">
                <input type="password" class="form-control" name="password" placeholder="请输入密码" required autofocus/>
            </div>
            <div class="col-lg-10"></div>
            <div class="col-lg-10"></div>
            <div class="col-lg-10">
                <button type="submit" class="btn btn-success col-lg-12">登录</button>
            </div>
        </div>
    </div>
</form>

</body>
</html>
