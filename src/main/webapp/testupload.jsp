<%--
  Created by IntelliJ IDEA.
  User: zsm
  Date: 2017/11/15
  Time: 22:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
    <script src="./resources/js/lib/jquery-3.2.1.min.js"></script>
</head>
<body>
<div style="margin-left: 20%;margin-top: 30px">
    <div>
        <form method="POST" action="/upload/file" enctype="multipart/form-data">
            选择一个文件:
            <input type="file" name="upLoadFile"/>
            <input type="text" name="number"/>
            <input type="submit" value="上传"/>
        </form>
    </div>
    <div>
        <form method="POST" action="/upload/file1" enctype="multipart/form-data">
            选择一个文件:
            <input type="file" name="upLoadFile"/>
            <input type="text" name="number"/>
            <input type="submit" value="上传"/>
        </form>
    </div>
    <div>
        <form method="POST" action="/upload/file2" enctype="multipart/form-data">
            选择一个文件:
            <input type="file" name="upLoadFile"/>
            <input type="text" name="number"/>
            <input type="submit" value="上传"/>
        </form>
    </div>
    <div>
        <form method="POST" action="/upload/file3" enctype="multipart/form-data">
            选择一个文件:
            <input type="file" name="upLoadFile"/>
            <input type="text" name="number"/>
            <input type="submit" value="上传"/>
        </form>
    </div>
    <div>
        <form method="POST" action="/upload/file4" enctype="multipart/form-data">
            选择一个文件:
            <input type="file" name="upLoadFile"/>
            <input type="file" name="upLoadFile"/>
            <input type="submit" value="上传"/>
        </form>
    </div>
    <div>
        <form method="POST" action="/upload/file5" enctype="multipart/form-data">
            选择一个文件:
            <input type="file" name="upLoadFile"/>
            <input type="file" name="upLoadFile"/>
            <input type="file" name="upLoadFile"/>
            <input type="submit" value="上传"/>
        </form>
    </div>
    <div>
        <form method="POST" action="/upload/file6" enctype="multipart/form-data">
            选择一个文件:
            <input type="file" name="upLoadFile"/>
            <input type="text" name="number"/>
            <input type="submit" value="上传"/>
        </form>

        <input type="file" onchange="selectImage(this);"/>
        <input type="button" onclick="uploadImage();" value="提交"/>
    </div>
    <input name="attach" class=" box2" type="file" onchange="checkFileSize(this)" value="检查文件大小"/>检查文件大小
    <div>
        <xmp>注意： 看看表单
            <from>中是否包含 enctype="multipart/form-data" enctype="multipart/form-data" 会让数据以二进制传输
        </xmp>
        <img id="image" src=""/>
    </div>
</div>
</body>
<script>
    var isIE = /msie/i.test(navigator.userAgent) && !window.opera;

    //检测上传文件的大小
    function checkFileSize(target) {
        //获取的大小单位为b
        var fileSize = 0;
        if (isIE && !target.files) {
            var filePath = target.val();
            var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
            var file = fileSystem.GetFile(filePath);
            fileSize = file.Size;
        } else {
            fileSize = target.files[0].size;
        }
        //获取的文件大小单位b
        //限制的大小为20MB
        var fileMaxSize = 20 * 1024 * 1024;
        if (fileSize > fileMaxSize) {
            //超过指定大小,删除附件
            alert("上传文件过大，请限制在20MB以下", "", 1);
            //上传附件文件过大，删除附件
            deleteAttach();
            return false;
        }
        return true;
    }

    var image = '';

    function selectImage(file) {
        debugger;
        if (!file.files || !file.files[0]) {
            return;
        }
        var reader = new FileReader();
        reader.onload = function (evt) {
            document.getElementById('image').src = evt.target.result;
            image = evt.target.result;
        }
        reader.readAsDataURL(file.files[0]);
    }

    function uploadImage() {
        debugger;
        image = JSON.stringify(image)
        $.ajax({
            type: 'POST',
            url: '/upload/file6',
            data: {base64: image},
            async: false,
            dataType: 'json',
            success: function (data) {
                alert(data.success)
                if (data.success) {
                    alert('上传成功');
                } else {
                    alert('上传失败');
                }
            },
            error: function (err) {
                alert('网络故障');
            }
        });
    }
</script>
</html>
