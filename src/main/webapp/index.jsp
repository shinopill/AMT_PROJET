<br>
  <body>
    <h2>Hello Customer!</h2>
  Login:
  <form action="${pageContext.request.contextPath}/view" method="post">
      Username:
      <input type="text" name="username"/><br>
      Password:
      <input type="password" name="password"/><br>
      <input type="submit" value="Login"/>
  </form>
</br></br>
     <a href="${pageContext.request.contextPath}/register">Create an account?</a>

</body>
</html>
