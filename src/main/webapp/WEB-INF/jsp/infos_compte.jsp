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
<c:if test="$ !empty sessionScope.user" >
    <p>Vous êtes ${sessionScope.user.email} ${sessionScope.user.first_name} ${sessionScope.user.username}</p>
</c:if>
</body>