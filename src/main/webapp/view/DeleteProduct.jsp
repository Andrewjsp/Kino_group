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
<div align="center">
    <c:if test="${requestScope.message ne null}"> ${requestScope.message}</c:if>
</div>
    <table border="1">
        <tr>
            <td><b><fmt:message key="productId" bundle="${bundle}"/></b></td>
            <td><b><fmt:message key="nameProduct" bundle="${bundle}"/></b></td>
            <td><b><fmt:message key="price" bundle="${bundle}"/> </b></td>
            <td><b><fmt:message key="description" bundle="${bundle}"/></b></td>
            <td><b><fmt:message key="delete" bundle="${bundle}"/></b></td>
            <td><b><fmt:message key="albumId" bundle="${bundle}"/></b></td>

        </tr>
        <c:forEach var="good" items="${showAllGoods}">
        <form action="/deleteGood" method="post">
            <tr>
                <input type="hidden" name="productId" value="${good.productId}"/>
                <td> ${good.productId} </td>
                <td> ${good.productName} </td>
                <td>${good.productPrice}</td>
                <td>${good.description}</td>
                <td>
                    <button type="submit"><fmt:message key="delete" bundle="${bundle}"/></button>
                </td>
                <td>${good.parentId}</td>
        </form>
            </tr>
        </c:forEach>
    </table>
<div align="right">
    <a href="/view/Welcome.jsp"> <fmt:message key="goMainPage" bundle="${bundle}"/> </a>
</div>
</body>
</html>
