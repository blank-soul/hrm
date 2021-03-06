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
        <label class="layui-form-label">职能名称</label>
        <div class="layui-input-block">
            <input type="text" name="jobName" placeholder="请输入" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">详情</label>
        <div class="layui-input-block">
            <textarea name="jobRemark" placeholder="请输入内容" class="layui-textarea"></textarea>
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
            var jobId = getQueryVariable("jobId");
            var jobName = data.field.jobName;
            var jobRemark = data.field.jobRemark;
            var htm = "jobName="+jobName+"&jobRemark="+jobRemark;
            if(undefined != jobId && null != jobId && '' != jobId){
                $.post("/job/update", htm+"&jobId="+jobId, function (res) {
                    if(0 != res && null != res){
                        alert("修改成功！")
                    } else {
                        alert("修改失败！")
                    }
                    location.reload();
                });
            } else {
                $.post("/job/insert", htm, function (res) {
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