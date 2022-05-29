<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Page du profil de ${user.username}</title>
    <link rel="stylesheet" href="">
</head>
<body>
<h1>Bonjour ${user.username}</h1>
<a href="/admin/user-list">Liste des utilisateurs</a>
</body>