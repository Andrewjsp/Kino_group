 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="Header.jsp"/>
<fmt:setLocale value="${local}"/>
<fmt:setBundle basename="locale" var="bundle"/>
<%--
  Created by IntelliJ IDEA.
  User: Андрей
  Date: 31.10.2018
  Time: 13:06
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

    <c:forEach var="Goods" items="${Good}">
        <tr>
            <form action="/AddProductInBasket" method="post">
                <input type="hidden" name="productId" value="${Goods.productId}"/>
                <td><a href="/ShowMusic?albumId=${Goods.productId}">${Goods.productName}</a></td>
                <input type="hidden" name="productName" value="${Goods.productName}"/>
                <td><input type="hidden" name="productPrice" value="${Goods.productPrice}"/>${Goods.productPrice}</td>
                <td>${Goods.description}</td>
                <td>
                    <button type="submit"><fmt:message key="addInBasket" bundle="${bundle}"/></button>
                </td>
            </form>
        </tr>
    </c:forEach>
</table>
</body>
</html>
