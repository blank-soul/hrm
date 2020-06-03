<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>table模块快速使用</title>
    <link rel="stylesheet" href="/layui/css/layui.css" media="all">
</head>
<body>

<div class="demoTable">
    搜索标题：
    <div class="layui-inline">
        <input class="layui-input" name="title" id="selectByTitle" autocomplete="off">
    </div>
    搜索内容：
    <div class="layui-inline">
        <input class="layui-input" name="content" id="selectByContent" autocomplete="off">
    </div>
    <button class="layui-btn" data-type="reload">搜索</button>
</div>

<table id="tab" lay-filter="test"></table>

<script type="text/html" id="barDemo">
    <shiro:hasPermission name="notice:update">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="notice:delete">
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </shiro:hasPermission>
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
            ,url: '/notice/selectAll' //数据接口
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
                ,{field: 'title', title: '标题'}
                ,{field: 'content', title: '详情'}
                ,{field: 'username', title: '发布人'}
                ,{field: 'createDate', title: '发布日期'}
                ,{fixed: 'right', align:'center', toolbar: '#barDemo'}
            ]]
        });

        //监听行工具事件
        table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                ,layEvent = obj.event; //获得 lay-event 对应的值
            if(layEvent === 'del'){
                layer.confirm('真的删除行么', function(index){
                    $.post("/notice/delete", "id="+data.id, function (res) {
                        if(0 != res && null != res){
                            layer.msg("删除成功！");
                            obj.del(); //删除对应行（tr）的DOM结构
                            layer.close(index);
                        } else {
                            layer.msg("删除失败！");
                        }
                    });
                });
            } else if(layEvent === 'edit'){
                location.href = '/addNotice.jsp?noticeId='+data.id;
            }
        });

        var $ = layui.$, active = {
            reload: function(){
                var selectByTitle = $('#selectByTitle');
                var selectByContent = $('#selectByContent');
                //执行重载
                table.reload('testReload', {
                    url: '/notice/selectByParam'
                    ,page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                        key: {
                            title: selectByTitle.val()
                            ,content: selectByContent.val()
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
