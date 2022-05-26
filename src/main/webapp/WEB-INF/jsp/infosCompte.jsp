<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${user.username} page de profil</title>
    <link rel="stylesheet" href="">
</head>
<body>
<c:if test="${!empty sessionScope.userId}">
    <h1>Infos de compte</h1>
    <form method="post" action="user/update">
        <label for="username">Pseudo : </label>
        <input name="username" type="text" id="username" value="${user.username}">
        <br>

        <label for="firstname">Prénom : </label>
        <input name="firstname" type="text" id="firstname" value="${user.first_name}">
        <br>

        <label for="lastname">Nom de famille : </label>
        <input name="lastname" type="text" id="lastname" value="${user.last_name}">
        <br>

        <label for="Dob">Date de naissance : </label>
        <input name="Dob" type="date" id="Dob" value="${user.dob}">
        <br>

        <label for="Email">Email : </label>
        <input name="Email" type="text" id="Email" value="${user.email}">
        <br>

        <label for="Gender">Gender</label>
        <select id="Gender" name="Gender" value="${user.gender}"/>
        <option value="Man">Homme</option>
        <option value="Woman">Femme</option>
        </select>
        <br>

        <label for="Address">Adresse : </label>
        <input name="Address" type="text" id="Address" value="${user.address}">
        <br>


        <label for="City">Ville : </label>
        <input name="City" type="text" id="City" value="${user.city}">
        <br>

        <label for="Zip_Code">Code postal : </label>
        <input name="Zip_Code" type="text" id="Zip_Code" value="${user.zip_code}">
        <br>

        <label for="Phone_Number">Numéro de téléphone : </label>
        <input name="Phone_Number" type="text" id="Phone_Number" value="${user.phone_number}">
        <br>

        <label for="Description">Description : </label>
        <input name="Description" type="text" id="Description" value="${user.description}">
        <br>

        <button type="submit" class="button">Envoyer</button>

    </form>
    <table>
        <tr>
            <th>Nom de l'habitation</th>
            <th>Type</th>
            <th>Nombre de lits</th>
            <th>Nombre de pièces</th>
            <th>Salle de bain</th>
            <th>Description</th>
            <th>Adresse</th>
            <th>Ville</th>
            <th>Pays</th>
            <th>Code Postal</th>
            <th>Services</th>
            <th>Constraints</th>
            <c:forEach var="equipment" items="${equipments}">
                <th>${equipment.name}</th>
            </c:forEach>
            <th></th>
        </tr>
        <c:forEach var="habits" items="${habits}" varStatus="loop">
            <tr>
                <th>${habits.name}</th>
                <th>${habits.type}</th>
                <th>${habits.bed}</th>
                <th>${habits.rooms}</th>
                <th>${habits.bathrooms}</th>
                <th>${habits.description}</th>
                <th>${habits.address}</th>
                <th>${habits.city}</th>
                <th>${habits.country}</th>
                <th>${habits.zip_code}</th>
                <th>${habits.services}</th>
                <th>${habits.constraints}</th>
                <c:forEach var="equipment" items="${equipments}">
                    <th>
                        <c:forEach var="equipments" items="${equipmentsByHabitation[loop.index]}">
                            <p><c:if test="${equipment.name == equipments.name}">TRUE</c:if></p>
                        </c:forEach>
                    </th>
                </c:forEach>
                <form action="myhabitations/${habits.habitationId}" method="post">
                    <th><input type="submit" value="Modifier"></th>
                </form>
                <form action="reservationRequest/${habits.habitationId}/fromUsers" method="get">
                    <th><input type="submit" value="View users reservations requests on my habitation"></th>
                </form>
            </tr>
        </c:forEach>
    </table>
</c:if>
<a href="addhabitation">Ajouter une habitation</a>
<a href="myMessagings">View my messagings</a>
<a href="/">Menu</a>

</body>