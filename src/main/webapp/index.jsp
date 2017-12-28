<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <link rel="icon" href="/resources/images/favicon.ico">
    <script type="text/javascript" src="/resources/js/lib/jquery-3.2.1.min.js"></script>
</head>
<body>
<h2>Hello World!</h2>
<div>
    <a href="testpost.jsp">Test Post</a><br>
    <a href="testget.jsp">Test Get</a><br>
    <a href="testupload.jsp">Test Upload</a><br>
    <a href="testlogin.jsp">Test Login</a><br>

</div>
</body>
<script type="text/javascript">
    // Echarts require方式引入  路径配置
    require.config({
        paths: {
            echarts: 'http://echarts.baidu.com/build/dist'
        }
    });
    // 使用
    require(
        [
            'echarts',
            'echarts/chart/bar',
            'echarts/chart/line'
        ],
        drawEcharts
    );

    function drawEcharts(ec) {
        drawChart(ec);
    }

    function drawChart(ec) {

    };
</script>
</html>
