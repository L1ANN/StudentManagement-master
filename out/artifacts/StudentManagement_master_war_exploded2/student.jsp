<%--
  Created by IntelliJ IDEA.
  User: L1ANN
  Date: 2017/10/18
  Time: 14:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>新生</title>
</head>
<style type="text/css">
    h1 {
        text-align: center
    }
</style>
<script type="text/javascript">
    function jumpPage() {
        var p2 = document.getElementById("page").value;
        window.location.href = "SelectStudent.do?p=" + p2;
    }

    function returnPage() {
        window.history.go(-1)；
    }
</script>
<body>
<h1>新生列表</h1>
<a onclick="returnPage()">返回</a>
<table align="center" cellpadding="10" cellspacing="10">
    <tr bgcolor="green">
        <td>准考证号</td>
        <td>姓名</td>
        <td>年龄</td>
        <td>性别</td>
        <td>民族</td>
        <td>籍贯</td>
        <td>入学时间</td>
        <td>专业</td>
        <td>系别</td>
        <td>班级</td>
        <td>出生时间</td>
        <td>联系电话</td>
        <td>家庭住址</td>
        <td>修改</td>
        <td>删除</td>
    </tr>
    <c:forEach var="stu" items="${Student}">
        <tr>
            <td>${stu.stu_num}</td>
            <td>${stu.stu_name}</td>
            <td>${stu.stu_age}</td>
            <td>${stu.stu_gender}</td>
            <td>${stu.stu_ethnic}</td>
            <td>${stu.stu_native}</td>
            <td>${stu.stu_time}</td>
            <td>${stu.stu_major}</td>
            <td>${stu.stu_department}</td>
            <td>${stu.stu_class}</td>
            <td>${stu.stu_birth}</td>
            <td>${stu.stu_phone}</td>
            <td>${stu.stu_address}</td>
            <td>修改</td>
            <td>删除</td>
        </tr>
    </c:forEach>
</table>
<div align="center">
    第${p}/共${count}页
    <a href="/SelectStudent.do?p=${p-1}">上一页</a>
    <a href="/SelectStudent.do?p=${p+1}">下一页</a>
    <a href="/SelectStudent.do?p=${count}">尾页</a>
    <input type="text" size="2" id="page">
    <input type="button" value="跳转" onclick="jumpPage()"/>
</div>
</body>
</html>
