/**
 * 将表单数据转化为JSON数据 form为表单对象，如$("#form_id"),返回序列化数据
 *
 * @param form_id   表单id
 * @returns {any}   返回json
 */
function formToJson(form) {
    var param = form.serialize();
    //防止中文乱码
    var data = decodeURIComponent(param, true);
    data = data.replace(/&/g, "','");
    data = data.replace(/=/g, "':'");
    data = "({'" + data + "'})";
    var obj = eval(data);
    obj = JSON.stringify(obj);
    return obj;
}