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
    <c: foreach var="user" items="user" >
        <p>Bienvenue ! ${user.username}</p>
    </c:>
    <a href="infos_compte">infos de compte</a>
    <a href="login">login</a><a href="logout">logout</a>
</body>