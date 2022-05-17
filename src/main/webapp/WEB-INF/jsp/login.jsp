<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
<h1>Login</h1>
<form method="post" action="/login-verif">
    <label for="Fname">FName</label>
    <input name="Fname" id="Fname" type="text" placeholder="name">
    <br>
    <label for="LName">LName</label>
    <input name="LName" id="LName" type="text" placeholder="lastname">
    <br>
    <input type="submit" value="envoyer">
</form>

</body>