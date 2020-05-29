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
<form class="layui-form" style="margin: 10px 10px 0px 0px;">
    <div class="layui-form-item">
        <label class="layui-form-label">公告标题</label>
        <div class="layui-input-block">
            <input type="text" name="noticeTitle" placeholder="请输入" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">公告内容</label>
        <div class="layui-input-block">
            <input type="text" name="noticeContent" placeholder="请输入" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">发布时间</label>
        <div class="layui-input-block">
            <input type="date" name="createDate" placeholder="请输入" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">发布人</label>
        <div class="layui-input-block">
            <select name="userId" id="userId">
                <option value=""></option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

<script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.js"></script>
<script src="/layui/layui.js"></script>
<script>
    layui.use(['form'], function(){
        var form = layui.form;
        var router = layui.router();

        //监听提交
        form.on('submit(formDemo)', function(data){
            var noticeId = router.search.noticeId;
            var noticeTitle = data.field.noticeTitle;
            var noticeContent = data.field.noticeContent;
            var createDate = data.field.createDate;
            var userId = data.field.userId;
            var htm = "noticeTitle="+noticeTitle+"&noticeContent="+noticeContent+"&createDate="+createDate+"&userId="+userId;
            if(undefined != noticeId){
                $.post("/notice/update", htm+"&noticeId="+noticeId, function (res) {
                    if(0 != res && null != res){
                        alert("修改成功！")
                    } else {
                        alert("修改失败！")
                    }
                    location.reload();
                });
            } else {
                $.post("/notice/insert", htm, function (res) {
                    if(0 != res && null != res){
                        alert("新增成功！")
                    } else {
                        alert("新增失败！")
                    }
                    location.reload();
                });
            }
            return false;
        });

        $(function () {
            $.post("/user/selectAllUser", function (res) {
                for(var i = 0; i < res.length; i++){
                    var post = res[i];
                    var htm = "<option value="+post.id+">"+post.username+"</option>";
                    $("#userId").append(htm);
                    form.render('select');
                }
            });
        });
    });
</script>
</body>
</html>
