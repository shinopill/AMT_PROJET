<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

   <head>
      <title>Applications</title>
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Ressources/css/admin.css"/>
   </head>
   <body>
      <nav class="sidebar">
         <ul>
            <li><a class="links" href="${pageContext.request.contextPath}/filtered/profil">Profil</a></li>
            <li><a class="links" href="${pageContext.request.contextPath}/filtered/view">Applications</a></li>
               <c:if test="${admin eq 0}">
               <li><a class="links" href="${pageContext.request.contextPath}/filtered/appregister">Add an app</a></li>
               </c:if>
               <c:if test="${admin eq 1}">
               <li><a class="links" href="${pageContext.request.contextPath}/filtered/admin">Admin</a></li>
               </c:if>

            <li><a class="links" href="${pageContext.request.contextPath}/logout">Logout</a></li>
         </ul>
      </nav>
      <div class='container'>
         <c:if test="${admin eq 0}">
            <h2>Your applications</h2>
         </c:if>
         <c:if test="${admin eq 1}">
            <h3>User's applications</h3>
         </c:if>

         <ul>
            <table>
               <tr>
                  <c:if test="${admin eq 1}">
                     <th>Owner</th>
                  </c:if>
                  <th>Name</th>
                  <th>Key API</th>
                  <th>Key Secret</th>
               </tr>
               <c:forEach items="${applist}" var="application">
                  <tr>
                     <c:if test="${admin eq 1}">
                        <th>${application.getAppOwner()}</th>
                        </c:if>
                     <th>${application.getName()}</th>
                     <th>${application.getKeyAPI()}</th>
                     <th>${application.getKeySecret()}</th>
                        <c:if test="${admin eq 0}">
                        <th>
                           <a class="table-links" href="${pageContext.request.contextPath}/filtered/edit?name=${application.getName()}">Edit</a>
                        </th>
                        <th>
                           <a class="table-links" href="${pageContext.request.contextPath}/filtered/view?delete=${application.getName()}" >Delete</a>
                        </th>
                     </c:if>
                  </tr>
               </c:forEach>

            </table>
         </ul>
         <c:if test="${pageApp ne 0}">
            <a class="links" href="${pageContext.request.contextPath}/filtered/view?do=previous" >Previous</a>
         </c:if>
         <c:if test="${appToSee ne 0}">
            <a class="links" href="${pageContext.request.contextPath}/filtered/view?do=next">Next</a>
         </c:if>
      </div>
   </div>
</body>
</html>