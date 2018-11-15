<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
   <head>
      <title>Profil</title>
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Ressources/css/admin.css"/>
   </head>
   <body>

      <nav class="sidebar">
         <a class="links" href="${pageContext.request.contextPath}/filtered/profil">Profil</a>
         <a class="links" href="${pageContext.request.contextPath}/filtered/view">Applications</a>
         <c:if test="${admin eq 0}">
            <a class="links" href="${pageContext.request.contextPath}/filtered/appregister">Add an app</a>
         </c:if>
         <c:if test="${admin eq 1}">
            <a class="links" href="${pageContext.request.contextPath}/filtered/admin">Admin</a>

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

         <form action="${pageContext.request.contextPath}/filtered/password" method="get">
            <input type="submit" value="Change Password"/>
         </form>

      </c:if>
   </div>
</body>
</html>
