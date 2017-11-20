<%--
  Created by IntelliJ IDEA.
  User: zsm
  Date: 2017/11/15
  Time: 22:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test Post</title>
    <script type="text/javascript" src="resources/js/testpost.js"></script>
</head>
<body>
<div>
    <a href="WEB-INF/view/test_request.jsp">Test Request</a>
    <a href="WEB-INF/view/test_upload.jsp">Test Upload</a>
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
</body>
</html>
