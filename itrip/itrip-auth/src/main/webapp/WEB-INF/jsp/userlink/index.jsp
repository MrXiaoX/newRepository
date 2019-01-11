<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.cookie.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/retoken.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#search").click(function () {
                var str = {
                    "linkUserName": $("#name").val()
                };
                var arrs = JSON.stringify(str);
                $.ajax({
                    "url": "http://localhost:80/itrip-biz/api/userinfo/queryuserlinkuser",
                    "type": "POST",
                    "contentType": 'application/json',
                    "dataType": "json",
                    "data": arrs,
                    "success": function (data) {
                        console.log(data);
                        if (data.success == "true") {
                            var list = data.data;
                            $("#tabDiv").html("");
                            var uid
                            var tab = $("<table border='1'></table>").append("<tr><td>id</td><td>userId</td><td>用户名</td><td>电话</td><td>操作</td></tr>").appendTo($("#tabDiv"));
                            $(list).each(function () {
                                tab.append("<tr><td>" + this.id + "</td><td>" + this.userId + "</td><td>" + this.linkUserName + "</td><td>" + this.linkPhone + "</td><td><a href='#' class='del'>删除</a><td><a href='#' class='updata'>修改</a></td></tr>");
                            });
                            $(".del").click(function () {
                                var linkid =[$(this).parent().siblings(0).html()] ;
                                alert(linkid);
                                var strid=JSON.stringify(linkid);
                                $.ajax({
                                    "url": "http://localhost:80/itrip-biz/api/userinfo/delLinkUser",
                                    "type": "GET",
                                    "contentType": 'application/json',
                                    "dataType": "json",
                                    "data": strid,
                                    "success": function (data) {
                                        console.log("删除联系人成功");
                                        var list = data.data;
                                        $("#tabDiv").html("");
                                        var tab = $("<table border='1'></table>").append("<tr><td>id</td><td>userId</td><td>用户名</td><td>电话</td><td>操作</td></tr>").appendTo($("#tabDiv"));
                                        $(list).each(function () {
                                            tab.append("<tr><td>" + this.id + "</td><td>" + this.userId + "</td><td>" + this.linkUserName + "</td><td>" + this.linkPhone + "</td><td><a href='#' class='del'>删除</a></td><td><a href='#' class='updata'>修改</a></td></tr>");
                                        });
                                    }, beforeSend: function (request) {
                                        request.setRequestHeader("token", $.cookie("token"));
                                    }
                                })
                            });

                            $(".updata").click(function () {
                                $("#addUser").show();
                                $(".modify").show();
                                $("#tijiao").hide();
                                var linkuserName =$(this).parent().siblings(0).html() ;
                                alert(linkuserName);
                                var strid=JSON.stringify(linkuserName);
                                $.ajax({
                                    "url": "http://localhost:80/itrip-biz/api/userinfo/selectlinkuser",
                                    "type": "POST",
                                    "contentType": 'application/json',
                                    "dataType": "json",
                                    "data": strid,
                                    "success": function (data) {
                                        var list = data.data;
                                        $("#linkUserName").val(list.linkUserName);
                                        $("#linkIdCard").val(list.linkIdCard);
                                        $("#linkPhone").val(list.linkPhone);
                                        $("#id").val(list.id);

                                    }, beforeSend: function (request) {
                                        request.setRequestHeader("token", $.cookie("token"));
                                    }
                                })
                            });
                        } else {
                            alert("token失效，请重新登录");
                        }
                    },
                    beforeSend: function (request) {
                        request.setRequestHeader("token", $.cookie("token"));
                    }
                });
            });
            $("#add").click(function () {
                $("#addUser").toggle();
            });
            $("#tijiao").click(function () {
                $(".modify").hide();
                var str = {
                    "linkUserName": $("#addUser input[name='linkUserName']").val(),
                    "linkIdCard": $("#addUser input[name='linkIdCard']").val(),
                    "linkPhone": $("#addUser input[name='linkPhone']").val(),
                    "linkIdCardType": "0"
                };
                var arrs = JSON.stringify(str);
                $.ajax({
                    "url": "http://localhost:80/itrip-biz/api/userinfo/adduserlinkuser",
                    "type": "POST",
                    "data": arrs,
                    "contentType": 'application/json',
                    "dataType": "json",
                    "success": function (data) {
                        console.log(data);
                        if (data.success == "true") {
                            $("#addUser").hide();
                            console.log("添加联系人成功");
                        } else {
                            console.log("添加联系人失败");
                        }
                    },
                    beforeSend: function (request) {
                        request.setRequestHeader("token", $.cookie("token"));
                    }
                })
            });

            $(".modify").click(function () {
                var str = {
                    "linkUserName": $("#addUser input[name='linkUserName']").val(),
                    "id": $("#addUser input[name='id']").val(),
                    "linkIdCard": $("#addUser input[name='linkIdCard']").val(),
                    "linkPhone": $("#addUser input[name='linkPhone']").val(),
                    "linkIdCardType": "0"
                };
                var arrs = JSON.stringify(str);
                $.ajax({
                    "url": "http://localhost:80/itrip-biz/api/userinfo/updataLinkUser",
                    "type": "POST",
                    "data": arrs,
                    "contentType": 'application/json',
                    "dataType": "json",
                    "success": function (data) {
                        console.log(data);
                        if (data.success == "true") {
                            $("#addUser").hide();
                            console.log("修改联系人成功");
                        } else {
                            console.log("修改联系人失败");
                        }
                    },
                    beforeSend: function (request) {
                        request.setRequestHeader("token", $.cookie("token"));
                    }
                })
            });
        });
    </script>
</head>
<body>
<div>

    旅客姓名：<input type="text" id="name"/>
    <input type="button" id="search" value="获取常用联系人列表"/>
    <input type="button" id="add" value="新增"/>
</div>
<div id="tabDiv">
</div>
<div id="addUser" style="display: none">
    <form>
        <table align="center">
            <tr>
                <td>联系人名称:</td>
                <td><input type="text" id="linkUserName" name="linkUserName"></td>
            </tr>
            <tr>
                <td>联系人证件号码:</td>
                <td><input type="text" id="linkIdCard" name="linkIdCard"></td>
            </tr>
            <tr>
                <td>联系人电话:</td>
                <td><input type="text" id="linkPhone" name="linkPhone">
                    <input type="hidden" id="id" name="id"></td>

            </tr>

            <tr>
                <td><input type="button" id="tijiao"  value="提交">
                    <input type="button" class="modify"  value="修改">
                    <input type="reset" value="重置"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
