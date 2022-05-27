<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Reservation | HomeExchange</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
    <link rel="stylesheet" href="../../css/home.css">
    <link rel="stylesheet" href="../../css/table.css">
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
<h1 class="sub-title" style="padding-top: 40px;text-align: center;"> Edit Reservation</h1>
<form method="POST" action="/reservationRequest/${reservationRequest.reservationRequestId}/modify/send" style="display: flex; flex-direction: column; justify-content: center; align-items: center;">
    <table class="rwd-table">
        <tr>
            <th>Date of start</th>
            <th>Date of end</th>
            <th>Habitation for the reservation request :</th>
        </tr>
        <tr>
            <td><input type="date" name="userDateOfStart" value="${reservationRequest.start}" min="2018-01-01" max="2022-12-31"></td>
            <td><input type="date" name="userDateOfEnd" value="${reservationRequest.end}" min="2018-01-01" max="2022-12-31"></td>
            <td>
            <select name="habitationToExchangeId"/>
                <option>--Please choose an option--</option>
                <c:forEach items="${habitations}" var="habitation">
                    <option name="habitation" value="${habitation.habitationId}" <c:choose>
                                <c:when test="${habitation.habitationId == reservationRequest.habitationToExchange.habitationId}">
                                    selected
                                </c:when></c:choose>>
                            ${habitation.name}
                    </option>
                </c:forEach>
            </select>
        </td>
        </tr>

    </table>
    <input type="submit" value="Modify my reservation request">
</form>
</body>