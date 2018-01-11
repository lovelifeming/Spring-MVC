/**
 * Created by zsm on 2017/11/15.
 */

var url = "http://localhost:8080/post/finduser6";
var param = {username: "admin", password: "123456"};

function postTest() {
    //0
    // postRequest(url,JSON.stringify(param),"application/x-www-form-urlencoded",function () { });
    //1,2，3，4
    //postRequest(url,param,"application/x-www-form-urlencoded",function () { });
    //5
    // postRequest(url, param, "application/x-www-form-urlencoded", function () { });

    //6
    //postRequest(url, JSON.stringify(param), "application/json;charset=utf-8", function () { });
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