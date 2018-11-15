<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Application Registration</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Ressources/css/applicationForm.css"/>
</head>

<body>
<nav class="sidebar">
  <ul>
    <li><a class="links" href="${pageContext.request.contextPath}/profil">Profil</a></li>
    <li><a class="links" href="${pageContext.request.contextPath}/view">Applications</a></li>
    <li><a class="links" href="${pageContext.request.contextPath}/appregister">Add an app</a></li>
    <c:if test="${admin eq 1}">
      <li><a class="links" href="${pageContext.request.contextPath}/admin">Admin</a></li>
    </c:if>
    <li><a class="links" href="${pageContext.request.contextPath}/logout">Logout</a>
  </ul>
    </nav>
<div class="container">
    <h2>App Registration</h2>
    Please provide the following information to register your app :

    <form class="registerForm" action="${pageContext.request.contextPath}/appregister" method="post">
        <input type="text" name="appName" placeholder="App Name"/>
        <textarea name="descripton" placeholder="App description"></textarea></br></br>
        <input type="submit" name="Add"/>
        <input type="button" value="Cancel" onclick="window.location.href='${pageContext.request.contextPath}/view'"/>
        <c:if test="${erreur ne null}">
            <p class="error">${erreur}</p>
        </c:if>
    </form>

</div>
</body>
</html>



