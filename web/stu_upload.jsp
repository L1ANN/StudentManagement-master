<%--
  Created by IntelliJ IDEA.
  User: L1ANN
  Date: 2017/10/26
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>导入新生列表</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

    <style>
        h1 {
            text-align: center
        }

        .container {
            width: 1400px;
        }

        .navbar-brand {
            height: 57px;
        }

        .navbar-nav > li > a {
            padding-top: 22px;
        }
    </style>
</head>
<body>
<c:import url="navigation.jsp" charEncoding="UTF-8"/>
<br>
<br>
<div class="page-header">
    <h1>
        <small>新生导入</small>
    </h1>
</div>
<form action="UploadExcel.do" method="post" enctype="multipart/form-data">
    <div class="container">
        <div class="form-group">
            <label>新生基本信息录入</label>
            <input type="file" name="stu_excel">
        </div>
        <input type="submit" class="btn btn-default" value="提交">
    </div>
</form>
</body>
<script type="text/javascript">
    window.onload = function () {
        if ("${message}" != null && "${message}" != "") {
            alert("${message}");
        }
    }
</script>
</html>
