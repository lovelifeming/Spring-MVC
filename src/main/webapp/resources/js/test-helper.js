/**
 * Created by zsm on 2017/11/17.
 */
var commitRequest = function () {
    $.ajax({
        type: 'POST',
        url: "/user/finduser4",
        context: "json",
        async: true,
        data: {
            "username": document.getElementById("user_name3").value,
            "pwd": document.getElementById("user_pwd3").value
        },
        success: function (data) {
            alert(data);
        },
        error: function (data) {
            alert(data);
        }
    });
};
/**
 * POST请求
 * @param urlStr
 * @param param
 */
function postRequest(urlStr, param, callBack) {
    $.ajax({
        type: "post",
        url: urlStr,
        dataType: "json",
        async: true,
        //contentType: "application/json;charset=utf-8",  //RequestBody 接受参数时需添加
        data: JSON.stringify(param),
        success: function (data) {
            console.log(data);
            var json = JSON.parse(data);
            if (json.status == undefined || json.status == 0) {
                return;
            }
            debugger;
            callBack(json);
            return json;
        },
        error: function (data) {
            console.log(data);
            return data;
        }
    })
}

/**
 * 获取前半年时间
 * @returns {string}
 */
function getPastHalfYear() {
    // 先获取当前时间
    var curDate = (new Date()).getTime();
    // 将半年的时间单位换算成毫秒
    var halfYear = 365 / 2 * 24 * 3600 * 1000;
    // 半年前的时间（毫秒单位）
    var pastResult = curDate - halfYear;
    // 日期函数，定义起点为半年前
    var pastDate = new Date(pastResult);
    var pastYear = pastDate.getFullYear();
    var pastMonth = pastDate.getMonth() + 1;
    var pastDay = pastDate.getDay();
    return pastYear + "-" + pastMonth + "-" + pastDay;
}

/**
 * 获取前181天前时间,2017-8-16
 * @returns {string}
 */
function getPastQuarterly() {
    // 先获取当前时间
    var curDate = (new Date()).getTime();
    // 将半年的时间单位换算成毫秒
    var halfYear = 365 / 4 * 24 * 3600 * 1000;
    // 半年前的时间（毫秒单位）
    var pastResult = curDate - halfYear;
    // 日期函数，定义起点为半年前
    var pastDate = new Date(pastResult);
    var pastYear = pastDate.getFullYear();
    var pastMonth = pastDate.getMonth() + 1;
    var pastDay = pastDate.getDate();
    return pastYear + "-" + pastMonth + "-" + pastDay;
    var name=document.getElementsByName("name");    //根据名字获取节点对象
    var tag=document.getElementsByTagName("tagName");    //根据标签获取节点对象
    $("selector")     //type或者标签
    $(".selector")     //class
    $("#selector")     //id
}

/**
 * 获取一年前的时间,返回 2017-11-17
 */
function getPastOneYearYM() {
    var nowDate = new Date();
    var year = nowDate.getFullYear() - 1;
    var month = nowDate.getMonth() + 1;
    var day = nowDate.getDate();
    var beforeYear = year + "-" + month + "-" + day;
    console.log(beforeYear);
    return beforeYear;
}