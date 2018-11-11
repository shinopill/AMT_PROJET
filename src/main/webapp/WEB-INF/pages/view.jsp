<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <title>Applications</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Ressources/css/styles.css" />
</head>
<body>
<nav>
    <a class="links" href="${pageContext.request.contextPath}/profil">Profil</a>
    <a class="links" href="${pageContext.request.contextPath}/view">Applications</a>
    <c:if test="${admin eq 1}">
        <a class="links" href="${pageContext.request.contextPath}/admin">Admin</a>
    </c:if>
    <a class="links" href="${pageContext.request.contextPath}/logout">Logout</a>
</nav>
<div class='container'>
    <h1>Your applications:</h1>

    <ul>
        <c:forEach items="${applist}" var="application">
            <p>${application.getName()}</p>
        </c:forEach>
    </ul>
</div>
    <div class='app'>
        <p>name</p>
        <p>description</p>
        <p>api key</p>
        <p>api secret</p>
    </div>
</div>
</body>
</html>
