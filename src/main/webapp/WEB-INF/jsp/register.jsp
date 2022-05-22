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
<form method="post">
    <label for="FName">FName</label>
    <input name="FName" id="FName" type="text" placeholder="name" required>
    <br>
    <label for="LName">LName</label>
    <input name="LName" id="LName" type="text" placeholder="lastname" required>
    <br>
    <label for="Password">LName</label>
    <input name="Password" id="Password" type="text" placeholder="Password" required>
    <br>
    <input type="submit" value="envoyer">
</form>

</body>