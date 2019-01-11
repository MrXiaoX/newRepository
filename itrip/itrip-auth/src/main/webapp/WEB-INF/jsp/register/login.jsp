<%--
  Created by IntelliJ IDEA.
  User: Mrxiao
  Date: 2018/12/7
  Time: 10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
<body>
<div id="div_login">
    <form>
        <table align="center">
            <tr>
                <td>昵称:</td>
                <td><input type="text" name="userName"></td>
            </tr>
            <tr>
                <td>登录密码:</td>
                <td><input type="password" name="userPassword"></td>
            </tr>
            <tr>
                <td>确认密码:</td>
                <td><input type="password" name="userPassword2"></td>
            </tr>
            <tr>
                <td><input type="button" id="tijiao" value="提交">
                    <input type="reset" value="重置"></td>
                <td><input type="button" id="wechat" value="微信登录"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
