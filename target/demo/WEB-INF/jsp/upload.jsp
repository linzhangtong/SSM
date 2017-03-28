<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图片上传预览</title>
    <script type="text/javascript">
        function imgPreview(fileDom) {
            //判断是否支持FileReader
            if (window.FileReader) {
                var reader = new FileReader();
            } else {
                alert("您的设备不支持图片预览功能，如需该功能请升级您的设备！");
            }

            //获取文件
            var file = fileDom.files[0];
            var imageType = /^image\//;
            //是否是图片
            if (!imageType.test(file.type)) {
                alert("请选择图片！");
                return;
            }
            //读取完成
            reader.onload = function (e) {
                //获取图片dom
                var img = document.getElementById("preview");
                //图片路径设置为读取的图片
                img.src = e.target.result;
            };
            reader.readAsDataURL(file);
        }
    </script>
    <style type="text/css">
        #div {
            width: 800px;
            height: 800px;
            position: relative;
            text-align: center;
            left: 0;
            right: 0;
            top: 0;
            bottom: 0;
            margin: auto;
            /*50%为自身尺寸的一半*/
        }

        #preview {
            width: 400px;
            height: 400px;
            border: 2px solid blue;
            position: absolute;
            text-align: center;
            margin-top: 5px;
            margin-left: -180px;
            /*50%为自身尺寸的一半*/
        }

        #file {
            position: absolute;
            margin-top: 250px;
            margin-left: -370px;
        }

        #content {
            position: absolute;
            margin-top: 450px;
            margin-left: -240px;
            width: 500px;
            height: 100px;
        }

        h2 {
            position: absolute;
            margin-top: 420px;
            margin-left: 280px;
        }

        #submit {
            position: absolute;
            margin-top: 580px;
        / / margin-left: px;
        }
    </style>
</head>
<body>
<div id="div">
    <form action="upload" enctype="multipart/form-data" method="post">
        <img id="preview"/>
        <input id="file" type="file" name="file" onchange="imgPreview(this)"/>
        <h2>请你对图片描述一下哦！</h2>
        <textarea type="text" id="content" name="content"></textarea>
        <input type="submit" id="submit"/>
    </form>
</div>
<br/>
</body>
</html>