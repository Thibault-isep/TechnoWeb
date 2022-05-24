<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Sign Up - CodeJava, barth</title>
    <link rel="stylesheet" href="css/home.css">
    <link rel="stylesheet" href="css/login.css" />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</head>
<header></header>
<body>
<nav class="white">
    <a href="/"><h2 class="logo">HomeExchange</h2></a>
    <ul class="nav-links">
        <li><a class="active" href="">Accommodations</a></li>
        <li><a href="#">Become a Host</a></li>
    </ul>
    <c:choose>
        <c:when test="${user.userId != null}">
            <ul class="nav-links">
                <li>Thib</li>
            </ul>
        </c:when>
        <c:otherwise>
            <ul class="connexion-links">
                <li><a href="/login" class="login-btn">Login</a></li>
                <li><a href="/register" class="register-btn">Register Now</a></li>
            </ul>
        </c:otherwise>
    </c:choose>
</nav>

<div class="formImage">
    <div class="formImage">

        <div id="imageLeft">
            <img src="images/vu-anh-TiVPTYCG_3E-unsplash_111.png">
        </div>
        <div id="formulaire">
            <div>
                <h2>Connexion</h2>
                <h1>${errorMsg}</h1>
            </div>
            <form th:action="@{/process_register}" th:object="${user}"
                  method="post">
                <div class="">
                    <div>
                        <label>E-mail: </label>
                        <div>
                            <input type="email" th:field="*{email}" name="Email" required />
                        </div>
                    </div>
                    <div>
                        <label>Mot de Passe: </label>
                        <div>
                            <input type="password" th:field="*{password}"
                                   required minlength="6" name="Password"/>
                        </div>
                    </div>
                    <div>
                        <button type="submit" class="button">Sign Up</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
</body>
</html>
