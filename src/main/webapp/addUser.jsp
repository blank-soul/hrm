<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 2020/5/19
  Time: 11:11
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
<form class="layui-form" action="" style="margin: 10px 10px 0px 0px;">
    <div class="layui-form-item">
        <label class="layui-form-label">用户名称</label>
        <div class="layui-input-block">
            <input type="text" name="username" placeholder="请输入" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">登录名称</label>
        <div class="layui-input-block">
            <input type="text" name="loginname" placeholder="请输入" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">登录密码</label>
        <div class="layui-input-block">
            <input type="password" name="password" placeholder="请输入" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">建档时间</label>
        <div class="layui-input-block">
            <input type="date" name="createDate" placeholder="请输入" autocomplete="off" class="layui-input">
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
            var userId = router.search.userId;
            var username = data.field.username;
            var loginname = data.field.loginname;
            var password = data.field.password;
            var createDate = data.field.createDate;
            var htm = "username="+username+"&loginname="+loginname+"&password="+password+"&createDate="+createDate;
            if(undefined != userId){
                $.post("/user/update", htm+"&userId="+userId, function (res) {
                    if(0 != res && null != res){
                        alert("修改成功！")
                    } else {
                        alert("修改失败！")
                    }
                    location.reload();
                });
            } else {
                $.post("/user/insert", htm, function (res) {
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
    });
</script>
</body>
</html>
