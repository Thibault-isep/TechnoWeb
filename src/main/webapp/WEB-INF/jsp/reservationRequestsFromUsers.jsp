<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My reservation requests</title>
</head>
<body>
<h1>List of my reservation requests</h1>
<div>
    <c:choose>
        <c:when test="${listOfReservationRequests.size() == 0}">
            You haven't any reservation request yet
            <br/>
        </c:when>
        <c:otherwise>
            <table>
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
                                <td><form action="/reservationRequest/${reservationRequest.reservationRequestId}/accept" method="GET">
                                    <input type="submit" name="acceptReservationRequest" value="Accept the reservation request"/>
                                </form></td>
                                <td><form action="/reservationRequest/${reservationRequest.reservationRequestId}/refuse" method="GET">
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