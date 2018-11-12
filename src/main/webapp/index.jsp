<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Index</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Ressources/css/styles.css"/>
</head>
<body>
<div class="form_div">
    <h2>Hello Customer!</h2>
    Login:
    <form action="${pageContext.request.contextPath}/login" method="post">
        <input type="text" name="Email" placeholder="Email"/><br>
        <input type="password" name="Password" placeholder="Password"/><br>
        <input type="submit" value="Login"/>
        <c:if test="${erreur ne null}">
            <p>${erreur}</p>
        </c:if>
    </form>
    <a href="${pageContext.request.contextPath}/register">Create an account?</a>
</div>
</body>
</html>
