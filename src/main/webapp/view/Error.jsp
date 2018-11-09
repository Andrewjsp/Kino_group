 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${local}"/>
<fmt:setBundle basename="locale" var="bundle"/>
<%--
  Created by IntelliJ IDEA.
  User: Андрей
  Date: 29.10.2018
  Time: 20:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><fmt:message key="shop" bundle="${bundle}"/> </title>
</head>
<body>
<h1><div align="center">
   <fmt:message key="notRoot" bundle="${bundle}"/>
</div></h1>
<h4><a href="/view/Welcome.jsp"><fmt:message key="goMainPage" bundle="${bundle}"/></a></h4>
</body>
</html>
