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
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<style>
    h1 {
        text-align: center
    }
    .container {
        width: 1700px;
    }
    .navbar-brand {
        height: 57px;
    }

    .navbar-nav > li > a {
        padding-top: 22px;
    }
</style>
<head>
    <title>新生录取信息</title>
</head>
<body>
<c:import url="navigation.jsp" charEncoding="UTF-8"/>
<br>
<br>
<div class="page-header">
    <h1>
        <small>新生列表</small>
    </h1>
</div>
<!--如果进行条件查询，servlet会将sqlname和sqlvalue加到请求属性中-->
<!--将sqlnam保存到隐藏组件中，将sqlvalue保存在输入框中，同时根据sqlname值设置下拉框的默认值(这里使用了EL表达式的关系运算符和三目运算符)-->
<div align="center">
    查询学生信息：查询条件:

    <input type="hidden" id="sql_name" value="${sqlname}">
    <select id="sqlname">
        <option value="">全部</option>
        <option value="stu_num" ${sqlname=="stu_num"?'selected':''}>准考证号</option>
        <option value="stu_name" ${sqlname=="stu_name"?'selected':''}>姓名</option>
        <option value="stu_department" ${sqlname=="stu_department"?'selected':''}>系别</option>
        <option value="stu_major" ${sqlname=="stu_major"?'selected':''}>专业</option>
    </select>
    关键字：<input type="text" id="sqlvalue" value="${sqlvalue}">
    <input type="button" value="搜索" class="btn btn-default" onclick="selectByCondition()">
</div>

<!--servlet查询结果返回List<Student>保存到请求属性Student中，利用JSTL进行拼接-->
<div class="container">
    <table class="table">
        <tr>
            <td><b>准考证号</b></td>
            <td><b>姓名</b></td>
            <td><b>年龄</b></td>
            <td><b>性别</b></td>
            <td><b>民则</b></td>
            <td><b>籍贯</b></td>
            <td><b>入学时间</b></td>
            <td><b>专业</b></td>
            <td><b>系别</b></td>
            <td><b>班级</b></td>
            <td><b>出生时间</b></td>
            <td><b>联系电话</b></td>
            <td><b>家庭住址</b></td>
            <td><b>修改</b></td>
            <td><b>删除</b></td>
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
                <td><input type="button" class="btn btn-default" onclick="update('${stu.stu_num}')" value="修改"></td>
                <td><input type="button" class="btn btn-default" onclick="deleted('${stu.stu_num}')" value="删除"></td>
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
        var name = document.getElementById("sqlname").value;
        var value = document.getElementById("sqlvalue").value;
        window.location.href = "SelectStudent.do?jsp=list&p=" + p + "&sqlname=" + name + "&sqlvalue=" + value;

    }

    function nextPage() {
        var p = ${p+1};
        var name = document.getElementById("sql_name").value;
        var value = document.getElementById("sqlvalue").value;
        window.location.href = "SelectStudent.do?jsp=list&p=" + p + "&sqlname=" + name + "&sqlvalue=" + value;

    }

    function lastPage() {
        var p = ${count};
        var name = document.getElementById("sql_name").value;
        var value = document.getElementById("sqlvalue").value;
        window.location.href = "SelectStudent.do?jsp=list&p=" + p + "&sqlname=" + name + "&sqlvalue=" + value;

    }

    function jumpPage() {
        var p2 = document.getElementById("page").value;
        var name = document.getElementById("sql_name").value;
        var value = document.getElementById("sqlvalue").value;
        window.location.href = "SelectStudent.do?jsp=list&p=" + p2 + "&sqlname=" + name + "&sqlvalue=" + value;

    }

    function returnPage() {
        window.location.href = "index.jsp";
    }

    function selectByCondition() {
        var name = document.getElementById("sqlname").value;
        var value = document.getElementById("sqlvalue").value;
        window.location.href = "SelectStudent.do?jsp=list&sqlname=" + name + "&sqlvalue=" + value;

    }

    function update(num) {

        window.location.href = "DetailStudent.do?jsp=detail&stu_num=" + num;
    }

    function deleted(num) {

        if (confirm("确定要删除该条记录吗？")) {
            window.location.href = "DeleteStudent.do?stu_num=" + num;
        }
    }

</script>
</html>
