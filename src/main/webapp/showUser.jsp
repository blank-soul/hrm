<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>table模块快速使用</title>
    <link rel="stylesheet" href="/layui/css/layui.css" media="all">
</head>
<body>

<div class="demoTable">
    用户名：
    <div class="layui-inline">
        <input class="layui-input" name="username" id="selectByUsername" autocomplete="off">
    </div>
    用户状态：
    <div class="layui-inline">
        <select class="layui-input" name="status" id="selectByStatus" autocomplete="off">
            <option value="">请选择</option>
            <option value="1">超级管理员</option>
            <option value="2">管理员</option>
        </select>
    </div>
    <button class="layui-btn" data-type="reload">搜索</button>
</div>

<table id="tab" lay-filter="test"></table>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.js"></script>
<script src="/layui/layui.js"></script>
<script>
    layui.use(['table', 'laypage'], function(){
        var table = layui.table,
            laypage = layui.laypage;

        table.render({
            elem: '#tab'
            ,id: 'testReload'
            ,url: '/user/selectAll' //数据接口
            ,method: 'post'
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
                ,{field: 'username', title: '用户名'}
                ,{field: 'loginname', title: '登录账号'}
                ,{field: 'password', title: '登录密码'}
                ,{field: 'status', title: '状态'}
                ,{field: 'createDate', title: '建档日期'}
                ,{fixed: 'right', align:'center', toolbar: '#barDemo'}
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

        var $ = layui.$, active = {
            reload: function(){
                var selectByUsername = $('#selectByUsername');
                var selectByStatus = $('#selectByStatus');
                //执行重载
                table.reload('testReload', {
                    url: '/user/selectByParam'
                    ,page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                        key: {
                            username: selectByUsername.val()
                            ,status: selectByStatus.val()
                        }
                    }
                });
            }
        };

        $('.demoTable .layui-btn[data-type]').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });
</script>
</body>
</html>
