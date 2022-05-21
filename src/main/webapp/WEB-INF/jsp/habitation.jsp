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
    <title>Habitation</title>
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
<div class="house-details">
    <div class="house-title">
        <h1>${habitation.description}</h1>
        <div class="row">
            <div>
                <i class="fas fa-star"></i>
                <span>${rating}</span>
            </div>
            <div>
                <p>Location: ${habitation.city}</p>
            </div>
        </div>
    </div>
    <div class="gallery">
        <div class="gallery-img-1"><img src="../images/house.jpg"></div>
        <div><img src="../images/house.jpg"></div>
        <div><img src="../images/house.jpg"></div>
        <div><img src="../images/house.jpg"></div>
        <div><img src="../images/house.jpg"></div>
    </div>
    <div class="small-details">
        <h2>Entire ${habitation.type} in ${habitation.city}</h2>
        <p>${habitation.bed} Bedrooms / ${habitation.bathrooms} Bathroom / <c:if test="${habitation.hasWifi}">WiFi</c:if> / Kitchen</p>
    </div>
    <hr class="line">
    <form class="reservation">
        <div>
            <label>Check-in</label>
            <input type="date" placeholder="Add date">
        </div>
        <div>
            <label>Check-Out</label>
            <input type="date" placeholder="Add date">
        </div>
        <div>
            <button type="submit">Check Availability</button>
        </div>
    </form>
    <hr class="line">
    <div class="map"></div>
</div>
</body>
</html>