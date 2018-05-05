<%--
  Created by IntelliJ IDEA.
  User: zengsm
  Date: 2018/1/12
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>--%>
<%
    String contextPath = request.getContextPath();
    String scheme = request.getScheme();
    String serverName = request.getServerName();
    String basePath = scheme + "://" + serverName + ":" + request.getServerPort() + contextPath + "/";
%>
<html>
<head>
    <title>Result</title>
    <%--<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">--%>
</head>
<body>
<div>
    <p>Test Result Web!</p>
    <P>${message}</P>
    <P>${status}</P>
    <P>${data}</P>
    <P>${pageContext.request.contextPath}</P>
</div>
</body>
</html>
