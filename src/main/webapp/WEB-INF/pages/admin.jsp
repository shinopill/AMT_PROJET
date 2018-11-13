<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Admin</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Ressources/css/styles.css" />
</head>
<body>
<nav>
    <a class="links" href="${pageContext.request.contextPath}/profil">Profil</a>
    <a class="links" href="${pageContext.request.contextPath}/view">Applications</a>
    <a class="links" href="${pageContext.request.contextPath}/logout">Logout</a>
</nav>


<div class="container">
    <h2>Users:</h2>
    <ul>
        <c:forEach items="${users}" var="user">
            <li>${user.getId()}</li>
            <li>${user.getIsDisabled()}</li>
            <form action="${pageContext.request.contextPath}/reset" method="post">
                <input type="submit" value="Reset Password"/>
            </form>
            <form action="${pageContext.request.contextPath}/admin" method="post">
                <input type="submit" value="Enable/Disable"/>
            </form>
        </c:forEach>
    </ul>
</div>
</body>
</html>