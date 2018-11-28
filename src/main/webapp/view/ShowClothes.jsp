 <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${local}"/>
<fmt:setBundle basename="locale" var="bundle"/>
<%@include file="Header.jsp" %>

<html>
<head>
    <title><fmt:message key="shop" bundle="${bundle}"/></title>
</head>
<body>

<div align="center">
    <c:if test="${requestScope.message ne null}"> ${requestScope.message}</c:if>
</div>
<table border="1">
    <tr>
        <td><b><fmt:message key="nameProduct" bundle="${bundle}"/></b></td>
        <td><b><fmt:message key="price" bundle="${bundle}"/> </b></td>
        <td><b><fmt:message key="description" bundle="${bundle}"/></b></td>
        <td><b><fmt:message key="color" bundle="${bundle}"/></b></td>
        <td><b><fmt:message key="size" bundle="${bundle}"/></b></td>
        <td><b><fmt:message key="addInBasket" bundle="${bundle}"/></b></td>
    </tr>
        <c:forEach var="Goods" items="${Good}">
        <form action="/view/AddProductInBasket" method="post">
            <tr>
                <td>${Goods.productName}</td>
                <input type="hidden" name="productId" value="${Goods.productId}"/>
                <input type="hidden" name="productName" value="${Goods.productName}"/>
                <td><input type="hidden" name="productPrice" value="${Goods.productPrice}"/>${Goods.productPrice}</td>
                <td>${Goods.description}</td>
                <td>${Goods.colorName}</td>
                <td>${Goods.sizeName}</td>
                <td>
                    <button type="submit"><fmt:message key="addInBasket" bundle="${bundle}"/></button>
                </td>
            </tr>
        </form>
        </c:forEach>
</table>
</body>
</html>

