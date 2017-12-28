/**
 * 动态生成Echarts图表,
 * param.text       主标题
 * param.subtext    子标题
 * param.legend     图例
 * param.xAxis      X坐标数据
 * param.series     Y坐标数据
 * param.echart     Echarts图标对象
 * @param param
 */
function dynamicDrawEchart(param) {
    var option = {
        title: {
            text: param.text,
            subtext: param.subtext
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: param.legend
        },
        calculable: true,
        xAxis: [
            {
                type: 'category',
                boundaryGap: false,
                data: param.xAxis
            }
        ],
        yAxis: [
            {
                type: 'value'
            }
        ],
        series: param.series
    };
    param.echart.setOption(option, true);
    $(window).resize(function () {
        param.echart.resize();
    });
}
