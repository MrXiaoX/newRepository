<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.cookie.js"></script>
<script type="text/javascript">
    $(function () {
        $("#bt").click(function () {
            var name = $("#name").val();
            var passwrod = $("#password").val();
            alert(name + " " + passwrod);
            $.ajax({
                "url": "${pageContext.request.contextPath}/api/dologin",
                "type": "POST",
                "data": {"name": name, "password": passwrod},
                "dataType": "json",
                "success": function (data) {
                    console.log(data);
                    if(data.success=="true"){
                        setCookie("token", data.data.token);
                        setCookie("expTime", data.data.expTime);
                    }else{
                        //location.href="/authDemo/tokenError.jsp";
                    }
                }
            });
        });
    });

    function setCookie(name, value) {
        document.cookie = name + '=' + escape(value);
    }
</script>
<body>
<div align="center">
    <h2>登录界面</h2>
    <form action="${pageContext.request.contextPath}/api/dologin"
          method="post">
        <div>
            用户账号: <input type="text" id="name" name="name"/>
        </div>
        <div>
            账号密码: <input type="password" id="password" name="password"/>
        </div>
        <div>
            <br>
            &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
            <input type="button" id="bt" value="登录"/>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="reset" value="重置"/> <br>
            <a href="${pageContext.request.contextPath}/api/validateToken.html">验证token</a>
            <a href="${pageContext.request.contextPath}/api/getUserList.html">获取用户列表</a>
            <a href="${pageContext.request.contextPath}/api/refrToken.html">置换token</a>
            <a href="${pageContext.request.contextPath}/api/register.html">注册</a>
            <a href="${pageContext.request.contextPath}/api/userlink.html">联系人</a>
            <a href=http://06256dbd.ngrok.io/itrip-auth/WeCat/login?signature=12&timestamp=333&nonce=23&echostr=122"><img src="${pageContext.request.contextPath}/js/weCat.jpg"></a>
        </div>
    </form>
</div>
</body>
</html>
