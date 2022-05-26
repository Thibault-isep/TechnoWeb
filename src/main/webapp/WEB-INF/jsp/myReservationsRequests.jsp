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
                <c:forEach items="${listOfReservationRequests}" var="reservationRequest">
                    <td>${reservationRequest.name}</td>
                    <c:choose>
                        <c:when test="${reservationRequest.validate == 0}">
                            <td><form action="/reservationRequest/${reservationRequest.reservationRequestId}/modify" method="GET">
                                <input type="submit" name="modifiyRegistration" value="Modify the reservation request"/>
                            </form></td>
                            <td><form action="/reservationRequest/${reservationRequest.reservationRequestId}/delete" method="GET">
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
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>