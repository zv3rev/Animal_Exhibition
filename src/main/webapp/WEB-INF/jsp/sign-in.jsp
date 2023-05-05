<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Sign in</title>
</head>
<body>
  <h1>Sign in</h1>
  <form method="post" action="${pageContext.request.contextPath}/sign-in">
    <input type="text" name="username" placeholder="Username">
    <input type="password" name="password" placeholder="Password">
    <input type="submit" value="Sign in">
    <c:if test="${isSignInFailed}">Wrong username or password</c:if>
  </form>
</body>
</html>
