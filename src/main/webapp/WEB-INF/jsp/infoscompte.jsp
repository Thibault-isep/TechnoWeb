<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Homepage</title>
    <link rel="stylesheet" href="">
</head>
<body>
<c:if test="${!empty sessionScope.userId}">
    <h1>Infos de compte</h1>
    <form method="post" action="user/update">
        <label for="username">Pseudo : </label>
        <input name="username" type="text" id="username" value="${user.username}">

        <label for="firstname">Prénom : </label>
        <input name="firstname" type="text" id="firstname" value="${user.first_name}">

        <label for="lastname">Pseudo : </label>
        <input name="lastname" type="text" id="lastname" value="${user.last_name}">
        <button type="submit" class="button">Envoyer</button>
    </form>
    <table>
        <tr>
            <th>ID Habitation</th>
            <th>Salle de bain</th>
            <th>Ville</th>
        </tr>
        <c:forEach var="habits" items="${habits}" varStatus="loop">
            <tr>
                <th>${habits.type}</th>
                <th>${habits.user.userId}</th>
                <th>${habits.bathrooms}</th>
                <th>${habits.address}</th>
                <th>${habits.rooms}</th>
                <th>${habits.zip_code}</th>
                <th>${loop.index}</th>
                <c:forEach var="equipments" items="${equipmentsByHabitation[loop.index]}">
                    <th>
                    <p>${equipments.name}</p>
                    </th>
                </c:forEach>
            </tr>
        </c:forEach>
    </table>
</c:if>
<a href="addHabitation">Ajouter une habitation</a>
<a href="/">Menu</a>

</body>