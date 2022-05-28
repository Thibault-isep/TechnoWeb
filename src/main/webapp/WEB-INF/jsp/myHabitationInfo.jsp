<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit ${habitation.name} Informations | HomeExchange</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
    <link rel="stylesheet" href="../css/addHabitation.css">
</head>
<body>
<div class="header" style="background-image: linear-gradient(rgba(0,0,0,0.3),rgba(0,0,0,0.3)),url('../images/edit.jpg');">
    <nav class="white">
        <a href="/"><h2 class="logo">HomeExchange</h2></a>
        <ul class="nav-links">
            <li><a href="/habitation/search">Accommodations</a></li>
            <li><a href="/add-habitation">Become a Host</a></li>
        </ul>
        <c:choose>
            <c:when test="${user.userId != null}">
                <label for="profile2" class="profile-dropdown">
                    <input type="checkbox" id="profile2">
                    <p>${user.username}</p>
                    <label for="profile2"><i class="fa-solid fa-bars"></i></label>
                    <ul>
                        <li><a href="/my-messagings"><i class="fa-solid fa-message"></i>Messages</a></li>
                        <li><a href="/my-reservations-requests"><i class="fa-solid fa-calendar"></i>Requests</a></li>
                        <li><a href=/infos-account><i class="fa-solid fa-user"></i>Account</a></li>
                        <c:if test="${user.roles == 'ROLE_ADMIN'}">
                            <li><a href="/admin"><i class="fa-solid fa-gear"></i>Admin</a></li>
                        </c:if>
                        <li><a href="/logout"><i class="fa-solid fa-arrow-right-from-bracket"></i>Logout</a></li>
                    </ul>
                </label>
            </c:when>
            <c:otherwise>
                <ul class="nav-links">
                    <li><a href="/login" class="login-btn">Login</a></li>
                    <li><a href="/register" class="register-btn">Register Now</a></li>
                </ul>
            </c:otherwise>
        </c:choose>
    </nav>
    <form action="/update-habitation" method="POST" enctype="multipart/form-data">
        <input type="hidden" value="${habitation.habitationId}" name="habitationId">
        <div class="container" id="part1">
            <h1 style="padding-top: 1%;">Edit your habitation</h1>
            <div class="search-bar" style="display: flex;flex-direction: row; justify-content: space-around">
                <div class="other-input">
                    <label for="Type">Type d'habitation</label>
                    <select id="Type" name="Type" value="${habitation.type}"/>
                    <option value="Appartement">Appartement</option>
                    <option value="House">Maison</option>
                    <option value="Studio">Studio</option>
                    <option value="Chambre">chambre</option>
                    </select>
                </div>
                <div class="other-input">
                    <label for="Name">Nom de l'habitation</label>
                    <input name="Name" id="Name" type="text" placeholder="Name" value="${habitation.name}" required>
                </div>
                <div class="other-input">
                    <label for="Address">Adresse</label>
                    <input name="Address" id="Address" type="text" placeholder="Address"  value="${habitation.address}" required>
                </div>
                <div class="other-input">
                    <label for="Country">Pays</label>
                    <input name="Country" id="Country" type="text" placeholder="Country" value="${habitation.country}" required>
                </div>
            </div>
        </div>
        <div class="container" id="part2">
            <div class="search-bar" style="display: flex;flex-direction: row; justify-content: space-around">
                <div class="other-input">
                    <label for="Zip_Code">Code postal</label>
                    <input name="Zip_Code" id="Zip_Code" type="text" placeholder="Zip_Code" value="${habitation.zip_code}" required>
                </div>
                <div class="other-input">
                    <label for="City">Ville</label>
                    <input name="City" id="City" type="text" placeholder="City" value="${habitation.city}" required>
                </div>
                <div class="other-input">
                    <label for="Rooms">Nombre de pièces</label>
                    <input name="Rooms" id="Rooms" type="number" placeholder="Rooms" value="${habitation.rooms}" required>
                </div>
                <div class="other-input" style="display: flex;flex-direction: column;">
                    <label for="Bed">Lits</label>
                    <input name="Bed" id="Bed" type="number" placeholder="Bed" value="${habitation.bed}" required>
                </div>
            </div>
        </div>
        <div class="container" id="part3">
            <div class="search-bar" style="display: flex;flex-direction: row; justify-content: space-around">
                <div class="other-input">
                    <label for="Bathrooms">Salle de bain</label>
                    <input name="Bathrooms" id="Bathrooms" type="number" placeholder="Bathrooms" value="${habitation.bathrooms}" required>
                </div>
                <div class="other-input">
                    <label for="Services">Services</label>
                    <input name="Services" id="Services" type="text" placeholder="Services" value="${habitation.services}" required>
                </div>
                <div class="other-input">
                    <label for="Constraints">Contraintes</label>
                    <input name="Constraints" id="Constraints" type="text" placeholder="Constraints" value="${habitation.constraints}" required>
                </div>
                <div class="other-input" style="display: flex;flex-direction: column;">
                    <label for="Photos">Photos</label>
                    <input type="file" id="Photos" name="Photos" accept="image/png, image/jpeg" multiple>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="search-bar" style="display: flex;flex-direction: row; justify-content: space-around">
                <c:forEach var="equipment" items="${equipment}">
                    <div class="other-input" style="display: flex;flex-direction: column;">
                        <label for="${equipment.name}">${equipment.name}</label>
                        <select name="equipments"/>
                            <c:choose>
                                <c:when test="${fn:contains(habitationsequipment, equipment)}">
                                    <option value="OUI">OUI</option>
                                    <option value="NON">NON</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="NON">NON</option>
                                    <option value="OUI">OUI</option>
                                </c:otherwise>
                            </c:choose>
                        </select>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="description">
            <label for="Description">Description</label><br>
            <input name="Description" id="Description" type="text" placeholder="Description" required class="descriptionInput" value="${habitation.description}" style="height: 200px;">
        </div>
        <div class="container">
            <c:forEach var="reservationPeriod" items="${reservationPeriods}">
                <input type="hidden" value="${reservationPeriod.reservationPeriodId}" name="reservationPeriodId">
            <div class="search-bar" style="display: flex;flex-direction: row; justify-content: space-around">
                <div class="other-input" style="display: flex;flex-direction: column;">
                    <label>Date of start</label>
                    <c:choose>
                        <c:when test="${reservationPeriod.validate == true}">
                            <input type="date" value="${reservationPeriod.start}" name="dateOfStart" readonly="readonly">
                        </c:when>
                        <c:otherwise>
                            <input type="date" value="${reservationPeriod.start}" name="dateOfStart">
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="other-input" style="display: flex;flex-direction: column;">
                    <label>Date of end</label>
                    <c:choose>
                        <c:when test="${reservationPeriod.validate == true}">
                            <input type="date" value="${reservationPeriod.end}" name="dateOfEnd" readonly="readonly">
                        </c:when>
                        <c:otherwise>
                            <input type="date" value="${reservationPeriod.end}" name="dateOfEnd">
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            </c:forEach>
            <div id="periodReservationContainer"></div>
            <div style="display: flex; justify-content: center; margin-top: 2px;">
                <input type="button" value="Add a new period reservation" onclick="addReservationPeriod()"/>
            </div>
        </div>
        <div style="display: flex; justify-content: center; margin-top: 2px;">
            <input type="submit" value="Edit" style="padding: 10px 50px; color: white; background: #008489;border: none;border-radius: 10px;">
        </div>
    </form>
    <div class="gallery">
        <c:forEach items="${photos}" var="photo" varStatus="loop">
            <div class="gallery-img-${loop.index + 1}">
                <img src="${photos[loop.index]}">
                <a href="/my-habitation/${habitation.habitationId}/delete-image/${loop.index}">Delete this image</a>
            </div>
        </c:forEach>
    </div>
</div>

</body>
</html>

<script>
    function addReservationPeriod() {
        var divDefineReservationPeriod = document.createElement('div');
        divDefineReservationPeriod.innerHTML = '<div class="search-bar" style="display: flex;flex-direction: row; justify-content: space-around"><div class="other-input" style="display: flex;flex-direction: column;"> <label>Date of start</label><input type="date" name="newDateOfStart"></div><div class="other-input" style="display: flex;flex-direction: column;"><label>Date of end</label><input type="date" name="newDateOfEnd"></td>';
        console.log(divDefineReservationPeriod);
        document.getElementById('periodReservationContainer').append(divDefineReservationPeriod);
    }
</script>