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
<h1>Take reservation for the habitation ${habitation.name}</h1>
<body>
<form method="POST" action="/reservationRequest/${habitation.habitationId}/send">
    <table>
        <tr>
            <th>Date of start</th>
            <th>Date of end</th>
        </tr>
        <tr>
            <td><input type="date" name="userDateOfStart" value="2018-07-22" min="2018-01-01" max="2018-12-31"></td>
            <td><input type="date" name="userDateOfEnd" value="2018-07-22" min="2018-01-01" max="2018-12-31"></td>
        </tr>
    </table>
    <input type="submit" value="ask owner for a reservation">
</form>
</body>