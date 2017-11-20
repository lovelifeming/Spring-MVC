/**
 * Created by zsm on 2017/11/17.
 */
function getThreeMonthBefor() {
    var resultDate, year, month, date, hms;
    var currDate = new Date();
    year = currDate.getFullYear();
    month = currDate.getMonth() + 1;
    date = currDate.getDate();
    hms = currDate.getHours() + ':' + currDate.getMinutes() + ':' + (currDate.getSeconds() < 10 ? '0' + currDate.getSeconds() : currDate.getSeconds());
    switch (month) {
        case 1:
        case 2:
        case 3:
            month += 9;
            year--;
            break;
        default:
            month -= 3;
            break;
    }
    //天数为做处理
    month = (month < 10) ? ('0' + month) : month;
    resultDate = year + '-' + month + '-' + date + ' ' + hms;
    return resultDate;
}

