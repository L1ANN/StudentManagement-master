<%--
  Created by IntelliJ IDEA.
  User: L1ANN
  Date: 2017/10/24
  Time: 22:26
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
        width: 500px;
    }
    .mycenter mysign {
        width: 460px;
    }

    .navbar-brand {
        height: 57px;
    }

    .navbar-nav > li > a {
        padding-top: 22px;
    }
</style>
<head>
    <title>用户管理</title>
</head>
<body>
<c:import url="navigation.jsp" charEncoding="UTF-8"/>
<br>
<br>
<div class="page-header">
    <h1>
        <small>修改密码</small>
    </h1>
</div>
<div class="container">
    <form>
        <div class="mysin">
            <div class="form-group ">
                <label>旧密码</label>
                <input type="password" class="form-control" id="oldpass" placeholder="Password">
            </div>
            <div class="form-group">
                <label>新密码</label>
                <input type="password" class="form-control" id="newpass" placeholder="NewPassword">
            </div>
            <div class="form-group">
                <label>确认新密码</label>
                <input type="password" class="form-control" id="re_newpass" placeholder="NewPassword">
            </div>
            <input type="button" class="btn btn-default col-lg-12" value="提交" onclick="changePass()">
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

    function changePass() {
        var oldpass = document.getElementById("oldpass").value;
        var newpass = document.getElementById("newpass").value;
        var re_newpass = document.getElementById("re_newpass").value;
        if (oldpass != ${admin.ad_pass}) {
            alert("旧密码不正确");
            $('#oldpass').val('');
            $('#newpass').val('');
            $('#re_newpass').val('');
        }
        else {
            if (newpass != re_newpass) {
                alert("两次输入的密码不同！");
                $('#oldpass').val('');
                $('#newpass').val('');
                $('#re_newpass').val('');
            }
            else {
                if (newpass == oldpass) {
                    alert("新密码不能与旧密码相同！");
                    $('#oldpass').val('');
                    $('#newpass').val('');
                    $('#re_newpass').val('');
                }
                else {
                    var re = /^[0-9]+.?[0-9]*$/;
                    if (!re.test(newpass)) {
                        alert("新密码必须是数字！");
                        $('#oldpass').val('');
                        $('#newpass').val('');
                        $('#re_newpass').val('');
                    } else {
                        window.location.href = "ChangePass.do?newpass=" + newpass;
                    }
                }
            }
        }
    }
</script>
</html>
