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
    <div>
        姓名：
        <div class="layui-inline">
            <input class="layui-input" name="name" id="selectByName" autocomplete="off">
        </div>
        &nbsp;&nbsp;手机：
        <div class="layui-inline">
            <input class="layui-input" name="phone" id="selectByPhone" autocomplete="off">
        </div>
        &nbsp;&nbsp;身份证号码：
        <div class="layui-inline">
            <input class="layui-input" name="cardId" id="selectByCardId" autocomplete="off">
        </div>
    </div>
    <div style="margin: 5px 0px;">
        <form class="layui-inline">
            部门：
            <div class="layui-inline">
                <select class="layui-input" name="deptId" id="selectByDeptId" autocomplete="off">
                    <option value="">请选择&nbsp;&nbsp;&nbsp;</option>
                </select>
            </div>
            &nbsp;&nbsp;职位：
            <div class="layui-inline">
                <select class="layui-input" name="jobId" id="selectByJobId" autocomplete="off">
                    <option value="">请选择&nbsp;&nbsp;&nbsp;</option>
                </select>
            </div>
        </form>
        &nbsp;&nbsp;性别：
        <div class="layui-inline">
            <select class="layui-input" name="sex" id="selectBySex" autocomplete="off">
                <option value="">请选择&nbsp;&nbsp;&nbsp;</option>
                <option value="男">男</option>
                <option value="女">女</option>
            </select>
        </div>
    </div>
    <button class="layui-btn" data-type="reload">搜索</button>
</div>

<table id="tab" lay-filter="test"></table>

<script type="text/html" id="barDemo">
    <shiro:hasPermission name="emp:update">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="emp:delete">
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </shiro:hasPermission>
</script>
<script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.js"></script>
<script src="/layui/layui.js"></script>
<script>
    layui.use(['table', 'laypage', 'form'], function(){
        var table = layui.table,
            laypage = layui.laypage,
            form = layui.form

        table.render({
            elem: '#tab'
            ,id: 'testReload'
            ,url: '/emp/selectAll' //数据接口
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
                ,{field: 'name', title: '姓名'}
                ,{field: 'sex', title: '性别'}
                ,{field: 'phone', title: '手机号码'}
                ,{field: 'email', title: '邮箱'}
                ,{field: 'jobName', title: '职位'}
                ,{field: 'deptName', title: '部门'}
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
                    $.post("/emp/delete", "id="+data.id, function (res) {
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
                location.href = '/addEmp.jsp?empId='+data.id;
            }
        });

        var $ = layui.$, active = {
            reload: function(){
                var selectByDeptId = $('#selectByDeptId');
                var selectByJobId = $('#selectByJobId');
                var selectByName = $('#selectByName');
                var selectByCardId = $('#selectByCardId');
                var selectByPhone = $('#selectByPhone');
                var selectBySex = $('#selectBySex');
                //执行重载
                table.reload('testReload', {
                    url: '/emp/selectByParam'
                    ,page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                        key: {
                            deptId: selectByDeptId.val()
                            ,jobId: selectByJobId.val()
                            ,name: selectByName.val()
                            ,cardId: selectByCardId.val()
                            ,phone: selectByPhone.val()
                            ,sex: selectBySex.val()
                        }
                    }
                });
            }
        };

        $('.demoTable .layui-btn[data-type]').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        $(function () {
            $.post("/dept/selectAll", "limit=10", function (res) {
                for(var i = 0; i < res.data.length; i++){
                    var post = res.data[i];
                    var html = "<option value="+post.id+">"+post.name+"&nbsp;&nbsp;&nbsp;</option>";
                    $("#selectByDeptId").append(html);
                }
                form.render('select');
            });
            $.post("/job/selectAll", "limit=10", function (res) {
                for(var i = 0; i < res.data.length; i++){
                    var post = res.data[i];
                    var html = "<option value="+post.id+">"+post.name+"&nbsp;&nbsp;&nbsp;</option>";
                    $("#selectByJobId").append(html);
                }
                form.render('select');
            });
        });
    });
</script>
</body>
</html>
