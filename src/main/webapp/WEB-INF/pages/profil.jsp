<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Profil</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Ressources/css/styles.css"/>
</head>
<body>
<nav>
    <a class="links" href="${pageContext.request.contextPath}/profil">Profil</a>
    <a class="links" href="${pageContext.request.contextPath}/view">Applications</a>
<<<<<<< HEAD
    <a class="links" href="${pageContext.request.contextPath}/appregister">Add an app</a>
    <c:if test="${admin == 1}">
=======
    <c:if test="${admin eq 1}">
>>>>>>> f67e2d35641169aab48566c3352c801bf5f33df5
        <a class="links" href="${pageContext.request.contextPath}/admin">Admin</a>
    </c:if>
    <a class="links" href="${pageContext.request.contextPath}/logout">Logout</a>
</nav>
<div class="container">
    <c:out value="${user}"/>
    <c:if test="${user ne null}">
        <p>Your informations </p>
        <p>Firstname : ${user.getFistName()}</p>
        <p>LastName : ${user.getLastName()}</p>
        <p>Email : ${user.getEmail()}</p>
    </c:if>
</div>
</body>
</html>
