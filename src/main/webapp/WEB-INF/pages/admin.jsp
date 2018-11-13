<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Ressources/css/styles.css"/>
</head>
<body>
<nav>
    <a class="links" href="${pageContext.request.contextPath}/profil">Profil</a>
    <a class="links" href="${pageContext.request.contextPath}/view">Applications</a>
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
                        <form action="${pageContext.request.contextPath}/reset" method="post">
                            <input type="submit" value="Reset Password"/>
                        </form>
                    </th>
                    <th>
                        <form action="${pageContext.request.contextPath}/admin" method="post">
                            <input type="submit" value="Enable/Disable"/>
                        </form>
                    </th>
                </tr>
            </c:forEach>

        </table>
    </ul>
    <c:if test="${pageApp ne 0}">
        <a class="links" href="${pageContext.request.contextPath}/admin" onclick=<%session.setAttribute("pageUser",(int)session.getAttribute("pageUser")-1);%>>Previous</a>
    </c:if>
    <c:if test="${usersArray.size() eq 10}">
        <a class="links" href="${pageContext.request.contextPath}/admin" onclick=<%session.setAttribute("pageUser",(int)session.getAttribute("pageUser")+1);%>>Next</a>
    </c:if>


</div>
</body>
</html>