<%--
  Created by IntelliJ IDEA.
  User: L1ANN
  Date: 2017/10/18
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<style>
    h1 {
        text-align: center
    }

    .navbar-brand {
        height: 57px;
    }

    .navbar-nav > li > a {
        padding-top: 22px;
    }
</style>
<head>
    <title>学生详细信息</title>
</head>

<script type="text/javascript">
    window.onload = function () {
        if ("${message}" != null && "${message}" != "") {
            alert("${message}");
        }
    }

    function returnPage() {
        window.location.href = "SelectStudent.do?jsp=list";
    }
</script>
<body>
<c:import url="navigation.jsp" charEncoding="UTF-8"/>
<br>
<br>
<div class="page-header">
    <h1>
        <small>新生基本信息</small>
    </h1>
</div>

<form action="UpdateStudent.do" method="post">
    <div class="form-group col-lg-4">
        <label>准考证号</label>
        <input type="text" class="form-control" readonly="readonly" name="stu_num" value="${stu.stu_num}">
    </div>
    <div class="form-group col-lg-4">
        <label>姓名</label>
        <input type="text" class="form-control" name="stu_name" value="${stu.stu_name}">
    </div>
    <div class="form-group col-lg-4">
        <label>年龄</label>
        <input type="text" class="form-control" name="stu_age" value="${stu.stu_age}">
    </div>
    <div class="form-group col-lg-4">
        <label>性别</label>
        <select class="form-control" name="stu_gender">
            <option value="男" ${stu.stu_gender=='男'?'selected':''}>男</option>
            <option value="女" ${stu.stu_gender=='女'?'selected':''}>女</option>
        </select>
    </div>
    <div class="form-group col-lg-4">
        <label>民族</label>
        <input type="text" class="form-control" name="stu_ethnic" value="${stu.stu_ethnic}">
    </div>
    <div class="form-group col-lg-4">
        <label>籍贯</label>
        <input type="text" class="form-control" name="stu_native" value="${stu.stu_native}">
    </div>
    <div class="form-group col-lg-4">
        <label>入学时间</label>
        <input type="text" class="form-control" readonly="readonly" name="stu_time" value="${stu.stu_time}">
    </div>
    <div class="form-group col-lg-4">
        <label>系别</label>
        <input type="text" class="form-control" readonly="readonly" name="stu_department" value="${stu.stu_department}">
    </div>
    <div class="form-group col-lg-4">
        <label>专业</label>
        <input type="text" class="form-control" readonly="readonly" name="stu_major" value="${stu.stu_major}">
    </div>
    <div class="form-group col-lg-4">
        <label>班级</label>
        <input type=text" class="form-control" readonly="readonly" name="stu_class" value="${stu.stu_class}">
    </div>
    <div class="form-group col-lg-4">
        <label>出生时间</label>
        <input type="date" class="form-control" name="stu_birth" value="${stu.stu_birth}">
    </div>
    <div class="form-group col-lg-4">
        <label>联系电话</label>
        <input type="text" class="form-control" name="stu_phone" value="${stu.stu_phone}">
    </div>
    <div class="form-group col-lg-4">
        <label>家庭住址</label>
        <input type="text" class="form-control" name="stu_address" value="${stu.stu_address}">
    </div>
    <div class="col-lg-12">
        <div align="center">
            <input type="submit" class="btn btn-default" value="提交">
            <input type="button" class="btn btn-default" value="返回" onclick="returnPage()">
        </div>
    </div>
</form>
</body>
</html>
