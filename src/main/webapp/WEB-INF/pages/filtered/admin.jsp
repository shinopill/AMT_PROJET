<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Ressources/css/admin.css"/>
</head>
<body>
<nav>
    <a class="links" href="${pageContext.request.contextPath}/filtered/profil">Profil</a>
    <a class="links" href="${pageContext.request.contextPath}/filtered/view">Applications</a>
    <c:if test="${admin eq 1}">
        <a class="links" href="${pageContext.request.contextPath}/filtered/admin">Admin</a>
    </c:if>
    <a class="links" href="${pageContext.request.contextPath}/logout">Logout</a>
</nav>


<div class="container">
    <h2>Users:</h2>
    <ul>
        <table>
            <c:forEach items="${usersArray}" var="user">
                <tr>
                    <th>${user.getEmail()}</th>
                    <th>${user.getIsDisabled()}</th>
                    <th>
                        <a class="links" href="${pageContext.request.contextPath}/filtered/admin?reset=${user.getEmail()}">reset password</a>
                    </th>
                    <th>
                        <a class="links" href="${pageContext.request.contextPath}/filtered/admin?disable=${user.getEmail()}">disable/enable user</a>
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