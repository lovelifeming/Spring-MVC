<%--
  Created by IntelliJ IDEA.
  User: zsm
  Date: 2017/11/15
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test Redirect</title>
    <%@include file="../../WEB-INF/jsp/common/staticresources.jsp" %>
</head>
<body>
<div style="margin-left: 15%;margin-top: 30px">
    <p>Test Redirect Request Web!</p>
    <P>${message}</P>
    <P>${status}</P>
    <P>${data}</P>
</div>
<div style="margin-left: 15%;margin-top: 30px">
    <span>重定向跳转</span>
    <div style="height: 40px">
        <a href="/jump/refresh">refresh</a>
    </div>
    <div style="height: 40px">
        <a href="/jump/header">header</a>
    </div>
    <div style="height: 40px">
        <a href="/jump/sendredirect">sendRedirect</a>
    </div>
</div>
</body>
</html>
