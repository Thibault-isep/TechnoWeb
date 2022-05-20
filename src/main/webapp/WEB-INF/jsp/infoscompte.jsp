<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Homepage</title>
    <link rel="stylesheet" href="">
</head>
<body>
<c:if test="${!empty sessionScope.userId}" >
    <p>Vous êtes ${user.email} ${user.first_name} ${user.username}</p>
    <table>
        <tr>
            <th>ID Habitation</th>
            <th>Salle de bain</th>
            <th>Ville</th>
        </tr>
        <c:forEach var="habits" items="${habits}">
            <tr>
                <th>${habits.type}</th>
                <th>${habits.user.userId}</th>
                <th>${habits.bathrooms}</th>
                <th>${habits.address}</th>
                <th>${habits.rooms}</th>
                <th>${habits.zip_code}</th>
            </tr>
        </c:forEach>
    </table>
</c:if>
<a href="/">Menu</a>

</body>