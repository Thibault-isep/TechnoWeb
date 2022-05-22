<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Homepage</title>
    <link rel="stylesheet" href="">
</head>
<body>
<form method="post" action="">
    <label for="Type">Type d'habitation (Appartement, maison)</label>
    <input name="Type" id="Type" type="text" placeholder="Type" required>
    <br>
    <label for="Address">Adresse</label>
    <input name="Address" id="Address" type="text" placeholder="Address" required>
    <br>
    <c:forEach var="equipment" items="${equipment}">
        <label for="${equipment.name}">${equipment.name}</label>
        <select name="equipments"/>
        <option value="NON">NON</option>
        <option value="OUI">OUI</option>
        </select>
    </c:forEach>

    <input type="submit" value="envoyer">
</form>
</body>
