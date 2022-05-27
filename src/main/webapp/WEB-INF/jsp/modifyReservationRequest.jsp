<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Take Reservation</title>
    <link rel="stylesheet" href="">
</head>
<h1>Modify the reservation request for ${reservationRequest.habitation.name}</h1>
<body>
<form method="POST" action="/reservationRequest/${reservationRequest.reservationRequestId}/modify/send">
    <table>
        <tr>
            <th>Date of start</th>
            <th>Date of end</th>
            <th>Habitation for the reservation request :</th>
        </tr>
        <tr>
            <td><input type="date" name="userDateOfStart" value="${reservationRequest.start}" min="2018-01-01" max="2018-12-31"></td>
            <td><input type="date" name="userDateOfEnd" value="${reservationRequest.end}" min="2018-01-01" max="2018-12-31"></td>
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