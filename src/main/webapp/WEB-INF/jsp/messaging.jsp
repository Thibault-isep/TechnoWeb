<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Message | HomeExchange</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
    <link rel="stylesheet" href="../css/chat.css">
    <link rel="stylesheet" href="../css/home.css">
</head>
<body>
<nav class="white">
    <a href="/"><h2 class="logo">HomeExchange</h2></a>
    <ul class="nav-links">
        <li><a href="/habitation/search">Accommodations</a></li>
        <li><a href="#">Become a Host</a></li>
    </ul>
    <c:choose>
        <c:when test="${user.userId != null}">
            <label for="profile2" class="profile-dropdown">
                <input type="checkbox" id="profile2">
                <p>${user.username}</p>
                <label for="profile2"><i class="fa-solid fa-bars"></i></label>
                <ul>
                    <li><a href="/myMessagings"><i class="fa-solid fa-message"></i>Messages</a></li>
                    <li><a href=/infoscompte><i class="fa-solid fa-user"></i>Account</a></li>
                    <c:if test="${user.roles == 'ROLE_ADMIN'}">
                        <li><a href="/admin"><i class="fa-solid fa-gear"></i>Settings</a></li>
                    </c:if>
                    <li><a href="/myReservationsRequests"><i class="fa-solid fa-calendar"></i>Reservations</a></li>
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
<div class="wrapper" style="margin-top: 40px">
    <section class="chat-area">
        <header>
            <a href="/myMessagings" class="back-icon"><i class="fas fa-arrow-left"></i></a>
            <div class="details">
                <span>${otherUser.first_name} ${otherUser.last_name}</span>
            </div>
        </header>
        <div class="chat-box">
            <c:forEach items="${exchangeMessages}" var="message">
                <div class="chat
        <c:choose>
        <c:when test="${message.user.userId == otherUser.userId}">
                incoming
        </c:when>
        <c:otherwise>
                outgoing
        </c:otherwise>
                </c:choose>">
                    <div class="details">
                        <p>${message.content}</p>
                    </div>
                </div>
            </c:forEach>
        </div>
        <form class="typing-area" action="/messaging/${exchangeId}/send" method="POST">
            <td><input class="incoming_id" type="text" name="messageContent" size="20" default="Send my message"/></td>
            <td><input class="input-field" type="submit" name="action" value="Send" style="width: 70px;"/></td>
        </form>
    </section>
</div>
</body>
</html>