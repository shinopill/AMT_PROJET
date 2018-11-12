<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Ressources/css/styles.css"/>
</head>
<body>
<div class="form_div">
    <h2>Registration</h2>
    Please provide the following information to register:

<<<<<<< HEAD
<form class="registerForm" action="${pageContext.request.contextPath}/register" method="post">
    <input type="text" name="firstname" placeholder="Firstname"/>
    <input type="text" name="lastname" placeholder="Lastname"/>
    <input type="text" name="email" placeholder="Email"/>
    <input type="password" name="password" placeholder="Password"/>
    <input type="password" name="confirmPassword" placeholder="Confirm password"/>
    <button type="submit" name="register">Register</button>
    <c:if test="${erreur ne null}">
        <p>${erreur}</p>
    </c:if>
</form>
=======
    <form action="${pageContext.request.contextPath}/register" method="post">
        <input type="text" name="firstname" placeholder="Firstname">
        <input type="text" name="lastname" placeholder="Lastname"/>
        <input type="text" name="email" placeholder="Email"/>
        <input type="password" name="password" placeholder="Password"/>
        <input type="password" name="confirmPassword" placeholder="Confirm password"></br>
        <input type="submit" value="Register"/>
        <c:if test="${erreur ne null}">
            <p>${erreur}</p>
        </c:if>
    </form>
</div>
>>>>>>> f67e2d35641169aab48566c3352c801bf5f33df5
</body>
</html>
