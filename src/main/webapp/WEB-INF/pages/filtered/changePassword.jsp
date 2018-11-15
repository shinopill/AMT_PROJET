<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change Password</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Ressources/css/changePwd.css"/>

</head>
<body>
<nav class="sidebar">
    <ul>
        <li><a class="links" href="${pageContext.request.contextPath}/filtered/profil">Profil</a></li>
        <li><a class="links" href="${pageContext.request.contextPath}/filtered/view">Applications</a></li>
        <c:if test="${admin eq 0}">
            <li><a class="links" href="${pageContext.request.contextPath}/filtered/appregister">Add an app</a></li>
        </c:if>
        <c:if test="${admin eq 1}">
            <li><a class="links" href="${pageContext.request.contextPath}/filtered/admin">Admin</a></li>
        </c:if>
        <li><a class="links" href="${pageContext.request.contextPath}/logout">Logout</a></li>

    </ul>
</nav>

<div class="container">
    <form action="${pageContext.request.contextPath}/filtered/password" method="post">
        <c:if test="${reseted ne null}">
            <p class="error">${erreur}</p>
        </c:if>
        <h2>Enter the new password</h2>
        <input type="password" name="oldPass" placeholder="Old password"/>
        <input type="password" name="newPass" placeholder="New password"/>
        <input type="password" name="newPassConf" placeholder="Retype new password"/></br>
        <input type="submit" value="Change"/>
        <c:if test="${isBeingReseted ne 1}">
            <input type="button" value="Cancel"
                   onclick="window.location.href = '${pageContext.request.contextPath}/filtered/profil'"/>
        </c:if>
        <c:if test="${erreur ne null}">
            <p class="error">${erreur}</p>
        </c:if>
    </form>
</div>

</body>
</html>
