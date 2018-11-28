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
<div align="center">
    <h3><fmt:message key="formClothes" bundle="${bundle}"/></h3>
    <form action="/view/AddProduct" method="post" accept-charset="UTF-8">
        <fmt:message key="enterNameProduct" bundle="${bundle}"/> <input type="text" name="productName"/></br>
        <fmt:message key="enterDiscriptionProduct" bundle="${bundle}"/> <input type="text" name="description"/></br>
        <fmt:message key="enterPriceProduct" bundle="${bundle}"/> <input type="text" name="productPrice"/></br>
        <input type="hidden" name="key" value="clothes"/>
        </br>
        <fmt:message key="selectSize" bundle="${bundle}"/>
        <select name="sizeId">
            <c:forEach items="${size}" var="size">
                <option value="${size.sizeId}">${size.sizeName}</option>
            </c:forEach>

        </select>

        <fmt:message key="selectColor" bundle="${bundle}"/>
        <select name="colorId">
            <c:forEach items="${color}" var="color">
                <option value="${color.colorId}">${color.colorName}</option>
            </c:forEach>
        </select>

        <fmt:message key="selectCategory" bundle="${bundle}"/>
        <select name="categoryId">
            <c:forEach var="category" items="${category}">
                <option value="${category.categoryId}">${category.categoryName}</option>
            </c:forEach>
        </select>
        <input type="submit" value="<fmt:message key="send" bundle="${bundle}"/>"/>
    </form>
</div>
<h3><fmt:message key="formAlbum" bundle="${bundle}"/></h3>
<form action="/view/AddProduct" method="post">
    <fmt:message key="enterNameAlbum" bundle="${bundle}"/> <input type="text" name="productName"/></br>
    <fmt:message key="enterDescriptionAlbum" bundle="${bundle}"/> <input type="text" name="description"/></br>
    <fmt:message key="enterPriceAlbum" bundle="${bundle}"/> <input type="text" name="productPrice"/></br>
    <input type="hidden" name="key" value="album"/>
    </br>
    <input type="submit" value="<fmt:message key="send" bundle="${bundle}"/>"/>
</form>


<h3><fmt:message key="formMusic" bundle="${bundle}"/></h3>
<form action="/view/AddProduct" method="post" accept-charset="UTF-8">
    <fmt:message key="enterNameMusic" bundle="${bundle}"/> <input type="text" name="productName"/> </br>
    <fmt:message key="enterDiscriptionMusic" bundle="${bundle}"/> <input type="text" name="description"/> </br>
    <fmt:message key="enterPriceMusic" bundle="${bundle}"/> <input type="text" name="productPrice"/> </br>
    <input type="hidden" name="key" value="music"/>
    <select name="albumId">
        <c:forEach var="album" items="${album}">
            <option value="${album.productId}">${album.productName}</option>
        </c:forEach>
        <input type="submit" value="<fmt:message key="send" bundle="${bundle}"/>"/>
    </select>
</form>
<div align="right">
    <a href="/view/Exit"> <fmt:message key="signOut" bundle="${bundle}"/></a>
</div>
<h4><a href="/view/Welcome.jsp"><fmt:message key="goMainPage" bundle="${bundle}"/></a></h4>
</body>
</html>
