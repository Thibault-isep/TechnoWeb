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
<p>Bienvenue ! ${user.username}</p>
<c:if test="${ !empty sessionScope }">
    <a href="infoscompte">infos de compte</a>
</c:if>
<br>
<a href="login">login</a>
<br>
<a href="logout">logout</a>


<form action="/habitation/search" method="post">
    <table>
        <tr>
            <td>Recherche :</td>
            <td><input type="text" name="habitationSearch" size="20" default="Where do you want to go?"/></td>
            <td><input type="submit" name="action" value="search"/></td>
        </tr>
    </table>
</form>

</body>