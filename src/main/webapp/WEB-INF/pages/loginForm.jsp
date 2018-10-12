<%--
  Created by IntelliJ IDEA.
  User: Florent
  Date: 05.10.2018
  Time: 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/view" method="post">
    Username:<br>
    <input type="text" name="username"/><br>
    Password:<br>
    <input type="password" name="password"/><br>
    <input type="submit" value="Login"/>
</form>
</form>

</body>
</html>
