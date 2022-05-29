<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Page d'informations de l'habitation ${habitation.name}</title>
    <link rel="stylesheet" href="../../../../css/admin.css">
</head>
<body>
<form method="post" action="update" enctype="multipart/form-data">
    <input name="habitationId" type="hidden" value="${habitation.habitationId}">

    <label for="name">Nom de l'habitation : </label>
    <input name="Name" type="text" id="name" value="${habitation.name}">
    <br>

    <label for="Type">Type d'habitation</label>
    <select id="Type" name="Type"/>
    <option value="Appartement">Appartement</option>
    <option value="House">Maison</option>
    <option value="Studio">Studio</option>
    <option value="Chambre">chambre</option>
    </select>
    <br>

    <label for="bed">Lits : </label>
    <input name="Bed" type="number" id="bed" value="${habitation.bed}">
    <br>

    <label for="rooms">Pièces : </label>
    <input name="Rooms" type="number" id="rooms" value="${habitation.rooms}">
    <br>

    <label for="bathrooms">Salles de bain : </label>
    <input name="Bathrooms" type="number" id="bathrooms" value="${habitation.bathrooms}">
    <br>

    <label for="description">Description : </label>
    <input name="Description" type="text" id="description" value="${habitation.description}">
    <br>

    <label for="address">Adresse : </label>
    <input name="Address" type="text" id="address" value="${habitation.address}">
    <br>

    <label for="city">Ville : </label>
    <input name="City" type="text" id="city" value="${habitation.city}">
    <br>


    <label for="country">pays : </label>
    <input name="Country" type="text" id="country" value="${habitation.country}">
    <br>

    <label for="Zip_Code">Code postal : </label>
    <input name="Zip_Code" type="text" id="Zip_Code" value="${habitation.zip_code}">
    <br>

    <label for="services">Services : </label>
    <input name="Services" type="text" id="services" value="${habitation.services}">
    <br>

    <label for="constraints">Contraintes : </label>
    <input name="Constraints" type="text" id="constraints" value="${habitation.constraints}">
    <br>

    <label for="Photos">Photos</label>
    <input type="file" id="Photos" name="Photos" accept="image/png, image/jpeg" multiple>

    <c:forEach var="equipment" items="${equipment}">
        <div class="other-input" style="display: flex;flex-direction: column;">
            <label for="${equipment.name}">${equipment.name}</label>
            <select name="equipments"/>
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
        </div>
    </c:forEach>
    <c:forEach var="reservationPeriod" items="${reservationPeriods}" varStatus="loop">
        <h2>Disponobilité de réservation ${loop.index + 1}</h2>
        <table>
            <tr>
                <th>Date of start</th>
                <th>Date of end</th>
            </tr>
            <c:choose>
                <c:when test="${reservationPeriod.validate == true}">
                    <tr>
                        <input type="hidden" value="${reservationPeriod.reservationPeriodId}"
                               name="reservationPeriodId">
                        <td><input type="date" value="${reservationPeriod.start}" name="dateOfStart"
                                   readonly="readonly"></td>
                        <td><input type="date" value="${reservationPeriod.end}" name="dateOfEnd" readonly="readonly">
                        </td>
                        <td>This period is on reservation</td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <tr>
                        <input type="hidden" value="${reservationPeriod.reservationPeriodId}"
                               name="reservationPeriodId">
                        <td><input type="date" value="${reservationPeriod.start}" name="dateOfStart"></td>
                        <td><input type="date" value="${reservationPeriod.end}" name="dateOfEnd"></td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </table>
    </c:forEach>
    <div class="gallery">
        <c:forEach items="${photos}" var="photo" varStatus="loop">
            <div class="gallery-img-${loop.index + 1}">
                <img src="../../../${photos[loop.index]}">
                <a href="/admin/user/${userId}/habitations/delete-image/${loop.index}">Delete this image</a>
            </div>
        </c:forEach>
    </div>
    <button type="submit" class="button">Envoyer</button>
</form>

<form action="/admin/${userId}/habitations/delete" method="GET">
    <h5>Delete</h5>
    <input type="submit" name="deleteHabitation" value="Delete the habitation"/>
</form>
</body>