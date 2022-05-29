<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My Messagings | HomeExchange</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
    <link rel="stylesheet" href="css/home.css">
    <link rel="stylesheet" href="css/chat.css">
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
                    <li><a href="/my-reservations-requests"><i class="fa-solid fa-calendar"></i>Reservations</a></li>
                    <li><a href=/infos-account><i class="fa-solid fa-user"></i>Account</a></li>
                    <c:if test="${user.roles == 'ROLE_ADMIN'}">
                        <li><a href="/admin"><i class="fa-solid fa-gear"></i>Admin</a></li>
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

<h1 style="text-align: center;padding: 10px;">List of my messagings</h1>
<div class="wrapper">
    <section class="users">
        <c:choose>
            <c:when test="${listOfExchanges.size() == 0}">
                You haven't any messaging yet
                <br/>
            </c:when>
            <c:otherwise>
                <div class="users-list">
                    <c:forEach items="${listOfExchanges}" var="exchange">
                        <div class="content">
                            <td>
                                <c:choose>
                                    <c:when test="${exchange.user1 == user}">
                                        Messaging with ${exchange.user2.first_name} ${exchange.user2.last_name}
                                    </c:when>
                                    <c:otherwise>
                                        Messaging with ${exchange.user1.first_name} ${excchange.user1.last_name}
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td><form action="/messaging/${exchange.exchangeId}" method="GET">
                                <input type="submit" name="seeExchange" value="See the exchange" style="cursor:pointer;color:white;padding: 15px 30px; border-radius: 10px; border: none; background: #008489"/>
                            </form></td>
                        </div>
                    </c:forEach>
                </div>
            </c:otherwise>
        </c:choose>
    </section>

</div>
</body>
</html>