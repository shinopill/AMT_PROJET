<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>View</title>
    <link rel="stylesheet" href="../css/styles.css">
</head>
<body>

<%
    String email = null;
    String sessionID = null;

    Cookie[] cookies = request.getCookies();
    if(cookies != null){
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("email")) email = cookie.getValue();
            if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
        }
    }
    System.out.println("email = " + email + " and JSESSIONID = " + sessionID);
%>

<h2>View</h2>
<p>You logged successfully!</p>
</br></br>
<a href="${pageContext.request.contextPath}/logout">Logout</a>
</body>
</html>
