<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zv3re
  Date: 28.04.2023
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Profile page</title>
</head>
<body>
  <c:set var="profile"  value="${sessionScope.get(loggedUsername)}"/>
  <h1>Hello, ${profile.getFio()}</h1>
</body>
</html>
