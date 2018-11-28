 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="Header.jsp"%>
<fmt:setLocale value="${local}"/>
<fmt:setBundle basename="locale" var="bundle"/>
<html>
<head>

    <title><fmt:message key="shop" bundle="${bundle}"/></title>
</head>
<body>
<div align="center">
    <c:if test="${requestScope.message ne null}"> ${requestScope.message}</c:if>
</div>
<div align="center">
<form action="/view/Registration" method="post">
<fmt:message key="enterName" bundle="${bundle}"/> <input type="text" name="name" /></br>
    <fmt:message key="enterLogin" bundle="${bundle}"/> <input type="text" name="login" /></br>
    <fmt:message key="enterPassword" bundle="${bundle}"/><input type="password" name="password" /></br>
    <input type="submit"value="<fmt:message key="send" bundle="${bundle}"/>"/>
</form>
</div>
</body>
</html>
