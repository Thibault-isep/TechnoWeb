<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Homepage</title>
    <link rel="stylesheet" href="">
</head>
<body>
    <p>Bienvenue ! ${user.username}</p>
    <c:if test="${user.username == null}">
        <p>${sessionScope.userId}</p>
        <a href="collectInfoCompte">infos de compte</a>
    </c:if>
    <a href="login">login</a>
    <a href="logout">logout</a>
</body>