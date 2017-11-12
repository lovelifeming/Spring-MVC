<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <script src="resources/js/lib/jquery-3.1.1.js"></script>
</head>
<body>
<h2>Hello World!</h2>
<div>
    <a href="/WEB-INF/view/test_request.jsp">Test Request</a>
    <a href="/WEB-INF/view/test_upload.jsp">Test Upload</a>
</div>
<div>
    <form name="user_no_form">
        <input name="no" value="" type="text" id="user_no">
        <button type="button" onclick="commitRequest()">Commit</button>
    </form>
</div>
<div>
    <form>
        <input name="name" value="" type="text" id="user_name">
        <%--<input name="pwd" value="" type="text">--%>
        <button type="button" onclick="commitRequest1()">Commit</button>
    </form>
</div>
<div>
    <form name="user_from1">
        <input name="name" value="" type="text" id="user_name1">
        <input name="pwd" value="" type="text" id="user_pwd1">
        <button type="button" onclick="commitRequest2()">Commit</button>
    </form>
</div>
<div>
    <form>
        <input name="name" value="" type="text" id="user_name2">
        <%--<input name="pwd" value="" type="text">--%>
        <button type="button" onclick="commitRequest3()">Commit</button>
    </form>
</div>
<div>
    <form>
        <input name="name" value="" type="text" id="user_name3">
        <input name="pwd" value="" type="text" id="user_pwd3">
        <button type="button" onclick="commitRequest4()">Commit</button>
    </form>
</div>
<div>
    <label>上传文件示例</label>
    <form action="/upload/file" method="post" enctype="multipart/form-data">
        <input type="file" name="file"/>
        <input type="submit" value="Commit">
    </form>
</div>

</body>
<script type="text/javascript">
    var commitRequest = function () {
        $.ajax({
            type: 'POST',
            url: "/user/finduser",
            dataType: "json",
            async: true,
            data: JSON.stringify({"no": user_no_form.no.value}),
            success: function (data) {
                alert(data)
            },
            error: function (data) {
                alert(data)
            }
        })
    };
    var commitRequest1 = function () {
        $.ajax({
            type: 'POST',
            url: "/user/finduser1",
            dataType: "application/json",
            async: true,
            data: JSON.stringify({"username": document.getElementById("user_name").value}),
            success: function (data) {
                alert(data)
            },
            error: function (data) {
                alert(data)
            }
        });
    };
    var commitRequest2 = function () {
        $.ajax({
            type: 'POST',
            url: "/user/finduser2",
            dataType: "application/json",
            async: true,
            data: JSON.stringify({
                "username": user_from1.name.value,
                "password": user_from1.pwd.value
            }),
            success: function (data) {
                alert(data)
            },
            error: function (data) {
                alert(data)
            }
        })
    };
    var commitRequest3 = function () {
        $.ajax({
            type: 'POST',
            url: "/user/finduser1",
            dataType: "application/json",
            async: true,
            data: JSON.stringify({"username": document.getElementById("user_name2").value}),
            success: function (data) {
                alert(data)
            },
            error: function (data) {
                alert(data)
            }
        })
    };
    var commitRequest4 = function () {
        $.ajax({
            type: 'POST',
            url: "/user/finduser4",
            context: "application/json",
            async: true,
            data: JSON.stringify({
                "username": document.getElementById("user_name3").value,
                "pwd": document.getElementById("user_pwd3").value
            }),
            success: function (data) {
                alert(data);
            },
            error: function (data) {
                alert(data);
            }
        });
    };
</script>
</html>
