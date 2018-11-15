<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
   <head>
      <title>Admin</title>
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Ressources/css/admin.css"/>
   </head>
   <body>
      <nav class="sidebar">
         <ul>
            <li><a class="links" href="${pageContext.request.contextPath}/filtered/profil">Profil</a></li>
            <li><a class="links" href="${pageContext.request.contextPath}/filtered/view">Applications</a></li>
               <c:if test="${admin eq 1}">
               <li><a class="links" href="${pageContext.request.contextPath}/filtered/admin">Admin</a></li>
               </c:if>
            <li><a class="links" href="${pageContext.request.contextPath}/logout">Logout</a></li>
         </ul>
      </nav>
      <div class="container">
         <h2>Users</h2>
         <ul>
            <table>
               <tr>
                  <th>Mail</th>
                  <th>Is disable</th>
                  <th>Password</th>
                  <th>Enable</th>
               </tr>
               <c:forEach items="${usersArray}" var="user">
                  <tr>
                     <th>${user.getEmail()}</th>
                     <th>${user.getIsDisabled()}</th>
                     <th>
                        <a class="table-links" href="${pageContext.request.contextPath}/filtered/admin?reset=${user.getEmail()}">reset password</a>
                     </th>
                     <th>
                        <a class="table-links" href="${pageContext.request.contextPath}/filtered/admin?disable=${user.getEmail()}">disable/enable user</a>
                     </th>
                  </tr>
               </c:forEach>



            </table>
         </ul>
         <c:if test="${pageUser ne 0}">
            <a class="links" href="${pageContext.request.contextPath}/filtered/admin?do=previous" >Previous</a>
         </c:if>
         <c:if test="${userToSee ne 0}">
            <a class="links" href="${pageContext.request.contextPath}/filtered/admin?do=next">Next</a>
         </c:if>


      </div>
   </body>
</html>
