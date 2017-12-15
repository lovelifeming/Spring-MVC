/**
 * 动态生成表格，数据是一行一行的
 * @param param
 *
 * param.table_selector         表格id
 *param.datas                   表格数据
 * param.headData               表格头
 */
function dynamicCreateTableByRow(param) {
    var table = $("#" + param.table_selector);
    table.innerHTML = "";

    dynamicCreateTableHeader(param);

    var datas = param.datas;
    //每一个数组是表格的一行
    for (var i in datas) {
        var data = datas[i];
        var contentRow = table.insertRow();
        for (var j in data) {
            var cell = contentRow.insertCell();
            cell.innerText = data[j];
        }
    }
    //每一个数组是表格的一列
    for (var i in datas) {
        var contentRow = table.insertRow();
        var data = datas[i];
        for (var j in  data) {
            var cell = contentRow.insertCell();
            cell.innerText = datas[j][i];
        }
    }
}

/**
 * 动态生成表格,数据是一列一列的
 * @param param
 *
 * param.table_selector         表格id
 *param.datas                   表格数据
 * param.headData               表格头
 */
function dynamicCreateTableByColumn(param) {
    var table = $("#" + param.table_selector);
    table.innerHTML = "";

    dynamicCreateTableHeader(param);
    //每一个数组是表格的一列
    for (var i in datas) {
        var contentRow = table.insertRow();
        var data = datas[i];
        for (var j in  data) {
            var cell = contentRow.insertCell();
            cell.innerText = datas[j][i];
        }
    }
}

function dynamicCreateTableHeader(param) {
    var table = $("#" + param.table_selector);
    //插入表头信息
    var headrow = table.insertRow();
    var headData = param.headData;
    if (headData == undefined || headData == null) {
        return;
    }
    for (var i in headData) {
        var cell1 = headrow.insertCell();
        cell1.innerText = headData[i];
    }
}
