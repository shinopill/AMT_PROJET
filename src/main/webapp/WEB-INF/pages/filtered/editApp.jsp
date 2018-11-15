<%--
  Created by IntelliJ IDEA.
  User: Romain
  Date: 12.11.2018
  Time: 18:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Application</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Ressources/css/styles.css"/>

</head>
<body>
<nav>
    <a class="links" href="${pageContext.request.contextPath}/filtered/profil">Profil</a>
    <a class="links" href="${pageContext.request.contextPath}/filtered/view">Applications</a>
    <a class="links" href="${pageContext.request.contextPath}/filtered/appregister">Add an app</a>
    <a class="links" href="${pageContext.request.contextPath}/logout">Logout</a>
</nav>

<div class="form_div">
    <h2>Edit App</h2>

    <form class="registerForm" action="${pageContext.request.contextPath}/filtered/edit?oldname=${name}" method="post">
        <input type="text" name="appName" placeholder="App Name (max 20 chars)" value="${name}"/>
        <textarea class="text_area" name="description" placeholder="App description">${description}</textarea></br></br>
        <input type="submit" name="Edit"/>
        <input type="button" value="Cancel" onclick="window.location.href='${pageContext.request.contextPath}/filtered/view'"/>
        <c:if test="${erreur ne null}">
            <p>${erreur}</p>
        </c:if>
    </form>

</div>

</body>
</html>
