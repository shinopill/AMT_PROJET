<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Profil</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Ressources/css/styles.css" />
</head>
<body>
<nav>
    <a class="links" href="${pageContext.request.contextPath}/profil">Profil</a>
    <a class="links" href="${pageContext.request.contextPath}/view">Applications</a>
    <a class="links" href="${pageContext.request.contextPath}/logout">Logout</a>
</nav>
<div class="container">
    <c:if test="${user ne null}">
        <p>Your informations </p>
        <p>Firstname : ${user.getFistName()}</p>
        <p>LastName : ${user.getLastName()}</p>
        <p>Email : ${user.getEmail()}</p>
</c:if>
</div>
</body>
</html>
