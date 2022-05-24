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
    <label for="Type">Type d'habitation</label>
    <select id="Type" name="Type"/>
    <option value="Appartement">Appartement</option>
    <option value="House">Maison</option>
    <option value="Studio">Studio</option>
    <option value="Chambre">chambre</option>
    </select>
    <br>

    <label for="Address">Adresse</label>
    <input name="Address" id="Address" type="text" placeholder="Address" required>
    <br>
    <label for="Country">Pays</label>
    <input name="Country" id="Country" type="text" placeholder="Country" required>
    <br>
    <label for="Zip_Code">Code postal</label>
    <input name="Zip_Code" id="Zip_Code" type="text" placeholder="Zip_Code" required>
    <br>
    <label for="City">Ville</label>
    <input name="City" id="City" type="text" placeholder="City" required>
    <br>
    <label for="Rooms">Nombre de pièces</label>
    <input name="Rooms" id="Rooms" type="number" placeholder="Rooms" required>
    <br>
    <label for="Bed">Lits</label>
    <input name="Bed" id="Bed" type="number" placeholder="Bed" required>
    <br>
    <label for="Bathrooms">Salle de bain</label>
    <input name="Bathrooms" id="Bathrooms" type="number" placeholder="Bathrooms" required>
    <br>
    <label for="Description">Description</label>
    <input name="Description" id="Description" type="text" placeholder="Description" required>
    <br>
    <label for="Services">Services</label>
    <input name="Services" id="Services" type="text" placeholder="Services" required>
    <br>
    <label for="Constraints">Contraintes</label>
    <input name="Constraints" id="Constraints" type="text" placeholder="Constraints" required>
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
