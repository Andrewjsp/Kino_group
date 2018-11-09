 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${local}"/>
<fmt:setBundle basename="locale" var="bundle"/>

<html>
<head>
    <title><fmt:message key="shop" bundle="${bundle}"/></title>
</head>
<body>
<c:if test="${sessionScope.User eq null}"> </c:if>
<fmt:message key="login" bundle="${bundle}"/> :  ${sessionScope.User.userLogin} </br>
<fmt:message key="name" bundle="${bundle}"/>:   ${sessionScope.User.userName} </br>
<fmt:message key="balance" bundle="${bundle}"/> : ${sessionScope.User.balance} </br>

<div align="right">
    <a href="/Exit"> <fmt:message key="signOut" bundle="${bundle}"/></a>
</div>

<div>
    <a href="/view/Welcome.jsp"><fmt:message key="goMainPage" bundle="${bundle}"/></a>
</div>
</body>
</html>