<html>
<head>
    <title>Index</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>

<h2>Hello Customer!</h2>
Login:
<form id="loginForm" action="${pageContext.request.contextPath}/view" method="post">

    <input type="text" name="Email" placeholder="Email"/><br>
    <input type="password" name="Password" placeholder="Password"/><br>
    <input type="submit" value="Login"/>
</form>
</br></br>
<a href="${pageContext.request.contextPath}/register">Create an account?</a>
</body>
</html>
