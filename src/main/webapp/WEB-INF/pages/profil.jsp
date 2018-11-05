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
<html>
<head>
    <title>Profil</title>
    <link rel="stylesheet" href="/WEB-INF/css/styles.css">
</head>
<body>
    <a href="${pageContext.request.contextPath}/profil">Profil</a>
    <a href="${pageContext.request.contextPath}/view">Applications</a>
    <a href="${pageContext.request.contextPath}/logout">Logout</a>
    <h1>Profil:</h1>
    <p>username</p>
    <p>email</p>
    <p>adress</p>
</body>
</html>
