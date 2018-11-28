
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${local}"/>
<fmt:setBundle basename="locale" var="bundle"/>
<html>
<head>
    <title><fmt:message key="shop" bundle="${bundle}"/> </title>
</head>
<body>

<c:if test="${sessionScope.User eq null}">
<a href="/view/Autorization.jsp"><fmt:message key="autorisation" bundle="${bundle}"/></a> <a href="/view/Registration.jsp"><fmt:message key="registration" bundle="${bundle}"/></a>
</c:if>
<c:if test="${sessionScope.User ne null}">
    <div align="right">
        <a href="/view/Exit"> <fmt:message key="signOut" bundle="${bundle}"/></a>
    </div>
</c:if>
<form action="/view/changeLocale" method="post">
<select name="LanguageList">
    <c:forEach var="language" items="${languages}">
        <option value="${language.languageId}">${language.languageLocal}</option>
    </c:forEach>
    <input type="submit" value="<fmt:message key="send" bundle="${bundle}"/>"/>
</select>
</form>
</br>

<c:forEach var="category" items="${allCategory}">
    <a href="/view/ShowGoods?categoryId=${category.categoryId}">${category.categoryName}</a>
</c:forEach>

<div align="left">
    <a href="/view/showGoodsInBasket"> <fmt:message key="basket" bundle="${bundle}"/> </a>
</div>
<div align="center">
<a href="/view/PersonalArea.jsp"> <fmt:message key="personalArea" bundle="${bundle}"/> </a>
<c:if test="${sessionScope.User.userRole eq 1}">
    <a href="/view/addProducts"> <fmt:message key="addProduct" bundle="${bundle}"/> </a>  <a href="/view/showAllGoods"><fmt:message key="showAllProduct" bundle="${bundle}"/></a> </br>
    <a href="/view/showUsers"><fmt:message key="showAllUsers" bundle="${bundle}"/></a> </br>
</c:if>
</div>

</body>
</html>