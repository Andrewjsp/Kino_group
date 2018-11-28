 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${local}"/>
<fmt:setBundle basename="locale" var="bundle"/>
<html>
<head>
    <title><fmt:message key="shop" bundle="${bundle}"/> </title>
</head>
<body>
<div align="center">
    <c:if test="${requestScope.message ne null}"> ${requestScope.message}</c:if>
</div>
Id<fmt:message key="user" bundle="${bundle}"/> ${editUser.userId}
<fmt:message key="login" bundle="${bundle}"/> ${editUser.userLogin}

<c:if test="${editUser.userRole eq 0}">
    <form action="/view/changeRole" method="post">
        <input type="checkbox" name="checkboxWithAccess" value="allow"/> <fmt:message key="makeAdmin" bundle="${bundle}"/> </br>
        <input type="hidden" name="userId" value="${editUser.userId}"/>
        <input type="hidden" name="userRole" value="${editUser.userRole}"/>
        <input type="submit" value="<fmt:message key="send" bundle="${bundle}"/>"/>
    </form>
</c:if>


<c:if test="${editUser.userRole eq 1}">
    <form action="/view/changeRole" method="post">
        <input type="checkbox" name="checkboxWithAccess" value="allow"/><fmt:message key="makeUser" bundle="${bundle}"/> </br>
        <input type="hidden" name="userId" value="${editUser.userId}"/>
        <input type="hidden" name="userRole" value="${editUser.userRole}"/>
        <input type="submit" value="<fmt:message key="save" bundle="${bundle}"/>"/>
    </form>
</c:if>
<div align="right">
    <a href="/view/Exit"> <fmt:message key="signOut" bundle="${bundle}"/></a>
</div>
<a href="/view/Welcome.jsp"> <fmt:message key="goMainPage" bundle="${bundle}"/> </a>
</body>
</html>
