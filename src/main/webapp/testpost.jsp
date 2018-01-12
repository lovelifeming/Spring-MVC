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
    <%@include file="WEB-INF/jsp/common/staticresources.jsp" %>
</head>
<body>
<div style="margin-left: 45%;margin-top: 30px">
    <div style="height: 40px">
        <input type="button" value="TestPost" onclick="postTest('')">
    </div>
    <div style="height: 40px">
        <input type="button" value="TestPost1" onclick="postTest(1)">
    </div>
    <div style="height: 40px">
        <input type="button" value="TestPost2" onclick="postTest(2)">
    </div>
    <div style="height: 40px">
        <input type="button" value="TestPost3" onclick="postTest(3)">
    </div>
    <div style="height: 40px">
        <input type="button" value="TestPost4" onclick="postTest(4)">
    </div>
    <div style="height: 40px">
        <input type="button" value="TestPost5" onclick="postTest(5)">
    </div>
    <div style="height: 40px">
        <input type="button" value="TestPost6" onclick="postTest1(6)">
    </div>
</div>
</body>
<script type="text/javascript">
    var url = "http://localhost:8080/post/finduser";
    var params = {username: "admin", password: "123456"};

    function postTest(param) {
        postRequest(url + param, params, "application/x-www-form-urlencoded", function () {
        });
    }

    function postTest1(param) {
        //6
        postRequest(url + param, JSON.stringify(params), "application/json;charset=utf-8", function () {
        });
    }

    /**
     * POST请求
     * @param urlStr
     * @param param
     * @param contentType   "application/json;charset=utf-8"
     * @param callBack  function
     */
    function postRequest(urlStr, param, contentType, callBack) {
        $.ajax({
            type: "post",
            url: urlStr,
            dataType: "json",
            async: false,
            //RequestBody 接受参数时需设置 application/json;charset=utf-8
            contentType: contentType,
            data: param,
            success: function (data) {
                console.log(data);
                var json = JSON.parse(data);
                if (json.status == undefined || json.status == 0) {
                    return;
                }
                callBack(json);
                return json;
            },
            error: function (data) {
                console.log(data);
                return data;
            }
        })
    }
</script>
</html>
