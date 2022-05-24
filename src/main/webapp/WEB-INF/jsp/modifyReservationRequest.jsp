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
<h1>Modify the reservation for ${reservation.habitation.name}</h1>
<body>
<form method="POST" action="/reservation/${reservation.reservationId}/modify/send">
    <table>
        <tr>
            <th>Date of start</th>
            <th>Date of end</th>
        </tr>
        <tr>
            <td><input type="date" name="userDateOfStart" value="${reservation.start}" min="2018-01-01" max="2018-12-31"></td>
            <td><input type="date" name="userDateOfEnd" value="${reservation.end}" min="2018-01-01" max="2018-12-31"></td>
        </tr>
    </table>
    <input type="submit" value="Modify my reservation request">
</form>
</body>