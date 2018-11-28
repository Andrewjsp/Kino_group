 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="Header.jsp"%>
<fmt:setLocale value="${local}"/>
<fmt:setBundle basename="locale" var="bundle"/>
<html>
<head>
    <title><fmt:message key="autorisation" bundle="${bundle}"/></title>
</head>
<body>
<div align="center">
    <c:if test="${requestScope.message ne null}"> ${requestScope.message}</c:if>
</div>
<form  action="/view/Autorisation" method="post">
<fmt:message key="enterLogin"  bundle="${bundle}"/><input type="text" name="login" maxlength="15"/>
    <br/><br/>
<fmt:message key="enterPassword" bundle="${bundle}"/> <input type="password" name="password" maxlength="15"/>
     <br/><br/>
    <input type="submit" value="<fmt:message key="send" bundle="${bundle}"/>"/>
</form>
</body>
</html>