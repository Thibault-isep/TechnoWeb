<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Homepage</title>
    <link rel="stylesheet" href="">
</head>
<body>
<form method="post">
    <label for="Type">Type d'habitation (Appartement, maison)</label>
    <input name="Type" id="Type" type="text" placeholder="Type" required>
    <br>
    <label for="Address">Adresse</label>
    <input name="Address" id="Address" type="text" placeholder="Address" required>
    <br>
    <input type="submit" value="envoyer" required>
</form>
</body>