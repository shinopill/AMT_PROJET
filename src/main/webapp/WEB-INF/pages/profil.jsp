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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Ressources/css/styles.css" />
</head>
<body>
<nav>
    <a class="links" href="${pageContext.request.contextPath}/profil">Profil</a>
    <a class="links" href="${pageContext.request.contextPath}/view">Applications</a>
    <a class="links" href="${pageContext.request.contextPath}/logout">Logout</a>
</nav>
<div class="container">
    <h1>Profil:</h1>
    <p>username</p>
    <p>email</p>
    <p>adress</p>
</div>
</body>
</html>
