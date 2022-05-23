<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My reservations requests</title>
</head>
<body>
<h1>List of my reservations resquests</h1>
<div>
    <c:choose>
        <c:when test="${listOfExchanges.size() == 0}">
            You haven't any messaging yet
            <br/>
        </c:when>
        <c:otherwise>
            <table>
                <c:forEach items="${listOfReservations}" var="reservation">
                    <tr>
                        <td>${reservation.name}</td>
                        <td><form action="/reservation/${reservation.reservationId}/modify" method="GET">
                            <input type="submit" name="modifiyRegistration" value="Modify the reservation request"/>
                        </form></td>
                        <td><form action="/reservation/${reservation.reservationId}/delete" method="GET">
                            <input type="submit" name="deleteRegistration" value="Delete the registration request"/>
                        </form></td>
                    </tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>