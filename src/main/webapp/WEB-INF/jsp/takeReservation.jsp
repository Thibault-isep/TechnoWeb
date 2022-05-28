<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Take Reservation | HomeExchange</title>
    <link rel="stylesheet" href="../../css/home.css">
    <link rel="stylesheet" href="../../css/table.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
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
<div class="container">
    <h1 class="sub-title" style="padding-top: 40px;text-align: center;">Take reservation for the habitation ${habitationToRequest.name}</h1>
    <form method="POST" action="/reservation-request/${habitationToRequest.habitationId}/${reservationPeriod.reservationPeriodId}/send" style="display: flex; flex-direction: column; justify-content: center; align-items: center;">
        <table class="rwd-table">
            <tr>
                <th>Date of start</th>
                <th>Date of end</th>
                <th>Choose your habitation for the reservation request :</th>
            </tr>
            <tr>
                <td><input type="date" name="userDateOfStart" value="${reservationPeriod.start}"></td>
                <td><input type="date" name="userDateOfEnd" value="${reservationPeriod.end}"></td>
                <td>
                    <select name="habitationToExchangeId"/>
                    <option>--Please choose an option--</option>
                    <c:forEach items="${habitationsToExchange}" var="habitation">
                        <option name="habitationToExchange" value="${habitation.habitationId}">${habitation.name}</option>
                    </c:forEach>
                    </select>
                </td>
            </tr>
        </table>
        <input type="submit" value="ask owner for a reservation">
    </form>
</div>

</body>