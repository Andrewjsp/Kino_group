 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${local}"/>
<fmt:setBundle basename="locale" var="bundle"/>
<jsp:include page="Header.jsp"/>
<%--
  Created by IntelliJ IDEA.
  User: Андрей
  Date: 31.10.2018
  Time: 13:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><fmt:message key="shop" bundle="${bundle}"/></title>
</head>
<body>
<table border="1">
    <tr>
        <td><b><fmt:message key="nameProduct" bundle="${bundle}"/></b></td>
        <td><b><fmt:message key="price" bundle="${bundle}"/> </b></td>
        <td><b><fmt:message key="description" bundle="${bundle}"/></b></td>
        <td><b><fmt:message key="addInBasket" bundle="${bundle}"/></b></td>
    </tr>
    <c:forEach var="Music" items="${music}">
        <tr>
            <form action="/AddProductInBasket" method="post">
                <td>${Music.productName}</td>
                <td>${Music.productPrice}</td>
                <td>${Music.description}</td>
                <input type="hidden" name="productId" value="${Music.productId}"/>
                <input type="hidden" name="productName" value="${Music.productName}"/>
                <input type="hidden" name="productPrice" value="${Music.productPrice}">
                <td>
                    <button type="submit"><fmt:message key="addInBasket" bundle="${bundle}"/></button>
                </td>
            </form>
        </tr>
    </c:forEach>
</table>
</body>
</html>
