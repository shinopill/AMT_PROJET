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
                    <th>${applist.size()}</th>
                    <c:if test="${admin eq 0}">
                        <th>
                            <form action="${pageContext.request.contextPath}/edit" method="post">
                                <input type="submit" name="edit" value="Edit"/>
                            </form>
                        </th>
                        <th>
                            <form action="${pageContext.request.contextPath}/deleteapp" method="post">
                                <input type="submit" name="delete" value="Delete"/>
                            </form>
                        </th>
                    </c:if>
                </tr>
            </c:forEach>

        </table>
    </ul>
    <c:if test="${pageApp ne 0}">
        <a class="links" href="${pageContext.request.contextPath}/view" onclick=<%session.setAttribute("pageApp",(int)session.getAttribute("pageApp")-1);%>>Previous</a>
    </c:if>
    <c:if test="${applist.size() eq 10}">
        <a class="links" href="${pageContext.request.contextPath}/view" onclick=<%session.setAttribute("pageApp",(int)session.getAttribute("pageApp")+1);%>>Next</a>
    </c:if>
</div>
</div>
</body>
</html>
