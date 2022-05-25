<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Search Results</title>
</head>
<body>
<div>
    <table>
        <tr>
            <th>ID Habitation</th>
            <th>Salle de bain</th>
            <th>Ville</th>
        </tr>
        <tr>
            <td>${habitation.habitationId}</td>
            <td>${habitation.bathrooms}</td>
            <td>${habitation.city}</td>
        </tr>
    </table>
    <form action="/messagingFromHabitation" method="POST">
        <input type="hidden" name="habitationId" value="${habitation.habitationId}"/>
        <input type="submit" name="seeMessaging" value="Contact the owner"/>
    </form>
    <c:forEach var="rating" items="${ratings}">
        <p>${rating.rate}</p>
        <p>${rating.description}</p>
        <p>by : ${rating.user.username}</p>
    </c:forEach>
</div>
</body>
</html>