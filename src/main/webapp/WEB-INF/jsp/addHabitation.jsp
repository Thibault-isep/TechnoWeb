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
    <form method="post" action="" enctype="multipart/form-data">
        <div class="container" id="part1">
            <h1 style="padding-top: 1%;">Enter your home caracteristics</h1>
            <div class="search-bar" style="display: flex;flex-direction: row; justify-content: space-around">
                <div class="other-input">
                    <label for="Type">Habitation's Type</label>
                    <select id="Type" name="Type"/>
                        <option value="Apartment">Apartment</option>
                        <option value="House">House</option>
                        <option value="Studio">Studio</option>
                        <option value="Room">Room</option>
                    </select>
                </div>
                <div class="other-input">
                    <label for="Name">Habitation's name</label>
                    <input name="Name" id="Name" type="text" placeholder="Name" required>
                </div>
                <div class="other-input">
                    <label for="Address">Address</label>
                    <input name="Address" id="Address" type="text" placeholder="Address" required>
                </div>
                <div class="other-input">
                    <label for="Country">Country</label>
                    <input name="Country" id="Country" type="text" placeholder="Country" required>
                </div>
            </div>
        </div>
        <div class="container" id="part2">
            <div class="search-bar" style="display: flex;flex-direction: row; justify-content: space-around">
                <div class="other-input">
                    <label for="Zip_Code">Zip Code</label>
                    <input name="Zip_Code" id="Zip_Code" type="text" placeholder="Zip_Code" required>
                </div>
                <div class="other-input">
                    <label for="City">City</label>
                    <input name="City" id="City" type="text" placeholder="City" required>
                </div>
                <div class="other-input">
                    <label for="Rooms">Rooms</label>
                    <input name="Rooms" id="Rooms" type="number" placeholder="Rooms" required>
                </div>
                <div class="other-input" style="display: flex;flex-direction: column;">
                    <label for="Bed">Beds</label>
                    <input name="Bed" id="Bed" type="number" placeholder="Bed" required>
                </div>
            </div>
        </div>
        <div class="container" id="part3">
            <div class="search-bar" style="display: flex;flex-direction: row; justify-content: space-around">
                <div class="other-input">
                    <label for="Bathrooms">Bathrooms</label>
                    <input name="Bathrooms" id="Bathrooms" type="number" placeholder="Bathrooms" required>
                </div>
                <div class="other-input">
                    <label for="Services">Services</label>
                    <input name="Services" id="Services" type="text" placeholder="Services" required>
                </div>
                <div class="other-input">
                    <label for="Constraints">Constraints</label>
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
        <div class="container">
            <div class="search-bar">
                <div class="other-input" style="display: flex;flex-direction: column;">
                    <label>Define reservation periods</label>
                    <input type="button" value="Add a new period reservation" onclick="addReservationPeriod()"/>
                </div>
            </div>
        </div>

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
        divDefineReservationPeriod.innerHTML = '<div class="container"><div class="search-bar" style="display: flex;flex-direction: row; justify-content: space-around"><div class="other-input" style="display: flex;flex-direction: column;"><label>Date of start</label><input type="date" name="dateOfStart"></div><div class="other-input" style="display: flex;flex-direction: column;"><label>Date of end</label><input type="date" name="dateOfEnd"></div></div></div>';
        console.log(divDefineReservationPeriod);
        document.getElementById('periodReservationContainer').append(divDefineReservationPeriod);
    }
</script>

