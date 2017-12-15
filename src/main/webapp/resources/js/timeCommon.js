/**
 * 获取指定时间24小时前的时间
 */
function getPastOneDay(param) {
    var d = new Date(param);
    d.setTime(d.getTime() - 24 * 60 * 60 * 1000);
    var dt = d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate();
    return new Date(dt);
}

/**
 * 获取前30天前时间,2017-8-16
 * @returns {string}
 */
function getPastThirthDay() {
    // 先获取当前时间
    var curDate = (new Date()).getTime();
    // 将时间单位换算成毫秒
    var oneMonth = 30 * 24 * 3600 * 1000;

    var pastResult = curDate - oneMonth;
    var pastDate = new Date(pastResult);
    var pastYear = pastDate.getFullYear();
    var pastMonth = pastDate.getMonth() + 1;
    var pastDay = pastDate.getDate();
    return pastYear + "-" + pastMonth + "-" + pastDay;
}

/**
 * 获取前三个月的时间
 * @returns {string}
 */
function getPastQuarterly() {
    var curDate = new Date();
    curDate.setMonth(curDate.getMonth() - 2);
    var pastDate = new Date(curDate);
    var pastYear = pastDate.getFullYear();
    var pastMonth = pastDate.getMonth() + 1;
    var pastDay = pastDate.getDate();
    return pastYear + "-" + pastMonth + "-" + pastDay;
}

/**
 * 获取前182.5天的时间
 * @returns {string}
 */
function getPastHalfYear() {
    // 先获取当前时间
    var curDate = (new Date()).getTime();
    // 将半年的时间单位换算成毫秒
    var halfYear = 365 / 2 * 24 * 3600 * 1000;
    // 半年前的时间（毫秒单位）
    var pastResult = curDate - halfYear;
    var pastDate = new Date(pastResult);
    var pastYear = pastDate.getFullYear();
    var pastMonth = pastDate.getMonth() + 1;
    var pastDay = pastDate.getDay();
    return pastYear + "-" + pastMonth + "-" + pastDay;
}

/**
 * 获取一年前的时间
 */
function getPastOneYear() {
    var nowDate = new Date();
    var year = nowDate.getFullYear() - 1;
    var month = nowDate.getMonth() + 1;
    var day = nowDate.getDate();
    var beforeYear = year + "-" + month + "-" + day;
    return beforeYear;
}
