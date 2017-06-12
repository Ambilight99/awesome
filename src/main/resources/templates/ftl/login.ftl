<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>登陆</title>
    <link rel="shortcut icon" type="image/ico" href="${root}/static/custome/images/favicon.ico">
    <link rel="stylesheet" type="text/css" href="${root}/static/custome/css/login.css"/>
</head>
<body>
    <div id="login">
        <h1>Login</h1>
        <form action="${root}/login" method="post" >
            <input class="input" type="text" required="required" placeholder="用户名" id="username" name="username" value="${username?default('')}" />
            <input class="input" type="password" required="required" placeholder="密码" id="password" name="password" />
            <input type="checkbox" name="remember-me" value="true" />
            <span  class="remember-me">7天自动登录</span>
            <span class="auth-failure">${( (error?default(""))=="authFailure")?string("用户名或密码错误！","") }</span>
            <button class="but" type="submit">登录</button>
        </form>
    </div>
</body>
</html>