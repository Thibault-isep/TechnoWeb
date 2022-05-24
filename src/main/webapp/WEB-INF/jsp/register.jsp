<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
<h1>Register</h1>
<h2>${errorMsg}</h2>
<form method="post">
    <label for="FName">Prénom</label>
    <input name="FName" id="FName" type="text" placeholder="name" required>
    <br>
    <label for="LName">Nom de famille</label>
    <input name="LName" id="LName" type="text" placeholder="lastname" required>
    <br>
    <label for="Password">Mot de passe</label>
    <input name="Password" id="Password" type="text" placeholder="Password" required>
    <br>
    <label for="Email">Email</label>
    <input name="Email" id="Email" type="text" placeholder="Email" required>
    <br>
    <label for="Username">Pseudo</label>
    <input name="Username" id="Username" type="text" placeholder="Username" required>
    <br>
    <label for="Dob">Date de naissance</label>
    <input name="Dob" id="Dob" type="date" placeholder="Dob" required>
    <br>
    <label for="Gender">Gender</label>
    <select id="Gender" name="Gender"/>
    <option value="Man">Homme</option>
    <option value="Woman">Femme</option>
    </select>
    <br>
    <label for="Address">Adresse</label>
    <input name="Address" id="Address" type="text" placeholder="Address" required>
    <br>
    <label for="City">Ville</label>
    <input name="City" id="City" type="text" placeholder="City" required>
    <br>
    <label for="Zip_Code">Date de naissance</label>
    <input name="Zip_Code" id="Zip_Code" type="text" placeholder="Zip_Code" required>
    <br>
    <label for="Phone_Number">Numéro de téléphone</label>
    <input name="Phone_Number" id="Phone_Number" type="text" placeholder="Phone_Number" required>
    <br>
    <label for="Description">Description</label>
    <input name="Description" id="Description" type="text" placeholder="Description" required>
    <br>
    <input type="submit" value="envoyer">
</form>

</body>