<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${user.username} Profile | HomeExchange</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
    <link rel="stylesheet" href="css/home.css">
    <link rel="stylesheet" href="css/table.css">
</head>
<body>
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
<c:if test="${!empty sessionScope.userId}">
<div class="container" style="height: 70vh">
    <h2 class="sub-title">Your Profile</h2>
    <div class="profile" style="display: flex;justify-content: center;align-items: center;">
        <form method="post" action="user/update" style="width: 50%;display: flex;flex-direction: column;  padding: 80px;box-shadow: 0 5px 10px rgba(0,0,0,0.05);">
            <div style="display: flex; flex-direction: column;">
                <div style="display: flex; flex-direction: column; width: 30%;padding-top: 15px;">
                    <label for="username">Username: </label>
                    <input name="username" type="text" id="username" value="${user.username}">
                </div>
                <div style="display: flex; flex-direction: row; justify-content: space-between;padding-top: 15px;">
                    <div style="display: flex;flex-direction: column;width: 25%;">
                        <label for="firstname">First name: </label>
                        <input name="firstname" type="text" id="firstname" value="${user.first_name}">
                    </div>
                    <div style="display: flex;flex-direction: column;width: 25%;">
                        <label for="lastname">Last name: </label>
                        <input name="lastname" type="text" id="lastname" value="${user.last_name}">
                    </div>
                    <div style="display: flex;flex-direction: column;width: 20%;">
                        <label for="Dob">Date of Birth: </label>
                        <input name="Dob" type="date" id="Dob" value="${user.dob}">
                    </div>
                </div>
                <div style="display: flex; flex-direction: row; justify-content: space-between;padding-top: 15px;">
                    <div style="display: flex;flex-direction: column;width: 50%;">
                        <label for="Email">Email: </label>
                        <input name="Email" type="text" id="Email" value="${user.email}">
                    </div>
                    <div style="display: flex;flex-direction: column;width: 25%;">
                        <label for="Gender">Gender</label>
                        <select id="Gender" name="Gender" value="${user.gender}"/>
                            <option value="Man">Man</option>
                            <option value="Woman">Woman</option>
                            </select>
                    </div>
                </div>
                <div style="display: flex; flex-direction: column; justify-content: space-between;padding-top: 15px;">
                    <label for="Address">Address: </label>
                    <input name="Address" type="text" id="Address" value="${user.address}">
                </div>
                <div style="display: flex; flex-direction: row; justify-content: space-between;padding-top: 15px;">
                    <div style="display: flex;flex-direction: column;width: 25%;">
                        <label for="City">City: </label>
                        <input name="City" type="text" id="City" value="${user.city}">
                    </div>
                    <div style="display: flex;flex-direction: column;width: 25%;">
                        <label for="Zip_Code">Zip Code: </label>
                        <input name="Zip_Code" type="text" id="Zip_Code" value="${user.zip_code}">
                    </div>
                    <div style="display: flex;flex-direction: column;width: 20%;">
                        <label for="Phone_Number">Phone number: </label>
                        <input name="Phone_Number" type="text" id="Phone_Number" value="${user.phone_number}">
                    </div>
                </div>
                <div style="display: flex; flex-direction: column; justify-content: space-between;padding-top: 15px;">
                    <label for="Description">Description: </label>
                    <input name="Description" type="text" id="Description" value="${user.description}">
                </div>
            </div>
            <div style="display: flex; align-items: center;justify-content: center;padding-top: 15px;">
                <button style="border-radius:10px; border:none;padding: 15px 30px; background: #008489; color: white;" type="submit" class="button">Send</button>
            </div>
        </form>
    </div>
    <hr class="line">
</div>


    <div class="container" style="min-height: 10vh">
        <h2 class="sub-title">Your Habitations</h2>
        <a href="/add-habitation" style="padding: 10px 30px; border-radius: 15px; background: #008489; color: white; text-decoration: none;">Add a new habitation</a>
        <table class="rwd-table">
            <tr>
                <th>Habitation</th>
                <th>Type</th>
                <th>Beds</th>
                <th>Rooms</th>
                <th>Bathrooms</th>
                <th>Description</th>
                <th>Address</th>
                <th>City</th>
                <th>Country</th>
                <th>Zip Code</th>
                <th>Services</th>
                <th>Constraints</th>
                <c:forEach var="equipment" items="${equipments}">
                    <th>${equipment.name}</th>
                </c:forEach>
                <th>Edit</th>
                <th>Reservations</th>
                <th>Delete</th>
            </tr>
            <c:forEach var="habits" items="${habits}" varStatus="loop">
                <tr>
                    <td>${habits.name}</td>
                    <td>${habits.type}</td>
                    <td>${habits.bed}</td>
                    <td>${habits.rooms}</td>
                    <td>${habits.bathrooms}</td>
                    <td>${habits.description}</td>
                    <td>${habits.address}</td>
                    <td>${habits.city}</td>
                    <td>${habits.country}</td>
                    <td>${habits.zip_code}</td>
                    <td>${habits.services}</td>
                    <td>${habits.constraints}</td>
                    <c:forEach var="equipment" items="${equipments}">
                        <td>
                            <c:forEach var="equipments" items="${equipmentsByHabitation[loop.index]}">
                                <p>
                                    <c:if test="${equipment.name == equipments.name}">TRUE</c:if>
                                </p>
                            </c:forEach>
                        </td>
                    </c:forEach>
                    <form action="my-habitations/${habits.habitationId}" method="post">
                        <td><input type="submit" value="Edit"></td>
                    </form>
                    <form action="reservation-request/${habits.habitationId}/from-users" method="get">
                        <td><input type="submit" value="Reservations"></td>
                    </form>
                    <form action="my-habitations/${habits.habitationId}/delete" method="GET">
                        <td><input type="submit" name="deleteHabitation" value="Delete"/></td>
                    </form>
                </tr>
            </c:forEach>
        </table>
    </div>
</c:if>
</body>