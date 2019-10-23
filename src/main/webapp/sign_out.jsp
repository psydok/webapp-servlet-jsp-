<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="UTF-8"/>
        <title>sign out</title>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/signout" method="post" name="out">
                    <p>Sign out</p>
                    Login: <input type="text" name="login"/>
                    Email: <input type="text" name="email"/>
                    Password: <input type="password" name="pass"/>
                    <input type="submit" value="Sign out">
        </form>
    </body>
</html>