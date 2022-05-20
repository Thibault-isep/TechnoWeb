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
<div>
    <table>
        <tr>
            <th>ID Habitation</th>
            <th>Salle de bain</th>
            <th>Ville</th>
        </tr>
        <tr>
            <td>${habitation.idHabitation}</td>
            <td>${habitation.bathrooms}</td>
            <td>${habitation.city}</td>
        </tr>
    </table>
</div>
</body>
</html>