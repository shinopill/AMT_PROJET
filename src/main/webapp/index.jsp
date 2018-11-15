
<html>
<head>
    <title>Index</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Ressources/css/login.css"/>
</head>
<body>
<div class="container">
    <h2>Hello Customer!</h2>
    Login:
    <form action="${pageContext.request.contextPath}/login" method="post">
        <input type="email" name="Email" placeholder="Email"/><br>
        <input type="password" name="Password" placeholder="Password"/><br>
        <Button>Login</Button>
        <c:if test="${erreur ne null}">
            <p class="error">${erreur}</p>
        </c:if>
    </form>
    <a href="${pageContext.request.contextPath}/register">Create an account?</a>
</div>
</body>
</html>