<%--
  Created by IntelliJ IDEA.
  User: Romain
  Date: 12.11.2018
  Time: 18:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change Password</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Ressources/css/styles.css"/>

</head>
<body>
<nav>
    <a class="links" href="${pageContext.request.contextPath}/profil">Profil</a>
    <a class="links" href="${pageContext.request.contextPath}/view">Applications</a>
    <a class="links" href="${pageContext.request.contextPath}/appregister">Add an app</a>
    <a class="links" href="${pageContext.request.contextPath}/logout">Logout</a>
</nav>

<div class="form_div">
    <form action="${pageContext.request.contextPath}/profil" method="post">
        <h2>Enter the new password</h2>
        <input type="password" placeholder="Old password"/>
        <input type="password" placeholder="New password"/>
        <input type="password" placeholder="Retype new password"/></br>
        <input type="submit" value="Change"/>
        <input type="button" value="Cancel" onclick="window.location.href='${pageContext.request.contextPath}/profil'"/>
    </form>
</div>

</body>
</html>
