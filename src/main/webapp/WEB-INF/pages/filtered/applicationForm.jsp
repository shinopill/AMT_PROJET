<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Application Registration</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Ressources/css/styles.css"/>
</head>

<body>
<nav>
    <a class="links" href="${pageContext.request.contextPath}/filtered/profil">Profil</a>
    <a class="links" href="${pageContext.request.contextPath}/filtered/view">Applications</a>
    <c:if test="${admin eq 0}">
        <a class="links" href="${pageContext.request.contextPath}/filtered/appregister">Add an app</a>
    </c:if>
    <c:if test="${admin eq 1}">
        <a class="links" href="${pageContext.request.contextPath}/filtered/admin">Admin</a>
    </c:if>
    <a class="links" href="${pageContext.request.contextPath}/logout">Logout</a>
</nav>

<div class="form_div">
    <h2>App Registration</h2>
    Please provide the following information to register your app :

    <form class="registerForm" action="${pageContext.request.contextPath}/filtered/appregister" method="post">
        <input type="text" name="appName" placeholder="App Name (max 20 chars)"/>
        <textarea class="text_area" name="descripton" placeholder="App description"></textarea></br></br>
        <input type="submit" name="Add"/>
        <input type="button" value="Cancel" onclick="window.location.href='${pageContext.request.contextPath}/filtered/view'"/>
        <c:if test="${erreur ne null}">
            <p>${erreur}</p>
        </c:if>
    </form>

</div>
</body>
</html>

