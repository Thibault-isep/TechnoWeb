<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/home.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
    <title>Search Results</title>
</head>
<body>
<nav class="white">
    <h2 class="logo">HomeExchange</h2>
    <ul class="nav-links">
        <li><a class="active" href="#">Accommodations</a></li>
        <li><a href="#">Become a Host</a></li>
    </ul>
    <c:choose>
        <c:when test="${user.userId != null}">
            <ul class="nav-links">
                <li>Thib</li>
            </ul>
        </c:when>
        <c:otherwise>
            <ul class="connexion-links">
                <li><a href="/login" class="login-btn">Login</a></li>
                <li><a href="/register" class="register-btn">Register Now</a></li>
            </ul>
        </c:otherwise>
    </c:choose>
</nav>
<div class="container">
    <div class="list-container">
        <div class="left-col">
            <c:choose>
                <c:when test="${result.size()==0}">
                    <p>No result found for you search</p>
                </c:when>
                <c:otherwise>
                    <p>
                        <c:if test="${result.size() == 1}">
                            ${result.size()} hébergement à ${city}
                        </c:if>
                        <c:if test="${result.size() > 1}">
                            ${result.size()} hébergements à ${city}
                        </c:if>
                    </p>
                    <c:forEach items="${result}" var="h" varStatus="loop">
                        <a href="/habitation/${h.idHabitation}">
                            <div class="house">
                                <div class="house-img">
                                    <img src="../images/house.jpg">
                                </div>
                                <div class="house-info">
                                    <p>Entire ${h.type} in ${city}</p>
                                    <h3>${h.description}</h3>
                                    <p>${h.bed} Bedrooms / ${h.bathrooms} Bathroom / <c:if test="${h.hasWifi}">WiFi</c:if> / Kitchen</p>
                                    <c:choose>
                                        <c:when test="${rating[loop.index]==null}">
                                            <i class="far fa-star"></i>
                                            <i class="far fa-star"></i>
                                            <i class="far fa-star"></i>
                                            <i class="far fa-star"></i>
                                            <i class="far fa-star"></i>
                                        </c:when>
                                        <c:otherwise>
                                            <i class="fas fa-star"></i>${rating[loop.index]}
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </a>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="right-col">

        </div>
    </div>
</div>
</body>
</html>