/**
 * Created by zsm on 2017/11/15.
 */

var commitRequest = function () {
    $.ajax({
        type: 'POST',
        url: "/user/finduser",
        dataType: "json",
        async: true,
        data: JSON.stringify({"no": user_no_form.no.value}),
        success: function (data) {
            alert(data)
        },
        error: function (data) {
            alert(data)
        }
    })
};
var commitRequest1 = function () {
    $.ajax({
        type: 'POST',
        url: "/user/finduser1",
        dataType: "son",
        async: true,
        data: JSON.stringify({"username": document.getElementById("user_name").value}),
        success: function (data) {
            alert(data)
        },
        error: function (data) {
            alert(data)
        }
    });
};
var commitRequest2 = function () {
    $.ajax({
        type: 'POST',
        url: "/user/finduser2",
        dataType: "json",
        async: true,
        data: JSON.stringify({
            "username": user_from1.name.value,
            "password": user_from1.pwd.value
        }),
        success: function (data) {
            alert(data)
        },
        error: function (data) {
            alert(data)
        }
    })
};
var commitRequest3 = function () {
    $.ajax({
        type: 'get',
        url: "/user/finduser3/" + document.getElementById("user_name2").value,
        dataType: "json",
        async: true,
        //data: {"username": document.getElementById("user_name2").value},
        success: function (data) {
            alert(data)
        },
        error: function (data) {
            alert(data)
        }
    })
};
var commitRequest4 = function () {
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
