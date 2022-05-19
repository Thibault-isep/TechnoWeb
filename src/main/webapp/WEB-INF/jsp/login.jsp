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
<h2>${errorMsg}</h2>
<form method="post">
    <label for="Username">Username</label>
    <input name="Username" id="Username" type="text" placeholder="username">
    <br>
    <label for="Password">Password</label>
    <input name="Password" id="Password" type="text" placeholder="Password">
    <br>
    <input type="submit" value="envoyer">
</form>
</body>