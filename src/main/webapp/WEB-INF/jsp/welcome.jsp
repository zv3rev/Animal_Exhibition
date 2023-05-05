<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>AnimalExhibition</title>
</head>
<body>
<c:if test="${!isSignedIn}">
    <a href="${pageContext.request.contextPath}/sign-up">Sign up</a>
    <a href="${pageContext.request.contextPath}/sign-in">Sign in</a>
</c:if>
<c:if test="${isSignedIn}">
    <a href="${pageContext.request.contextPath}/user/profile-page">Profile</a>
</c:if>
<a href="${pageContext.request.contextPath}/swagger-ui/#">Swagger</a>
    <h1>
        Welcome to Animal Exhibition App
        <c:if test="${isSignedIn}">, ${profile.getUsername()}</c:if>!        !
    </h1>
    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
</body>
</html>
