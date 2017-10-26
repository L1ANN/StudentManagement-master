<%--
  Created by IntelliJ IDEA.
  User: L1ANN
  Date: 2017/10/19
  Time: 12:41
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
        width: 1400px;
    }
    .navbar-brand {
        height: 57px;
    }

    .navbar-nav > li > a {
        padding-top: 22px;
    }
</style>
<head>
    <title>学费</title>
</head>
<body>
<c:import url="navigation.jsp" charEncoding="UTF-8"/>
<br>
<br>
<div class="page-header">
    <h1>
        <small>新生缴费列表</small>
    </h1>
</div>
<!--如果进行条件查询，servlet会将sqlname和sqlvalue加到请求属性中-->
<!--将name保存到隐藏控件中保存搜索的状态，同时根据name值设置下拉框的默认值(这里使用了EL表达式的关系运算符和三目运算符)-->
<div align="center">
    查询缴费信息：查询条件:
    <input type="hidden" id="sql_name" value="${name}">
    <select class="from-control" id="sqlname">
        <option value="">全部</option>
        <option value="1" ${name=="1"?'selected':''}>已缴费</option>
        <option value="0" ${name=="0"?'selected':''}>未缴费</option>
    </select>

    <input type="button" class="btn btn-default" value="搜索" onclick="selectByCondition()">
</div>

<!--servlet查询结果返回List<Student>保存到请求属性Student中，利用JSTL进行拼接-->
<div class="container">
    <table class="table">
        <tr>
            <td><b>准考证号</b></td>
            <td><b>姓名</b></td>
            <td><b>专业</b></td>
            <td><b>系别</b></td>
            <td><b>班级</b></td>
            <td><b>应缴</b></td>
            <td><b>已缴</b></td>
        </tr>
        <c:forEach var="stu" items="${Student}">
            <tr>
                <td>${stu.stu_num}</td>
                <td>${stu.stu_name}</td>
                <td>${stu.stu_major}</td>
                <td>${stu.stu_department}</td>
                <td>${stu.stu_class}</td>
                <td>22368.0</td>
                <td>${stu.stu_tuition}</td>
                <input type="hidden" id="stu_num" value="${stu.stu_num}">
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


    function priorPage() {
        var p = ${p-1};
        var name = document.getElementById("sqlname").value;
        //var value = document.getElementById("sqlvalue").value;
        window.location.href = "SelectTuition.do?p=" + p + "&sqlname=" + name;

    }

    function nextPage() {
        var p = ${p+1};
        var name = document.getElementById("sql_name").value;
        //var value = document.getElementById("sqlvalue").value;
        window.location.href = "SelectTuition.do?p=" + p + "&sqlname=" + name;

    }

    function lastPage() {
        var p = ${count};
        var name = document.getElementById("sql_name").value;
        //var value = document.getElementById("sqlvalue").value;
        window.location.href = "SelectTuition.do?p=" + p + "&sqlname=" + name;

    }

    function jumpPage() {
        var p2 = document.getElementById("page").value;
        var name = document.getElementById("sql_name").value;
        //var value = document.getElementById("sqlvalue").value;
        window.location.href = "SelectTuition.do?p=" + p2 + "&sqlname=" + name;

    }

    function selectByCondition() {
        var name = document.getElementById("sqlname").value;

        window.location.href = "SelectTuition.do?sqlname=" + name;

    }


</script>
</html>
