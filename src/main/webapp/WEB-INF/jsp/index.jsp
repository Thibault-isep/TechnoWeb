<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Homepage</title>
    <link rel="stylesheet" href="">
</head>
<body>
<p>Bienvenue ! ${user.username}</p>
<c:if test="${!empty sessionScope.userId}">
    <a href="infoscompte">infos de compte</a>
</c:if>
<br>
<c:if test="${empty sessionScope.userId}">
    <a href="login">login</a>
</c:if>
<br>
<c:if test="${empty sessionScope.userId}">
    <a href="register">register</a>
</c:if>
<br>
<c:if test="${!empty sessionScope.userId}">
    <a href="logout">logout</a>
</c:if>


<form action="/habitation/search" method="post">
    <h1>Recherche :</h1>
    <input type="text" name="habitationSearch" size="20" default="Where do you want to go?"/>
    <br>
    <label for="rooms">rooms</label>
    <input type="range" id="rooms" name="rooms" min="1" max="7" value="7" step="1"
           oninput="document.getElementById('AfficheRange').textContent=value"/>
    <span id="AfficheRange">7</span>.
    <input type="submit" name="action" value="search"/>
</form>

</body> 
