<%--
  Created by IntelliJ IDEA.
  User: L1ANN
  Date: 2017/10/24
  Time: 17:38
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
    <title>Title</title>
</head>
<body>
<c:import url="navigation.jsp" charEncoding="UTF-8"/>
<br>
<br>
<div class="page-header">
    <h1>
        <small>新生成绩列表</small>
    </h1>
</div>
<!--如果进行条件查询，servlet会将sqlname和sqlvalue加到请求属性中-->
<!--将sqlnam保存到隐藏组件中，将sqlvalue保存在输入框中，同时根据sqlname值设置下拉框的默认值(这里使用了EL表达式的关系运算符和三目运算符)-->
<input type="hidden" id="de_id_hidden" value="">
<input type="hidden" id="ma_id_hidden" value="${ma_id}">
<input type="hidden" id="cl_id_hidden" value="${cl_id}">

<form>
    <div class="container">
        <div class="col-lg-3"><label>查询条件：</label></div>
        <div id="de_select" class="col-lg-3"></div>
        <div id="ma_select" class="col-lg-3"></div>
        <div id="cl_select" class="col-lg-3"></div>
    </div>
    <br>
    <div class="container">
        <div align="center" class="col-lg-12"><input type="button" class="btn btn-default" value="搜索"
                                                     onclick="selectByCondition()"></div>
    </div>
</form>

<!--servlet查询结果返回List<Student>保存到请求属性Student中，利用JSTL进行拼接-->
<div class="container">
    <table class="table">
        <tr>
            <td><b>准考证号</b></td>
            <td><b>姓名</b></td>
            <td><b>专业</b></td>
            <td><b>班级</b></td>
            <td><b>英语成绩</b></td>
            <td><b>政治成绩</b></td>
            <td><b>高数成绩</b></td>
            <td><b>专业课成绩</b></td>
            <td><b>总成绩</b></td>
        </tr>
        <c:forEach var="stu" items="${Student}">
            <tr>
                <td>${stu.stu_num}</td>
                <td>${stu.stu_name}</td>
                <td>${stu.stu_major}</td>
                <td>${stu.stu_class}</td>
                <td>${stu.re_eng}</td>
                <td>${stu.re_pol}</td>
                <td>${stu.re_math}</td>
                <td>${stu.re_pro}</td>
                <td>${stu.re_total}</td>
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

<script src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
    //页面加载时，从后台获取系别写入select中
    window.onload = function () {
        $.ajax({
            url: "SelectDepartmentAJAX.do?",
            async: false,
            dataType: "json",
            success: function (data) {
                var de = data.departments;
                var html = "<select id='de_id' class='form-control' name='de_id' onchange='getMajor()' >";
                html = html + "<option>请选择系别</option>";
                for (var i = 0; i < de.length; i++) {
                    var id = de[i].de_id;
                    var option = "<option value=" + id + ">" + de[i].de_name + "</opiton>";
                    html = html + option;
                }
                html = html + "</select>";
                document.getElementById('de_select').innerHTML = html;
            }
        })
    }

    //选择系别后，从后台获取专业写入select中，同时将de_id保存到隐藏控件中，用于前端判断
    function getMajor() {
        var de_id = document.getElementById('de_id').value;
        document.getElementById('de_id_hidden').value = de_id;
        $.ajax({
            url: "SelectMajor.do?",
            data: {"departmentId": de_id},
            async: false,
            dataType: "json",
            success: function (data) {
                var major = data.majors;
                var html = "<select id='ma_id' class='form-control' name='ma_id' onchange='getClass()'>";
                html = html + "<option>请选择专业</option>";
                for (var i = 0; i < major.length; i++) {
                    var id = major[i].ma_id;
                    var option = "<option value=" + id + ">" + major[i].ma_name + "</opiton>";
                    html = html + option;
                }
                html = html + "</select>";
                document.getElementById('ma_select').innerHTML = html;
            }
        })
    }

    //选择专业后，从后台获取班级写入select中，同时将ma_id保存到隐藏控件中，用于前端判断以及保存搜索状态。
    //这里注意还要将cl_id_hidden清空，否则如果先进行班级成绩查询，cl_id_hidden保存班级id，再进行年级查询，此时cl_id_hidden应该为空，但是之前赋的值没有消除，导致查询的还是之前的班级成绩。
    function getClass() {
        document.getElementById('cl_id_hidden').value='';
        var ma_id = document.getElementById('ma_id').value;
        document.getElementById('ma_id_hidden').value = ma_id;
        $.ajax({
            url: "SelectClassAJAX.do?",
            data: {"majorId": ma_id},
            async: false,
            dataType: "json",
            success: function (data) {
                var classes = data.classes;
                var html = "<select id='cl_id' class='form-control' name='cl_id' onchange='setHidden()'>";
                html = html + "<option>请选择班级</option>";
                for (var i = 0; i < classes.length; i++) {
                    var id = classes[i].cl_id;
                    var option = "<option value=" + id + ">" + classes[i].cl_name + "</opiton>";
                    html = html + option;
                }
                html = html + "</select>";
                document.getElementById('cl_select').innerHTML = html;
            }
        })
    }

    //选择班级后，将cl_id保存到隐藏控件中，用于保存搜索状态
    function setHidden() {
        var cl_id = document.getElementById('cl_id').value;
        document.getElementById('cl_id_hidden').value = cl_id;
    }

    //搜索，首先判断系别和专业是否选择，如果没有提示用户选择
    function selectByCondition() {
        var de_id = document.getElementById("de_id_hidden").value;
        var ma_id = document.getElementById("ma_id_hidden").value;
        var cl_id = document.getElementById("cl_id_hidden").value;
        if (de_id == "") {
            alert("请选择要查询的系别！");
        } else {
            if (ma_id == "") {
                alert("请选择要查询的专业!");
            } else {
                window.location.href = "SelectRecord.do?ma_id=" + ma_id + "&cl_id=" + cl_id;
            }
        }
    }

    //分页
    function priorPage() {
        var p = ${p-1};
        var ma_id = document.getElementById("ma_id_hidden").value;
        var cl_id = document.getElementById("cl_id_hidden").value;
        window.location.href = "SelectRecord.do?p=" + p + "&ma_id=" + ma_id + "&cl_id=" + cl_id;

    }

    function nextPage() {
        var p = ${p+1};
        var ma_id = document.getElementById("ma_id_hidden").value;
        var cl_id = document.getElementById("cl_id_hidden").value;
        window.location.href = "SelectRecord.do?p=" + p + "&ma_id=" + ma_id + "&cl_id=" + cl_id;

    }

    function lastPage() {
        var p = ${count};
        var ma_id = document.getElementById("ma_id_hidden").value;
        var cl_id = document.getElementById("cl_id_hidden").value;
        window.location.href = "SelectRecord.do?p=" + p + "&ma_id=" + ma_id + "&cl_id=" + cl_id;

    }

    function jumpPage() {
        var p2 = document.getElementById("page").value;
        var ma_id = document.getElementById("ma_id_hidden").value;
        var cl_id = document.getElementById("cl_id_hidden").value;
        window.location.href = "SelectRecord.do?p=" + p2 + "&ma_id=" + ma_id + "&cl_id=" + cl_id;

    }


</script>
</html>
