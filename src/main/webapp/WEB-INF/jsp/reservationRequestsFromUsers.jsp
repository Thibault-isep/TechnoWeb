<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
    <title>Reservations | HomeExchange</title>
    <link rel="stylesheet" href="../../css/home.css">
    <link rel="stylesheet" href="../../css/table.css">
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
    <h2 class="sub-title">List of my reservation requests</h2>
    <c:choose>
        <c:when test="${listOfReservationRequests.size() == 0}">
            You haven't any reservation request yet
            <br/>
        </c:when>
        <c:otherwise>
            <table class="rwd-table">
                <tr>
                    <th>Name of the request</th>
                    <th>Date of start</th>
                    <th>Date of end</th>
                    <th>Habitation for the reservation request :</th>
                </tr>
                <c:forEach items="${listOfReservationRequests}" var="reservationRequest">
                    <tr>
                        <td>${reservationRequest.name}</td>
                        <td><input type="date" name="requestDateOfStart" value="${reservationRequest.start}" readonly="readonly"></td>
                        <td><input type="date" name="requestDateOfEnd" value="${reservationRequest.end}" readonly="readonly"></td>
                        <td><input name="habitationReservationRequest" value="${reservationRequest.habitationToExchange.name}" readonly="readonly"></td>
                        <td><form action="/habitation/${reservationRequest.habitationToExchange.habitationId}/exchange" method="GET">
                            <input type="submit" name="seeHabitation" value="See the user habitation for the exchange"/>
                        </form></td>
                        <c:choose>
                            <c:when test="${reservationRequest.validate == 0}">
                                <td><form action="/reservation-request/${reservationRequest.reservationRequestId}/accept" method="GET">
                                    <input type="submit" name="acceptReservationRequest" value="Accept the reservation request"/>
                                </form></td>
                                <td><form action="/reservation-request/${reservationRequest.reservationRequestId}/refuse" method="GET">
                                    <input type="submit" name="refuseReservationRequest" value="Refuse the reservation request"/>
                                </form></td>
                            </c:when>
                        </c:choose>
                        <c:choose>
                            <c:when test="${reservationRequest.validate == 1}">
                                <td>Reservation request accepted !</td>
                            </c:when>
                        </c:choose>
                    </tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>