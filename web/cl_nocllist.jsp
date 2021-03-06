<%--
  Created by IntelliJ IDEA.
  User: L1ANN
  Date: 2017/10/24
  Time: 14:03
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
    <title>添加班级人员</title>
</head>
<body>
<c:import url="navigation.jsp" charEncoding="UTF-8"/>
<br>
<br>
<div class="page-header">
    <h1>
        <small>无班级新生</small>
    </h1>
</div>
<input type="hidden" id="cl_id" value="${cl_id}">
<!--servlet查询结果返回List<Student>保存到请求属性Student中，利用JSTL进行拼接-->
<div class="container">
    <table class="table">
        <tr>
            <td><b>准考证号</b></td>
            <td><b>姓名</b></td>
            <td><b>系别</b></td>
            <td><b>专业</b></td>
            <td><b>添加</b></td>

        </tr>
        <c:forEach var="student" items="${students}">
            <tr>
                <td>${student.stu_num}</td>
                <td>${student.stu_name}</td>
                <td>${student.stu_department}</td>
                <td>${student.stu_major}</td>
                <td><input type="button" class="btn btn-default" onclick="add('${student.stu_num}')" value="添加"></td>
            </tr>
        </c:forEach>
    </table>
</div>
<!--完成翻页和跳转动作-->
<div align="center">
    第${p}/共${count}页
    <input type="button" class="btn btn-default" onclick="priorPage()" value="上一页">
    <input type="button" class="btn btn-default" onclick="nextPage()" value="下一页">
    <input type="button" class="btn btn-default" onclick="lastPage()" value="尾页">
    <input type="text" size="2" id="page">
    <input type="button" class="btn btn-default" value="跳转" onclick="jumpPage()"/>
</div>
</body>
<script type="text/javascript">

    window.onload = function () {
        if ("${message}" != "" && "${message}" != null) {
            alert("${message}");
        }
    }

    function priorPage() {
        var p = ${p-1};
        var cl_id = document.getElementById("cl_id").value;
        window.location.href = "SelectNoClStudent.do?p=" + p + "&cl_id=" + cl_id;

    }

    function nextPage() {
        var p = ${p+1};
        var cl_id = document.getElementById("cl_id").value;
        window.location.href = "SelectNoClStudent.do?p=" + p + "&cl_id=" + cl_id;

    }

    function lastPage() {
        var p = ${count};
        var cl_id = document.getElementById("cl_id").value;
        window.location.href = "SelectNoClStudent.do?p=" + p + "&cl_id=" + cl_id;

    }

    function jumpPage() {
        var p = document.getElementById("page").value;
        var cl_id = document.getElementById("cl_id").value;
        window.location.href = "SelectNoClStudent.do?p=" + p + "&cl_id=" + cl_id;

    }


    function add(stu_num) {
        var cl_id = document.getElementById("cl_id").value;
        window.location.href = "AddClStudent.do?stu_num=" + stu_num + "&cl_id=" + cl_id;
    }


</script>
</html>
