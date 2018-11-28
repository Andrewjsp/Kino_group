 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${local}"/>
<fmt:setBundle basename="locale" var="bundle"/>
<html>
<head>
    <title><fmt:message key="shop" bundle="${bundle}"/></title>
</head>
<body>
<table border="1">
    <tr>
        <td><b><fmt:message key="loginUser" bundle="${bundle}"/></b></td>
        <td><b><fmt:message key="accessLevel" bundle="${bundle}"/></b></td>
        <td><b><fmt:message key="delete" bundle="${bundle}"/> </b></td>
        <td><b><fmt:message key="edit" bundle="${bundle}"/></b></td>
    </tr>
    <c:forEach var="user" items="${showAllUsers}">
        <c:if test="${user.userId ne sessionScope.User.userId}">
<form action="/view/DeleteUser" method="post">
    <tr>
        <td>${user.userLogin}</td>
        <c:if test="${user.userRole eq 1}">
            <td><fmt:message key="admin" bundle="${bundle}"/></td>
        </c:if>
        <c:if test="${user.userRole eq 0}">
            <td> <fmt:message key="user" bundle="${bundle}"/></td>
        </c:if>
      <input type="hidden" name="userId" value="${user.userId}"/>
        <td><button type="submit"><fmt:message key="delete" bundle="${bundle}"/></button></td>

</form>
    <form action="/view/changeUserRole" method="post">
        <input type="hidden" name="userId" value="${user.userId}"/>
        <input type="hidden" name="userLogin" value="${user.userLogin}"/>
        <input type="hidden" name="userRole" value="${user.userRole}"/>
        <td><button type="submit"> <fmt:message key="edit" bundle="${bundle}"/></button></td>
    </form>
    </tr>
        </c:if>
    </c:forEach>

</table>
<div align="left">
    <a href="/view/Welcome.jsp"><fmt:message key="goMainPage" bundle="${bundle}"/> </a>
</div>
<div align="right">
    <a href="/view/Exit"> <fmt:message key="signOut" bundle="${bundle}"/></a>
</div>
</body>
</html>