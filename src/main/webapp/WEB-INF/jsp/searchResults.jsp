<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Search Results</title>
</head>
<body>
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
<div>
    <c:choose>
        <c:when test="${result.size() == 0}">
            No result found for you search
            <br/>
        </c:when>
        <c:otherwise>
            <table>
                <tr>
                    <th>ID Habitation</th>
                    <th>Salle de bain</th>
                    <th>Ville</th>
                </tr>
                <c:forEach items="${result}" var="h">
                <tr>
                    <td>${h.habitationId}</td>
                    <td>${h.bathrooms}</td>
                    <td>${h.city}</td>
                    <td><form action="/habitation/${h.habitationId}" method="POST"><input type="submit" name="seeHabitation" value="See the habitation"/></form></td>
                </tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>