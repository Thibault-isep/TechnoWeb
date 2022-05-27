<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Liste des users</title>
    <link rel="stylesheet" href="">
</head>
<body>
<table>
    <thead>
    <tr>
        <th>Users</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>UserId</td>
        <td>Username</td>
        <td>Email</td>
    </tr>
    <c:forEach items="${users}" var="user">
        <c:if test="${user.roles != 'ROLE_ADMIN'}">
            <tr>
                <td>${user.userId}</td>
                <td>${user.username}</td>
                <td>${user.email}</td>
                <td><a href="/admin/user/${user.userId}/edit">Edit user</a></td>
                <td><a href="/admin/user/${user.userId}/delete">Delete user</a></td>
            </tr>
        </c:if>
    </c:forEach>
    </tbody>
</table>
</body>