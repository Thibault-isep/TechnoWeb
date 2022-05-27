<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Search Results</title>
    <link rel="stylesheet" href="../css/home.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.js"></script>
</head>
<body>
<nav class="white">
    <h2 class="logo">HomeExchange</h2>
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
                    <li><a href="#"><i class="fa-solid fa-gear"></i>Settings</a></li>
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
<div class="container">
    <div class="search-bar" style="box-shadow: rgba(0,0,0,0.24) 0px 3px 8px;">
        <div class="form-search">
            <input type="text" name="searchBar" size="40" id="searchBar" placeholder="Where do you want to go?" value="${userSearch}" />
            <div class="other-input">
                <label for="Beds"><small>Beds</small></label>
                <div style="display: flex">
                    <input type="range" id="Beds" name="rooms" min="1" max="${MaxBeds}" value="1" step="1" oninput="document.getElementById('AfficheRange').textContent=value"/><span id="AfficheRange">1</span>
                </div>
            </div>
            <div class="other-input">
                <label for="Bathrooms"><small>Bathrooms</small></label>
                <div style="display: flex">
                    <input type="range" id="Bathrooms" name="bathrooms" min="1" max="7" value="1" step="1" oninput="document.getElementById('AfficheRangeBath').textContent=value"/><span id="AfficheRangeBath">1</span>
                </div>
            </div>
            <div class="other-input">
                <label for="Type"><small>Type</small></label>
                <div style="display: flex">
                    <select id="Type">
                        <option value="All" selected="selected">All</option>
                        <option value="House">House</option>
                        <option value="Flat">Flat</option>
                    </select>
                </div>
            </div>
        </div>


    </div>
</div>


<div class="container">
    <div class="list-container">
        <div class="left-col">
            <c:choose>
                <c:when test="${habitations.size()==0}">
                    <p>No result found for you search</p>
                </c:when>
                <c:otherwise>
                    <p id="error"></p>
                    <c:forEach items="${habitations}" var="h" varStatus="loop">
                        <a href="/habitation/${h.habitationId}/${reservationPeriods[loop.index].reservationPeriodId}" style="text-decoration: none;">
                            <div class="house" id="${h.habitationId}">
                                <div class="house-img">
                                    <img src="../images/house.jpg">
                                </div>
                                <div class="house-info">
                                    <p>Entire ${h.type} in ${h.city}</p>
                                    <h3>${h.description}</h3>
                                    <p>${h.bed} Bedrooms / ${h.bathrooms} Bathroom /
                                        <c:forEach items="${Equipments[loop.index]}" var="equipment">
                                        ${equipment.name} /
                                        </c:forEach> Kitchen / ${Means[loop.index]}</p>
                                    <br>
                                    <p>Available from ${reservationPeriods[loop.index].start} to ${reservationPeriods[loop.index].end}</p>
                                </div>
                            </div>
                        </a>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="right-col">

        </div>
    </div>
</div>
</body>
</html>
<script>
    class habit {
        getcity() {
            return this.city;
        }

        setcity(value) {
            this.city = value;
        }

        gettype() {
            return this.type;
        }

        settype(value) {
            this.type = value;
        }

        sethabitationId(value) {
            this.habitationId = value;
        }

        gethabitationId() {
            return this.habitationId;
        }

        setuserId(value) {
            this.userId = value;
        }

        getuserId() {
            return this.userId;
        }

        setbed(value) {
            this.bed = value;
        }

        getbed() {
            return this.bed;
        }

        setbathrooms(value){
            this.bathrooms = value;
        }

        getbathrooms(){
            return this.bathrooms;
        }

        constructor(type, city, habitationId, userId, bed,bathrooms) {
            this.habitationId = habitationId;
            this.type = type;
            this.city = city;
            this.userId = userId;
            this.bed = bed;
            this.bathrooms = bathrooms;
        }
    }

    function myFunction() {
        const habitation = [];
        <c:forEach items="${habitations}" var="h">
        habitation.push(new habit("<c:out value="${h.type}"/>", "<c:out value="${h.city}"/>", "<c:out value="${h.habitationId}"/>", "<c:out value="${h.user.userId}"/>", "<c:out value="${h.bed}"/>",  "<c:out value="${h.bathrooms}"/>"));
        </c:forEach>
        typeInput = document.getElementById("Type");
        typeFilter = typeInput.options[typeInput.selectedIndex].text;

        cityInput = document.getElementById("searchBar");
        cityFilter = cityInput.value.toLowerCase();

        bedInput = document.getElementById("Beds");
        bedFilter = bedInput.value.toLowerCase();

        bathroomsInput = document.getElementById("Bathrooms");
        bathroomsFilter = bathroomsInput.value.toLowerCase();

        for (i = 0; i < habitation.length; i++) {
            type = habitation[i].type;
            city = habitation[i].city;
            bed = habitation[i].bed;
            bathrooms = habitation[i].bathrooms;
            if(!(typeFilter === "All")){
                if (city.toLowerCase().indexOf(cityFilter) < 0 || bed < bedFilter || bathrooms < bathroomsFilter || type.indexOf(typeFilter) < 0) {
                    document.getElementById(habitation[i].habitationId).style.display = "none";
                } else {
                    document.getElementById(habitation[i].habitationId).style.display = "";
                }
            }else{
                if (city.toLowerCase().indexOf(cityFilter) < 0 || bed < bedFilter || bathrooms < bathroomsFilter) {
                    document.getElementById(habitation[i].habitationId).style.display = "none";
                } else {
                    document.getElementById(habitation[i].habitationId).style.display = "";
                }
            }

        }
        $(document).ready(function() {
            var count = $('div.house:hidden').length;
            if(count == habitation.length){
                document.getElementById('error').textContent = "No result found for your research";
            }else{
                document.getElementById('error').textContent = "";
            }
        })

    }

    setInterval(myFunction, 100);
</script>