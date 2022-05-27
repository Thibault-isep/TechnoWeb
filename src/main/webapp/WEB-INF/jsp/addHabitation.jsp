<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>HomeExchange | AddHabitation</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
    <link rel="stylesheet" href="../css/addHabitation.css">
</head>
<body>
<div class="header" style="background-image: linear-gradient(rgba(0,0,0,0.3),rgba(0,0,0,0.3)),url('../images/add.jpg');">
    <nav class="white">
        <a href="/"><h2 class="logo">HomeExchange</h2></a>
        <ul class="nav-links">
            <li><a href="/habitation/search">Accommodations</a></li>
            <li><a href="#">Become a Host</a></li>
        </ul>
        <c:choose>
            <c:when test="${user.userId != null}">
                <label for="profile2" class="profile-dropdown">
                    <input type="checkbox" id="profile2">
                    <p>${user.username}</p>
                    <label for="profile2"><i class="fa-solid fa-bars"></i></label>
                    <ul>
                        <li><a href="/myMessagings"><i class="fa-solid fa-message"></i>Messages</a></li>
                        <li><a href=/infoscompte><i class="fa-solid fa-user"></i>Account</a></li>
                        <c:if test="${user.roles == 'ROLE_ADMIN'}">
                            <li><a href="/admin"><i class="fa-solid fa-gear"></i>Settings</a></li>
                        </c:if>
                        <li><a href="/myReservationsRequests"><i class="fa-solid fa-calendar"></i>Reservations</a></li>
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
    <form method="post" action="" enctype="multipart/form-data">
        <div class="container" id="part1">
            <h1 style="padding-top: 1%;">Enter your home caracteristics</h1>
            <div class="search-bar" style="display: flex;flex-direction: row; justify-content: space-around">
                <div class="other-input">
                    <label for="Type">Type d'habitation</label>
                    <select id="Type" name="Type"/>
                        <option value="Appartement">Appartement</option>
                        <option value="House">Maison</option>
                        <option value="Studio">Studio</option>
                        <option value="Chambre">chambre</option>
                    </select>
                </div>
                <div class="other-input">
                    <label for="Name">Nom de l'habitation</label>
                    <input name="Name" id="Name" type="text" placeholder="Name" required>
                </div>
                <div class="other-input">
                    <label for="Address">Adresse</label>
                    <input name="Address" id="Address" type="text" placeholder="Address" required>
                </div>
                <div class="other-input">
                    <label for="Country">Pays</label>
                    <input name="Country" id="Country" type="text" placeholder="Country" required>
                </div>
            </div>
        </div>
        <div class="container" id="part2">
            <div class="search-bar" style="display: flex;flex-direction: row; justify-content: space-around">
                <div class="other-input">
                    <label for="Zip_Code">Code postal</label>
                    <input name="Zip_Code" id="Zip_Code" type="text" placeholder="Zip_Code" required>
                </div>
                <div class="other-input">
                    <label for="City">Ville</label>
                    <input name="City" id="City" type="text" placeholder="City" required>
                </div>
                <div class="other-input">
                    <label for="Rooms">Nombre de pièces</label>
                    <input name="Rooms" id="Rooms" type="number" placeholder="Rooms" required>
                </div>
                <div class="other-input" style="display: flex;flex-direction: column;">
                    <label for="Bed">Lits</label>
                    <input name="Bed" id="Bed" type="number" placeholder="Bed" required>
                </div>
            </div>
        </div>
        <div class="container" id="part3">
            <div class="search-bar" style="display: flex;flex-direction: row; justify-content: space-around">
                <div class="other-input">
                    <label for="Bathrooms">Salle de bain</label>
                    <input name="Bathrooms" id="Bathrooms" type="number" placeholder="Bathrooms" required>
                </div>
                <div class="other-input">
                    <label for="Services">Services</label>
                    <input name="Services" id="Services" type="text" placeholder="Services" required>
                </div>
                <div class="other-input">
                    <label for="Constraints">Contraintes</label>
                    <input name="Constraints" id="Constraints" type="text" placeholder="Constraints" required>
                </div>
                <div class="other-input" style="display: flex;flex-direction: column;">
                    <label for="Photos">Photos</label>
                    <input type="file" id="Photos" name="Photos" required accept="image/png, image/jpeg" multiple>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="search-bar" style="display: flex;flex-direction: row; justify-content: space-around">
                <c:forEach var="equipment" items="${equipment}">
                    <div class="other-input" style="display: flex;flex-direction: column;">
                        <label for="${equipment.name}">${equipment.name}</label>
                        <select name="equipments"/>
                        <option value="NON">NON</option>
                        <option value="OUI">OUI</option>
                        </select>
                    </div>
                </c:forEach>
            </div>
        </div>

        <label>Define reservation periods</label>
        <input type="button" value="Add a new period reservation" onclick="addReservationPeriod()"/>
        <div id="periodReservationContainer"></div>

        <div class="description">
            <label for="Description">Description</label><br>
            <input name="Description" id="Description" type="text" placeholder="Description" required class="descriptionInput" style="height: 200px;">
        </div>
        <div style="display: flex; justify-content: center; margin-top: 2px;">
            <input type="submit" value="Add" style="padding: 10px 50px; color: white; background: #008489;border: none;border-radius: 10px;">
        </div>
    </form>
</div>
</body>

<script>
    function addReservationPeriod() {
        var divDefineReservationPeriod = document.createElement('div');
        divDefineReservationPeriod.innerHTML = '<br><table><tr><th>Date of start</th><th>Date of end</th></tr><tr><td><input type="date" name="dateOfStart"></td><td><input type="date" name="dateOfEnd"></td></tr></table>';
        console.log(divDefineReservationPeriod);
        document.getElementById('periodReservationContainer').append(divDefineReservationPeriod);
    }
</script>

