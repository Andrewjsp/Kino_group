
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


<a href="/view/Autorization.jsp"><fmt:message key="autorisation" bundle="${bundle}"/></a> <a href="/view/Registration.jsp"><fmt:message key="registration" bundle="${bundle}"/></a>


<form action="/changeLocale" method="post">
<select name="LanguageList">
    <c:forEach var="language" items="${languages}">
        <option value="${language.languageId}">${language.languageLocal}</option>
    </c:forEach>
    <input type="submit" value="<fmt:message key="send" bundle="${bundle}"/>"/>
</select>
</form>
</br>

<c:forEach var="category" items="${allCategory}">
    <a href="/ShowGoods?categoryId=${category.categoryId}">${category.categoryName}</a>
</c:forEach>

<div align="left">
    <a href="/showGoodsInBasket"> <fmt:message key="basket" bundle="${bundle}"/> </a>
</div>
<div align="center">
<a href="/PersonalArea"> <fmt:message key="personalArea" bundle="${bundle}"/> </a>
<c:if test="${sessionScope.User.userRole eq 1}">
    <a href="/addProducts"> <fmt:message key="addProduct" bundle="${bundle}"/> </a>  <a href="/showAllGoods"><fmt:message key="showAllProduct" bundle="${bundle}"/></a> </br>
    <a href="/showUsers"><fmt:message key="showAllUsers" bundle="${bundle}"/></a> </br>
</c:if>
</div>
</body>
</html>