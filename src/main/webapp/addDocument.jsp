<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 2020/5/19
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Title</title>
    <link rel="stylesheet" href="/layui/css/layui.css" media="all">
</head>
<body>
<form class="layui-form" action="/document/unload" method="post" enctype="multipart/form-data" style="margin: 10px 10px 0px 0px;">
    <div class="layui-form-item">
        <label class="layui-form-label">文件标题</label>
        <div class="layui-input-block">
            <input type="text" name="documentTitle" placeholder="请输入" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">文件上传</label>
        <div class="layui-input-block">
            <input type="file" name="upfile" class="layui-upload">
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">文件详情</label>
        <div class="layui-input-block">
            <textarea name="documentRemark" placeholder="请输入内容" class="layui-textarea"></textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
        </div>
    </div>
</form>

<script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.js"></script>
<script src="/layui/layui.js"></script>
<script>
    function getQueryVariable(variable)
    {
        var query = window.location.search.substring(1);
        var vars = query.split("&");
        for (var i=0;i<vars.length;i++) {
            var pair = vars[i].split("=");
            if(pair[0] == variable){return pair[1];}
        }
        return(false);
    }

    // layui.use(['form','upload'], function(){
    //     var form = layui.form;
    //     var router = layui.router();
    //     var upload = layui.upload;
    //
    //     //监听提交
    //     form.on('submit(formDemo)', function(data){
    //         var documentId = getQueryVariable("documentId");
    //         var documentTitle = data.field.documentTitle;
    //         var filename = data.field.filename;
    //         var documentRemark = data.field.documentRemark;
    //         var createDate = data.field.createDate;
    //         var userId = data.field.userId;
    //         var htm = "documentTitle="+documentTitle+"&filename="+filename+"&documentRemark="+documentRemark+"&createDate="+createDate+"&userId="+userId;
    //         if(undefined != documentId && null != documentId && '' != documentId){
    //             $.post("/document/update", htm+"&documentId="+documentId, function (res) {
    //                 if(0 != res && null != res){
    //                     alert("修改成功！")
    //                 } else {
    //                     alert("修改失败！")
    //                 }
    //                 location.reload();
    //             });
    //         } else {
    //             $.post("/document/insert", htm, function (res) {
    //                 if(0 != res && null != res){
    //                     alert("新增成功！")
    //                 } else {
    //                     alert("新增失败！")
    //                 }
    //                 location.reload();
    //             });
    //         }
    //         return false;
    //     });
    // });
</script>
</body>
</html>
