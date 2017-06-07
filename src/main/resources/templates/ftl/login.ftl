<!DOCTYPE html>
<head>
    <title>登录页</title>
</head>
<body>
    <form action="http://127.0.0.1:8080/login" method="post">
        <div><label>用户名 : <input type="text" name="username"/> </label></div>
        <div><label>密&nbsp;&nbsp;码: <input type="password" name="password"/> </label></div>
        <div><input type="submit" value="Sign In"/></div>
        <input type="checkbox" name="remember-me" value="true" th:checked="checked"/><p>Remember me</p>
    </form>
</body>
</html>