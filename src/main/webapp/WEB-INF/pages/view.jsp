<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <title>Applications</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Ressources/css/app.css" />
</head>
<body>
<nav class="sidebar">
    
  <ul>
  <li> <a class="links" href="${pageContext.request.contextPath}/profil">Profil</a></li>
    <li><a class="links" href="${pageContext.request.contextPath}/view">Applications</a></li>
    <li><a class="links" href="${pageContext.request.contextPath}/appregister">Add an app</a></li>
    <li><a class="links" href="${pageContext.request.contextPath}/admin">Admin</a></li>
    <li><a class="links" href="${pageContext.request.contextPath}/logout">Logout</a></li>
  </ul>
</nav>
<div class='container'>
    <h2>Your applications</h2>

    <ul>
        <c:forEach items="${applist}" var="application">
            <p>${application.getName()}</p> 
        </c:forEach>
    </ul>
</div>
</div>
</body>
</html>
