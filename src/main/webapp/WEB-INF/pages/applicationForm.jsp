<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Application Registration</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Ressources/css/styles.css" />
</head>

<body>
<nav>
    <a class="links" href="${pageContext.request.contextPath}/profil">Profil</a>
    <a class="links" href="${pageContext.request.contextPath}/view">Applications</a>
    <a class="links" href="${pageContext.request.contextPath}/appregister">Add an app</a>
    <a class="links" href="${pageContext.request.contextPath}/admin">Admin</a>
    <a class="links" href="${pageContext.request.contextPath}/logout">Logout</a>
</nav>

<h2>App Registration</h2>
Please provide the following information to register your app :

<form class="registerForm" action="${pageContext.request.contextPath}/appregister" method="post">
    <input type="text" name="appName" placeholder="App Name"/>
    <textarea  name="descripton" placeholder="App description"></textarea>
    <button type="submit" name="register">Register</button>
    <c:if test="${erreur ne null}">
        <p>${erreur}</p>
    </c:if>
</form>
</body>
</html>

