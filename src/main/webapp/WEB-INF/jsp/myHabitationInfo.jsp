<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${habitation.name} informations</title>
</head>
<body>
<div>
    <form action="/updatehabitation" method="POST">
        <input type="hidden" value="${habitation.habitationId}" name="habitationId">
        <label for="Type">Type d'habitation</label>
        <select id="Type" name="Type" value="${habitation.type}"/>
        <option value="Appartement">Appartement</option>
        <option value="House">Maison</option>
        <option value="Studio">Studio</option>
        <option value="Chambre">chambre</option>
        </select>
        <br>

        <label for="Name">Nom de l'habitation</label>
        <input name="Name" id="Name" type="text" placeholder="Name" value="${habitation.name}" required>
        <br>

        <label for="Address">Adresse</label>
        <input name="Address" id="Address" type="text" placeholder="Address" value="${habitation.address}" required>
        <br>
        <label for="Country">Pays</label>
        <input name="Country" id="Country" type="text" placeholder="Country" value="${habitation.country}" required>
        <br>
        <label for="Zip_Code">Code postal</label>
        <input name="Zip_Code" id="Zip_Code" type="text" placeholder="Zip_Code" value="${habitation.zip_code}" required>
        <br>
        <label for="City">Ville</label>
        <input name="City" id="City" type="text" placeholder="City" value="${habitation.city}" required>
        <br>
        <label for="Rooms">Nombre de pièces</label>
        <input name="Rooms" id="Rooms" type="number" placeholder="Rooms" value="${habitation.rooms}" required>
        <br>
        <label for="Bed">Lits</label>
        <input name="Bed" id="Bed" type="number" placeholder="Bed" value="${habitation.bed}" required>
        <br>
        <label for="Bathrooms">Salle de bain</label>
        <input name="Bathrooms" id="Bathrooms" type="number" placeholder="Bathrooms" value="${habitation.bathrooms}"
               required>
        <br>
        <label for="Description">Description</label>
        <input name="Description" id="Description" type="text" placeholder="Description"
               value="${habitation.description}" required>
        <br>
        <label for="Services">Services</label>
        <input name="Services" id="Services" type="text" placeholder="Services" value="${habitation.services}" required>
        <br>
        <label for="Constraints">Contraintes</label>
        <input name="Constraints" id="Constraints" type="text" placeholder="Constraints"
               value="${habitation.constraints}" required>
        <br>

        <c:forEach var="equipment" items="${equipment}">
            <label for="equipments">${equipment.name}</label>
            <select name="equipments" id="equipments">
                <c:choose>
                    <c:when test="${fn:contains(habitationsequipment, equipment)}">
                        <option value="OUI">OUI</option>
                        <option value="NON">NON</option>
                    </c:when>
                    <c:otherwise>
                        <option value="NON">NON</option>
                        <option value="OUI">OUI</option>
                    </c:otherwise>
                </c:choose>
            </select>
            <br>
        </c:forEach>

        <input type="submit" value="envoyer">
    </form>
</div>
</body>
</html>