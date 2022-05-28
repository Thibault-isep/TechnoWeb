<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
    <title>My reservation requests | HomeExchange</title>
    <link rel="stylesheet" href="css/home.css">
    <link rel="stylesheet" href="css/table.css">
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
    <div style="display: flex;justify-content: center;flex-direction: column; padding-top: 50px;">
        <h1 style="text-align: center;">List of my reservation requests</h1>
        <c:choose>
            <c:when test="${listOfReservationRequests.size() == 0}">
                <p style="text-align: center">You haven't any reservation request yet</p>
            </c:when>
            <c:otherwise>
                <table class="rwd-table" style="width: 50%; margin: auto; padding-top: 10px;">
                    <c:forEach items="${listOfReservationRequests}" var="reservationRequest">
                        <tr>
                            <td>${reservationRequest.name}</td>
                            <c:choose>
                                <c:when test="${reservationRequest.validate == 0}">
                                    <td><form action="/reservation-request/${reservationRequest.reservationRequestId}/modify" method="GET">
                                        <input type="submit" name="modifiyRegistration" value="Modify the reservation request"/>
                                    </form></td>
                                    <td><form action="/reservation-request/${reservationRequest.reservationRequestId}/delete" method="GET">
                                        <input type="submit" name="deleteRegistration" value="Delete the registration request"/>
                                    </form></td>
                                </c:when>
                                <c:when test="${reservationRequest.validate == -1}">
                                    <td>Your request has been rejected</td>
                                </c:when>
                                <c:when test="${reservationRequest.validate == 1}">
                                    <td>Your request has been accepted !</td>
                                </c:when>
                            </c:choose>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>