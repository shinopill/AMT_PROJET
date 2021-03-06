<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Ressources/css/registration.css"/>
</head>
<body>
<div class="container">
    <h2>Registration</h2>
    Please provide the following information to register:

    <form action="${pageContext.request.contextPath}/register" method="post">
        <input type="text" name="firstname" placeholder="Firstname">
        <input type="text" name="lastname" placeholder="Lastname"/>
        <input type="email" name="email" placeholder="Email"/>
        <input type="password" name="password" placeholder="Password"/>
        <input type="password" name="confirmPassword" placeholder="Confirm password"></br>
        <input type="submit" value="Register"/>
        <input type="button" value="Cancel" onclick="window.location.href='${pageContext.request.contextPath}/'"/>
        <c:if test="${erreur ne null}">
            <p class="error">${erreur}</p>
        </c:if>
    </form>
</div>
</body>
</html>
