<%--
  Created by IntelliJ IDEA.
  User: L1ANN
  Date: 2017/10/23
  Time: 10:31
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
    <title>宿舍详情</title>
</head>
<body>
<c:import url="navigation.jsp" charEncoding="UTF-8"/>
<br>
<br>
<div class="page-header">
    <h1>
        <small>宿舍详情</small>
    </h1>
</div>
<div class="container">
    <form action="De_AdApartment.do" method="post">
        <div class="form-group col-lg-4">
            <label>宿舍楼</label>
            <input type="text" class="form-control" readonly="readonly" value="${ap.bu_name}">
        </div>
        <div class="form-group col-lg-4">
            <label>宿舍号</label>
            <input type="text" class="form-control" readonly="readonly" value="${ap.ap_num}">
        </div>
        <div class="form-group col-lg-4">
            <label>当前宿舍人数</label>
            <input type="text" class="form-control" readonly="readonly" id="ap_total" value="${ap.ap_total}">
        </div>
        <!--隐藏控件，保存宿舍id-->
        <input type="hidden" name="ap_id" value="${ap.ap_id}">
        <!--这两个隐藏控件，用来控制请求方法和前端判断条件是否通过-->
        <input type="hidden" name="method" id="method" value="0">
        <input type="hidden" name="success" id="success" value="0">


        &nbsp;&nbsp;&nbsp;<label>宿舍人员名单</label>
        <c:forEach var="stu" items="${students}">
            <div class="checkbox-inline">
                <label>
                    <input name="stu_num" type="checkbox" value="${stu.stu_num}">${stu.stu_name}
                </label>
            </div>
        </c:forEach>

        <div class="col-lg-12">
            <div align="center">
                <input type="submit" class="btn btn-default" value="删除人员" onclick="deleteStudents()">
                <input type="submit" class="btn btn-default" value="增加人员" onclick="addStudent()">
                <input type="button" class="btn btn-default" value="返回" onclick="returnPage()">
            </div>
        </div>
    </form>
</div>
</body>
<script src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
    window.onload = function () {
        if ("${message}" != null && "${message}" != "") {
            alert("${message}");
        }
    }

    function returnPage() {
        window.location.href = "SelectApartment.do";
    }

    //获取多选框对象，如果其中有一个被选中，设置隐藏控件success值为1，代表前端验证通过。同时设置method为-1，代表执行删除动作
    function deleteStudents() {
        var key = document.getElementsByName("stu_num");
        $("#method").val("-1");
        for (var i = 0; i < key.length; i++) {
            if (key[i].checked) {
                $("#success").val("1");
                break;
            }
        }

    }

    //判断宿舍人数是否是4，如果不是4，设置success和method为1，代表执行增加动作且前端验证通过。
    function addStudent() {
        if (document.getElementById("ap_total").value != "4") {
            document.getElementById("method").value = "1";
            document.getElementById("success").value = "1";
        }
    }

</script>
</html>
