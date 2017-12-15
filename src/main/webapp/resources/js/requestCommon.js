/**
 * 请求，回掉callBack
 * @param type
 * @param dataType
 * @param urlStr
 * @param param
 * @param callBack
 */
function requestCallBack(type,dataType,urlStr, param, callBack) {
    $.ajax({
        type: type,
        url: urlStr,
        dataType: dataType,
        async: false,
        //contentType: "application/json;charset=utf-8",  //RequestBody 接受参数时需添加
        data: JSON.stringify(param),
        success: function (data) {
            callBack(data);
        },
        error: function (data) {
            console.log(data);
            return data;
        }
    })
}
/**
 * POST请求,HttpServletRequest请求获取参数
 * @param urlStr
 * @param param
 * @callback callBack 回调函数
 */
function postRequestCallBack(urlStr, param, callBack) {
    $.ajax({
        type: "post",
        url: urlStr,
        dataType: "json",
        async: true,
        //contentType: "application/json;charset=utf-8",  //RequestBody 接受参数时需添加
        data: JSON.stringify(param),
        success: function (data) {
            callBack(data);
        },
        error: function (data) {
            console.log(data);
            return data;
        }
    })
}

/**
 * POST请求,包含了contentType请求头
 * @param urlStr
 * @param param
 * @callback callBack
 */
function postRequestIncludeContentTypeCallBack(urlStr, param, callback) {
    $.ajax({
        type: "post",
        url: urlStr,
        dataType: "json",
        async: true,
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(param),
        success: function (data) {
            callback(data);
        },
        error: function (data) {
            console.log(data);
            return data;
        }
    })
}
