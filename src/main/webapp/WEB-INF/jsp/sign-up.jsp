<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: zv3re
  Date: 27.04.2023
  Time: 23:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Sign up</title>
</head>
<body>
  <h1>Sign up</h1>
  <%--@elvariable id="profile" type="com.vsu.app.entity.Profile"--%>
  <form:form action="/sign-up" method="post" modelAttribute="profile">
    Username<br/>
    <form:input path="username"/><br/>
    Password<br/>
    <form:password path="password"/><br/>
    Name<br/>
    <form:input path="fio"/><br/>
    E-mail<br/>
    <form:input path="email"/><br/>
    Phone number<br/>
    <form:input path="phone"/><br/>
    <input type="submit">
  </form:form>
</body>
</html>
