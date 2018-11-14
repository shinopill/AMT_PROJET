<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <title>Applications</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Ressources/css/styles.css"/>
</head>
<body>
<nav>
    <a class="links" href="${pageContext.request.contextPath}/profil">Profil</a>
    <a class="links" href="${pageContext.request.contextPath}/view">Applications</a>
    <c:if test="${admin eq 0}">
        <a class="links" href="${pageContext.request.contextPath}/appregister">Add an app</a>
    </c:if>
    <c:if test="${admin eq 1}">
        <a class="links" href="${pageContext.request.contextPath}/admin">Admin</a>
    </c:if>

    <a class="links" href="${pageContext.request.contextPath}/logout">Logout</a>
</nav>
<div class='container'>
    <c:if test="${admin eq 0}">
        <h2>Your applications:</h2>
    </c:if>
    <c:if test="${admin eq 1}">
        <h2>Users' applications:</h2>
    </c:if>

    <ul>
        <table>
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
                            <a class="links" href="${pageContext.request.contextPath}/edit?name=${application.getName()}">Edit</a>
                        </th>
                        <th>
                            <a class="links" href="${pageContext.request.contextPath}/view?delete=${application.getName()}" >Delete</a>
                        </th>
                    </c:if>
                </tr>
            </c:forEach>

        </table>
    </ul>
    <c:if test="${pageApp ne 0}">
        <a class="links" href="${pageContext.request.contextPath}/view?do=previous" >Previous</a>
    </c:if>
    <c:if test="${appToSee ne 0}">
        <a class="links" href="${pageContext.request.contextPath}/view?do=next">Next</a>
    </c:if>
</div>
</div>
</body>
</html>
