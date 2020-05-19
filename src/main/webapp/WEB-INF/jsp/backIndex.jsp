<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>layout 后台大布局 - Layui</title>
    <link rel="stylesheet" href="layui/css/layui.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo"><h2>人事管理系统</h2></div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">

        </ul>
        <ul class="layui-nav layui-layout-right">
            <b>欢迎</b>
            <li class="layui-nav-item">
                <a href="javascript:;">
                    ${sessionScope.userMessage.username}
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
            <c:forEach items="${sessionScope.menus}" var="perent">
                <li class="layui-nav-item">
                    <a class="" href="javascript:;">${perent.name}</a>
                    <dl class="layui-nav-child">
                        <c:forEach items="${perent.child}" var="child">
                            <dd><a href="${child.location}" target="center_body">${child.name}</a></dd>
                        </c:forEach>
                    </dl>
                </li>
            </c:forEach>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <iframe name="center_body" width="100%" height="95%" frameborder="0"></iframe>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © layui.com - 底部固定区域
    </div>
</div>
<script src="layui/layui.js"></script>
<script>
    //JavaScript代码区域
    layui.use('element', function() {
        var element = layui.element;

    });
</script>
</body>
</html>

