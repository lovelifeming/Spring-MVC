<%--
  Created by IntelliJ IDEA.
  User: zengsm
  Date: 2018/7/19
  Time: 22:32
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>--%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <!--cdnjs官网： https://www.bootcdn.cn/  js仓库：https://www.bootcdn.cn/URI.js/  https://www.bootcdn.cn/jquery/ -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.1.1/css/bootstrap.min.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/URI.js/1.19.1/URI.min.js"></script>

    <title><fmt:message key="i18n.page.title"></fmt:message></title>
</head>
<script>
    $(document).ready(function () {
        $(".i18n-choose").click(function () {
            var uri = new URI();
            uri.setSearch("locale", $(this).attr("data-language"));
            location.href = uri.toString();
        });
    });
</script>

<style type="text/css">
    .i18n-choose {
        cursor: pointer;
    }

    .i18n-choose hover {
        text-decoration: underline;
    }
</style>
<body>
<br>
<h2>I18N Page</h2>
<a>
    <span class="i18n-choose" data-language="zh_CN">中文</span> |
    <span class="i18n-choose" data-language="en_US">English</span> |
    <span class="i18n-choose" data-language="de_DE">Deutsch</span>
</a>
<br><br>
show:
<br>
<table class="table table-striped" border="1" cellspacing="0" cellpadding="10">
    <tr>
        <td><fmt:message key="i18n.form.name"></fmt:message></td>
    </tr>
    <tr>
        <td><fmt:message key="i18n.product.name"></fmt:message></td>
    </tr>
    <tr>
        <td><fmt:message key="i18n.product.description"></fmt:message></td>
    </tr>
    <tr>
        <td><fmt:message key="i18n.product.price"></fmt:message></td>
    </tr>
    <tr>
        <td><fmt:message key="i18n.button.reset"></fmt:message></td>
    </tr>
    <tr>
        <td><fmt:message key="i18n.button.submit"></fmt:message></td>
    </tr>
</table>

</body>
</html>