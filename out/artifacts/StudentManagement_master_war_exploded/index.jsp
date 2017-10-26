<%--
  Created by IntelliJ IDEA.
  User: L1ANN
  Date: 2017/10/17
  Time: 12:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>首页</title>
</head>
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<style>
    .navbar-brand{
        height:57px;
    }
    .navbar-nav>li>a{
        padding-top:22px;
    }
</style>
<body>
    <nav class="navbar  navbar-fixed-top navbar-inverse">

            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">
                    <img alt="HUST" src="img/logo.png" height="36" width="180">
                </a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">新生报到
                            <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li class="dropdown-header">管理</li>
                            <li><a href="SelectStudent.do?jsp=list">新生录取信息</a></li>
                            <li><a href="SelectStudent.do?jsp=majorlist">新生专业调整</a></li>
                            <li><a href="SelectStudent.do?jsp=classlist">新生班级分配</a></li>
                            <li><a href="SelectTuition.do">财务缴费</a></li>
                            <li class="dropdown-header">录入</li>
                            <li><a href="JumpUpload.do">录入新生基本信息</a></li>
                        </ul>
                    </li>
                    <li><a href="SelectApartment.do">宿舍管理</a></li>
                    <li><a href="SelectClass.do">班级管理</a></li>
                    <li><a href="JumpRecord.do">成绩管理</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">用户管理
                            <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li class="dropdown-header">账号</li>
                            <li><a href="JumpAdmin.do">更改密码</a></li>

                        </ul>
                    </li>
                    <li><a href="LogoutServlet.do">退出系统</a></li>

                </ul>
            </div>
    </nav>
</body>
</html>
