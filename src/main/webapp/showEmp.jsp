<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>table模块快速使用</title>
    <link rel="stylesheet" href="/layui/css/layui.css" media="all">
</head>
<body>

<table id="tab" lay-filter="test"></table>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script src="/layui/layui.js"></script>
<script>
    layui.use(['table', 'laypage'], function(){
        var table = layui.table,
            laypage = layui.laypage;

        table.render({
            elem: '#tab'
            ,url: '/emp/selectAll' //数据接口
            ,page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                ,curr: 1    // 设定初始在第 1 页
                ,limit: 5   // 每页显示5条数据
                ,limits: [2,5,10]
                ,groups: 2 //只显示 1 个连续页码
                ,first: false //不显示首页
                ,last: false //不显示尾页
            }
            ,cols: [[ //表头
                {field: 'id', title: 'ID', sort: true, fixed: 'left'}
                ,{field: 'name', title: '姓名'}
                ,{field: 'sex', title: '性别'}
                ,{field: 'phone', title: '手机号码'}
                ,{field: 'email', title: '邮箱'}
                ,{field: 'jobId', title: '职位'}
                ,{field: 'deptId', title: '部门'}
                ,{field: 'education', title: '学历'}
                ,{field: 'cardId', title: '身份证号码'}
                ,{field: 'address', title: '联系地址'}
                ,{field: 'createDate', title: '建档日期'}
                ,{fixed: 'right', width: 200, align:'center', toolbar: '#barDemo'}
            ]]
        });

        //监听行工具事件
        table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                ,layEvent = obj.event; //获得 lay-event 对应的值
            if(layEvent === 'del'){
                layer.confirm('真的删除行么', function(index){
                    obj.del(); //删除对应行（tr）的DOM结构
                    layer.close(index);
                    //向服务端发送删除指令
                });
            } else if(layEvent === 'edit'){
                layer.msg('编辑操作');
            }
        });
    });
</script>
</body>
</html>
