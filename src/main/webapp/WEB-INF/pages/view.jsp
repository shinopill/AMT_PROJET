<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <title>Applications</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Ressources/css/styles.css"/>
</head>
<body>
<nav>
    <a class="links" href="${pageContext.request.contextPath}/profil">Profil</a>
    <c:if test="${admin eq 0}">
        <a class="links" href="${pageContext.request.contextPath}/appregister">Add an app</a>
    </c:if>
    <c:if test="${admin eq 1}">
        <a class="links" href="${pageContext.request.contextPath}/admin">Admin</a>
    </c:if>

    <a class="links" href="${pageContext.request.contextPath}/logout">Logout</a>
</nav>
<div class='container'>
    <c:if test="${admin eq 0}">
        <h2>Your applications:</h2>
    </c:if>
    <c:if test="${admin eq 1}">
        <h2>Users' applications:</h2>
    </c:if>

    <ul>
        <c:forEach items="${applist}" var="application">
            <p>${application.getName()}</p>
            <form action="${pageContext.request.contextPath}/edit" method="post">
                <input type="submit" value="Edit"/>
            </form>
            <form action="${pageContext.request.contextPath}/deleteapp" method="post">
                <input type="submit" value="Delete"/>
            </form>
        </c:forEach>
    </ul>
</div>
</div>
</body>
</html>
