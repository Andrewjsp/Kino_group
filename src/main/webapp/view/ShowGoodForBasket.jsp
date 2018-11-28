 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="locale" var="bundle"/>
<html>
<head>
    <title><fmt:message key="shop" bundle="${bundle}"/></title>

</head>
<body>
<div align="center">
    <c:if test="${message ne null}">${message}</c:if>
</div>

<c:if test="${sessionScope.totalSum eq 0}">
    <fmt:message key="yourBasketIsEmpty" bundle="${bundle}"/>
</c:if>
<c:if test="${sessionScope.totalSum ne 0}">
    <table border="1">
        <tr>
            <td><b><fmt:message key="nameProduct" bundle="${bundle}"/></b></td>
            <td><b><fmt:message key="price" bundle="${bundle}"/> </b></td>
            <td><b><fmt:message key="delete" bundle="${bundle}"/></b></td>
        </tr>
        <c:forEach var="basket" items="${goodsForBasket}">
          <tr>
            <form action="/view/DeleteGoodInBasket" method="post">
                <td>${basket.goodName}</td>
                <td>${basket.goodPrice}</td>
                <input type="hidden" name="orderId" value="${basket.orderId}"/>
                <input type="hidden" name="productPrice" value="${basket.goodPrice}"/>
                <td><button type="submit"><fmt:message key="delete" bundle="${bundle}"/></button></td>
            </form>
          </tr>
        </c:forEach>
    </table>

        <form action="/view/BuyGood" method="post">
            <button type="submit" name="totalSum" value="${totalSum}"><fmt:message key="buyProduct" bundle="${bundle}"/></button>
        </form>
        <fmt:message key="totalSumProduct" bundle="${bundle}"/>: ${sessionScope.totalSum}
</c:if>
<div align="right">
    <a href="/view/Exit"> <fmt:message key="signOut" bundle="${bundle}"/></a>
</div>
<a href="/view/Welcome.jsp"><fmt:message key="goMainPage" bundle="${bundle}"/></a>
</body>
</html>
