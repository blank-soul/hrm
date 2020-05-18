<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
          crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="https://cdn.bootcss.com/font-awesome/5.13.0/css/all.css" />
    <title>Hello, world!</title>

    <style type="text/css">
        body {
            background-image: url(images/body-bg.png);
            background-size: cover;
            background-attachment: fixed;
        }

        .card {
            background-color: rgba(255, 255, 255, 0.5);
            width: 400px;
            height: 400px;
        }
    </style>
</head>
<body scroll="no">
<div class="container">
    <div class="row" id="row">
        <div class="col d-flex justify-content-center align-items-center">
            <div class="card">
                <div class="row mt-3 pb-1 h-75">
                    <div class="col text-center">
                        <h4>人事管理系统</h4>
                    </div>
                </div>
                <div class="row no-gutters justify-content-center">
                    <form class="needs-validation" novalidate>
                        <div class="form-row" style="">
                            <div class="col">
                                <label><small>登录账号</small></label>
                                <input id="username" type="text" class="form-control" placeholder="登录账号" required />
                            </div>
                        </div>
                        <div class="form-row mt-4">
                            <div class="col">
                                <label><small>登录密码</small></label>
                                <input id="pwd" type="password" class="form-control" placeholder="登录密码" required />
                            </div>
                        </div>
                        <div class="form-row mt-4">
                            <div class="col">
                                <button id="submit" type="button" class="btn btn-primary w-100">登录</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.js"></script>
<script src="https://cdn.bootcss.com/twitter-bootstrap/4.4.1/js/bootstrap.bundle.js"></script>
<script type="text/javascript">
    $(function() {
        var nav_height = window.screen.availHeight;
        $(".row").css("height", nav_height + "px");
    });
</script>
<script type="text/javascript">
    $("#submit").click(function() {
        var username = $("#username").val();
        var pwd = $("#pwd").val();
        if(username == "" || username == null || username == undefined){
            alert("账号不可为空！");
        } else if(pwd == "" || pwd == null || pwd == undefined){
            alert("密码不可为空！");
        } else{
            $.post("loginResult", "username="+username+"&pwd="+pwd, function (res) {
                if(0 != res.length){
                    alert("登录成功！");
                    location.href='/loginInit?id=' + res[0].id;
                } else {
                    alert("登录失败！");
                }
            });
        }
    });
</script>
</body>
</html>


