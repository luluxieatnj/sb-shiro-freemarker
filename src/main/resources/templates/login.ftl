<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登陆页面</title>
</head>
<body>
<div>
    <form id="form" action="http://localhost:8080/user/login" method="post">
        <input type="text" name="username" placeholder="username">
        <input type="password" name="password" placeholder="password">

        <input type="submit" id="login" value="登陆"/>
        <div class="error"><span></span></div>

    </form>
</div>
</body>
</html>