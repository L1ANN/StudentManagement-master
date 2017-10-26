<%--
  Created by IntelliJ IDEA.
  User: L1ANN
  Date: 2017/10/19
  Time: 14:44
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
    <title>新生专业调整</title>
</head>

<body>
<c:import url="navigation.jsp" charEncoding="UTF-8"/>
<br>
<br>
<div class="page-header">
    <h1>
        <small>新生专业调整</small>
    </h1>
</div>
<form action="UpdateMajor.do" method="post">
    <div class="form-group col-lg-6">
        <label>准考证号</label>
        <input type="text" class="form-control" readonly="readonly" readonly="readonly" name="stu_num" value="${stu.stu_num}">
    </div>
    <div class="form-group col-lg-6">
        <label>姓名</label>
        <input type="text" class="form-control" readonly="readonly" name="stu_name" value="${stu.stu_name}">
    </div>
    <div class="form-group col-lg-6">
        <label>系别</label>
        <input type="text" class="form-control" readonly="readonly" name="stu_department" value="${stu.stu_department}">
    </div>
    <div class="form-group col-lg-6">
        <label>专业</label>
        <input type="text" class="form-control" readonly="readonly" name="stu_major" value="${stu.stu_major}">
    </div>
    <div class="form-group col-lg-6">
        <label>新系别</label>
        <select name="de_id" class="form-control" id="departmentId" onchange="getValue()">
            <option value="">请选择系别</option>
            <c:forEach var="it" items="${departmentlist}">
                <option value="${it.de_id}"}>${it.de_name}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group col-lg-6">
        <label>新专业</label>
        <div id="major_select">
        </div>
    </div>

    <div class="col-lg-12">
        <div align="center">
            <input type="submit" class="btn btn-default" value="提交">
            <input type="button" class="btn btn-default" value="返回" onclick="returnPage()">
        </div>
    </div>
</form>
</body>
<script src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
    window.onload = function () {
        if ("${message}" != null && "${message}" != "") {
            alert("${message}");
        }
    }

    function returnPage() {
        window.location.href = "SelectStudent.do?jsp=majorlist"
    }

    //当选中系别后，通过AJAX异步更新取到对应专业的数据并显示在select标签下
    function getValue() {
        var departmentId = document.getElementById("departmentId").value;
        $.ajax({
            url: "SelectMajor.do?",
            data: {"departmentId": departmentId},
            async: false,
            dataType: "json",
            success: function (data) {
                var major = data.majors;
                var html = "<select class='form-control' name='ma_id' >";
                html = html+"<option value=''>请选择专业</option>";
                for (var i = 0; i < major.length; i++) {
                    var option = '<option value=' + major[i].ma_id + '>' + major[i].ma_name + '</opiton>';
                    html = html + option;
                }
                html = html + "</select>";
                document.getElementById('major_select').innerHTML = html;
            }
        })
    }
</script>
</html>
