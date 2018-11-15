<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Profil</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Ressources/css/admin.css"/>
</head>
<body>
<nav class="sidebar">
  <ul>
    <li><a class="links" href="${pageContext.request.contextPath}/profil">Profil</a></li>
   <li> <a class="links" href="${pageContext.request.contextPath}/view">Applications</a></li>
   <li> <a class="links" href="${pageContext.request.contextPath}/appregister">Add an app</a></li>
    <c:if test="${admin eq 1}">
      <li><a class="links" href="${pageContext.request.contextPath}/admin">Admin</a></li>
    </c:if>
    <li>  <a class="links" href="${pageContext.request.contextPath}/logout">Logout</a></li>
</ul>
    </nav>
<div class="container">
    <c:if test="${user ne null}">
        <h2>Your informations </h2>
        <p>Firstname : ${user.getFirstName()}</p>
        <p>LastName : ${user.getLastName()}</p>
        <p>Email : ${user.getEmail()}</p>
    </c:if>
</div>
</body>
</html>
