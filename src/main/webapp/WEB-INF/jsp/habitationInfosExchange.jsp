<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../css/home.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
    <title>${habitation.name} | HomeExchange</title>
</head>
<body>
<nav class="white">
    <a href="/"><h2 class="logo">HomeExchange</h2></a>
    <ul class="nav-links">
        <li><a href="/habitation/search">Accommodations</a></li>
        <li><a href="/add-habitation">Become a Host</a></li>
    </ul>
    <c:choose>
        <c:when test="${user.userId != null}">
            <label for="profile2" class="profile-dropdown">
                <input type="checkbox" id="profile2">
                <p>${user.username}</p>
                <label for="profile2"><i class="fa-solid fa-bars"></i></label>
                <ul>
                    <li><a href="/my-messagings"><i class="fa-solid fa-message"></i>Messages</a></li>
                    <li><a href="/my-reservations-requests"><i class="fa-solid fa-calendar"></i>Requests</a></li>
                    <li><a href=/infos-account><i class="fa-solid fa-user"></i>Account</a></li>
                    <c:if test="${user.roles == 'ROLE_ADMIN'}">
                        <li><a href="/admin"><i class="fa-solid fa-gear"></i>Settings</a></li>
                    </c:if>
                    <li><a href="/logout"><i class="fa-solid fa-arrow-right-from-bracket"></i>Logout</a></li>
                </ul>
            </label>
        </c:when>
        <c:otherwise>
            <ul class="nav-links">
                <li><a href="/login" class="login-btn">Login</a></li>
                <li><a href="/register" class="register-btn">Register Now</a></li>
            </ul>
        </c:otherwise>
    </c:choose>
</nav>
<div class="house-details">
    <div class="house-title">
        <h1>${habitation.name}</h1>
        <div class="row">
            <p>Location: ${habitation.city}</p>
        </div>
    </div>
    <div class="gallery">
        <div class="gallery-img-1"><img src="../../images/house.jpg"></div>
        <c:forEach items="${photos}" var="photo" varStatus="loop">
            <div><img class="gallery-img-${loop.index + 2}" src="../${photo}"></div></c:forEach>
    </div>

    <div class="small-details">
        <h2>Entire ${habitation.type} in ${habitation.city}</h2>
        <p>${habitation.bed} Bedrooms / ${habitation.bathrooms} Bathroom /
            <c:forEach items="${equipments}" var="equipment">
                ${equipment.name} /
            </c:forEach>
            Kitchen</p>
    </div>
    <hr class="line">
    <div style="display: flex;justify-content: space-between;align-items: center;">
        <div class="host">
            <div>
                <h2>Hosted by ${habitation.user.first_name}</h2>
                <p>${habitation.user.description}</p>
                <form action="/messaging-from-habitation" method="POST">
                    <input type="hidden" name="habitationId" value="${habitation.habitationId}"/>
                    <input class="reservation" style="border: none;background: #008489;color: white;cursor: pointer;" type="submit" name="seeMessaging" value="Contact the owner"/>
                </form>
            </div>
        </div>
    </div>
    <hr class="line">
    <div>
        <h2 class="sub-title">Comments</h2>
        <c:forEach var="rating" items="${ratings}">
            <div class="comments">
                <div class="user-rating" style="display: flex; flex-direction: column">
                    <span style="font-size: 16px;line-height: 19px">${rating.user.username}</span>
                    <span style="font-size: 12px;line-height: 14px;">${rating.user.city}</span>
                </div>

                <div class="rating">
                    <p>${rating.rate}</p>
                    <p>${rating.description}</p>
                </div>
            </div>
        </c:forEach>
    </div>
    <hr class="line">
    <div class="map">
        <h3>Location on Map</h3>
        <iframe width="600" height="450" frameborder="0" style="border:0"
                src="https://www.google.com/maps/embed/v1/place?q=${habitation.city}, France&amp;key=AIzaSyDOwVaIg70MQVTpE86Ykwo1zzz7BOLwwuk">
        </iframe>
    </div>

</div>
</body>
</html>
