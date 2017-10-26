<%--
  Created by IntelliJ IDEA.
  User: L1ANN
  Date: 2017/10/22
  Time: 13:43
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
    .navbar-brand{
        height:57px;
    }
    .navbar-nav>li>a{
        padding-top:22px;
    }
</style>
<head>
    <title>宿舍管理</title>
</head>
<body>
<c:import url="navigation.jsp" charEncoding="UTF-8" />
<br>
<br>
<div class="page-header">
    <h1><small>宿舍列表</small></h1>
</div>
<!--如果进行条件查询，servlet会将sqlname和sqlvalue加到请求属性中-->
<!--将sqlnam保存到隐藏组件中，将sqlvalue保存在输入框中，同时根据sqlname值设置下拉框的默认值(这里使用了EL表达式的关系运算符和三目运算符)-->
<div align="center">
    查询宿舍：查询条件:

    <input type="hidden" id="sql_name" value="${sqlname}">
    <select id="sqlname">
        <option value="">全部</option>
        <option value="bu_name" ${sqlname=="bu_name"?'selected':''}>宿舍楼</option>
        <option value="ap_num" ${sqlname=="ap_num"?'selected':''}>宿舍号</option>
        <option value="ap_total" ${sqlname=='ap_total'?selected:''}>宿舍人数</option>
    </select>
    关键字：<input type="text" id="sqlvalue" value="${sqlvalue}">
    <input type="button" value="搜索" class="btn btn-default" onclick="selectByCondition()">
</div>

<!--servlet查询结果返回List<Student>保存到请求属性Student中，利用JSTL进行拼接-->
<div class="container">
<table class="table">
    <tr>
        <td><b>宿舍楼</b></td>
        <td><b>宿舍号</b></td>
        <td><b>宿舍人数</b></td>
        <td><b>管理宿舍人员信息</b></td>

    </tr>
    <c:forEach var="ap" items="${Apartment}">
        <tr>
            <td>${ap.bu_name}</td>
            <td>${ap.ap_num}</td>
            <td>${ap.ap_total}</td>
            <td><input type="button" class="btn btn-default" onclick="update('${ap.ap_id}')" value="管理宿舍人员信息"></td>
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
        window.location.href = "SelectApartment.do?p=" + p + "&sqlname=" + name + "&sqlvalue=" + value;

    }

    function nextPage() {
        var p = ${p+1};
        var name = document.getElementById("sql_name").value;
        var value = document.getElementById("sqlvalue").value;
        window.location.href = "SelectApartment.do?p=" + p + "&sqlname=" + name + "&sqlvalue=" + value;

    }

    function lastPage() {
        var p = ${count};
        var name = document.getElementById("sql_name").value;
        var value = document.getElementById("sqlvalue").value;
        window.location.href = "SelectApartment.do?p=" + p + "&sqlname=" + name + "&sqlvalue=" + value;

    }

    function jumpPage() {
        var p2 = document.getElementById("page").value;
        var name = document.getElementById("sql_name").value;
        var value = document.getElementById("sqlvalue").value;
        window.location.href = "SelectApartment.do?p=" + p2 + "&sqlname=" + name + "&sqlvalue=" + value;

    }


    function selectByCondition() {
        var name = document.getElementById("sqlname").value;
        var value = document.getElementById("sqlvalue").value;
        window.location.href = "SelectApartment.do?sqlname=" + name + "&sqlvalue=" + value;

    }

    function update(ap_id) {

        window.location.href = "DetailApartment.do?ap_id=" + ap_id;
    }


</script>
</html>
