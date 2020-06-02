<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 2020/5/19
  Time: 11:12
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
        <label class="layui-form-label">姓名</label>
        <div class="layui-input-block">
            <input type="text" name="empName" placeholder="请输入" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">部门</label>
        <div class="layui-input-block">
            <select name="deptId" id="deptId">
                <option value=""></option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">职位</label>
        <div class="layui-input-block">
            <select name="jobId" id="jobId">
                <option value=""></option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">身份证号</label>
        <div class="layui-input-block">
            <input type="text" name="cardId" placeholder="请输入" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">住址</label>
        <div class="layui-input-block">
            <input type="text" name="address" placeholder="请输入" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">手机</label>
        <div class="layui-input-block">
            <input type="text" name="phone" placeholder="请输入" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">电子邮箱</label>
        <div class="layui-input-block">
            <input type="email" name="email" placeholder="请输入" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">性别</label>
        <div class="layui-input-block">
            <select name="sex" id="sex">
                <option value=""></option>
                <option value="男">男</option>
                <option value="女">女</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">学历</label>
        <div class="layui-input-block">
            <select name="education" id="education">
                <option value=""></option>
                <option value="专科">专科</option>
                <option value="本科">本科</option>
            </select>
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

    layui.use(['form'], function(){
        var form = layui.form;
        var router = layui.router();

        //监听提交
        form.on('submit(formDemo)', function(data){
            var empId = getQueryVariable("empId");
            var empName = data.field.empName;
            var cardId = data.field.cardId;
            var address = data.field.address;
            var phone = data.field.phone;
            var email = data.field.email;
            var sex = data.field.sex;
            var education = data.field.education;
            var createDate = data.field.createDate;
            var deptId = data.field.deptId;
            var jobId = data.field.jobId;
            var htm = "empName="+empName+"&cardId="+cardId+"&address="+address+"&phone="+phone+"&email="+email+"&sex="+sex+"&education="+education+"&createDate="+createDate+"&deptId="+deptId+"&jobId="+jobId;
            if(undefined != empId && null != empId && '' != empId){
                $.post("/emp/update", htm+"&empId="+empId, function (res) {
                    if(0 != res && null != res){
                        alert("修改成功！")
                    } else {
                        alert("修改失败！")
                    }
                    location.reload();
                });
            } else {
                $.post("/emp/insert", htm, function (res) {
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
            $.post("/dept/selectAllDept", function (res) {
                for(var i = 0; i < res.length; i++){
                    var post = res[i];
                    var htm = "<option value="+post.id+">"+post.name+"</option>";
                    $("#deptId").append(htm);
                    form.render('select');
                }
            });

            $.post("/job/selectAllJob", function (res) {
                for(var i = 0; i < res.length; i++){
                    var post = res[i];
                    var htm = "<option value="+post.id+">"+post.name+"</option>";
                    $("#jobId").append(htm);
                    form.render('select');
                }
            });
        });
    });
</script>
</body>
</html>
