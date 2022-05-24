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
</head>
<body>
<nav class="white">
    <a class="logo" href='/'>HomeExchange</a></h2>
    <ul class="nav-links">
        <li><a class="active" href="#">Accommodations</a></li>
        <li><a href="#">Become a Host</a></li>
    </ul>
    <c:choose>
        <c:when test="${user.userId != null}">
            <ul class="nav-links">
                <li>${user.username}</li>
            </ul>
        </c:when>
        <c:otherwise>
            <ul class="connexion-links">
                <li><a href="/login" class="login-btn">Login</a></li>
                <li><a href="/register" class="register-btn">Register Now</a></li>
            </ul>
        </c:otherwise>
    </c:choose>
</nav>
<p id="test"></p>
    <h1>Recherche :</h1>
    <input type="text" name="searchBar" size="40" id="searchBar" placeholder="Where do you want to go?" value="${userSearch}"/>
    <br>
<br>
<br>
<br>
<h1 id="empty" style ="display: none">Empty</h1>

<div class="container">
    <div class="list-container">
        <div class="left-col">
            <c:choose>
                <c:when test="${habitations.size()==0}">
                    <p>No result found for you search</p>
                </c:when>
                <c:otherwise>
                    <p>
                        <c:if test="${habitations.size() == 1}">
                            ${habitations.size()} hébergement à ${city}
                        </c:if>
                        <c:if test="${result.size() > 1}">
                            ${habitations.size()} hébergements à ${city}
                        </c:if>
                    </p>
                    <c:forEach items="${habitations}" var="h" varStatus="loop">
                        <a href="/habitation/${h.habitationId}">
                            <div class="house" id="${h.habitationId}">
                                <div class="house-img">
                                    <img src="../images/house.jpg">
                                </div>
                                <div class="house-info">
                                    <p>Entire ${h.type} in ${city}</p>
                                    <h3>${h.description}</h3>
                                    <p>${h.bed} Bedrooms / ${h.bathrooms} Bathroom /
                                        / Kitchen</p>

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

        constructor(type, city, habitationId, userId) {
            this.habitationId = habitationId;
            this.type = type;
            this.city = city;
            this.userId = userId;
        }
    }
    const habitation = [];
    <c:forEach items="${habitations}" var="h">
    habitation.push(new habit("<c:out value="${h.city}"/>", "<c:out value="${h.type}"/>", "<c:out value="${h.habitationId}"/>", "<c:out value="${h.user.userId}"/>"));
    </c:forEach>
    const searchBar = document.getElementById("searchBar");
    searchBar.addEventListener('keyup', (e) => {
        const target = e.target.value;
        const filteredHabits = habitation.filter( habitation => {
            return habitation.type.toLowerCase().includes(target.toLowerCase()) || habitation.city.toLowerCase().includes(target.toLowerCase());
        });
        if (filteredHabits.length == 0) {
            document.getElementById("empty").style.display = '';
        } else {
            document.getElementById("empty").style.display = 'none';
        }

        for (let i = 0; i < habitation.length; i++) {
            if (!filteredHabits.includes(habitation[i])) {
                document.getElementById(habitation[i].gethabitationId()).style.display = 'none';

            } else {
                document.getElementById(habitation[i].gethabitationId()).style.display = '';
            }
        }
    })
    searchBar.dispatchEvent(new KeyboardEvent('keyup', {
        'key': 'a'
    }));
</script>