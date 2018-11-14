<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <c:if test="${admin eq 0}">
        <a class="links" href="${pageContext.request.contextPath}/appregister">Add an app</a>
    </c:if>    <a class="links" href="${pageContext.request.contextPath}/logout">Logout</a>
</nav>

<div class="form_div">
    <form action="${pageContext.request.contextPath}/password" method="post">
        <h2>Enter the new password</h2>
        <input type="password" name ="oldPass" placeholder="Old password"/>
        <input type="password" name  ="newPass" placeholder="New password"/>
        <input type="password" name = "newPassConf" placeholder="Retype new password"/></br>
        <input type="submit" value="Change"/>
        <input type="button" value="Cancel" onclick="window.location.href='${pageContext.request.contextPath}/profil'"/>
        <c:if test="${erreur ne null}">
            <p>${erreur}</p>
        </c:if>
    </form>
</div>

</body>
</html>
