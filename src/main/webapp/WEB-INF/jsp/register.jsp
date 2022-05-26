<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <link rel="stylesheet" href="css/login.css" />
    <link rel="stylesheet" href="css/home.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
</head>
<body>
<nav class="white">
    <a href="/"><h2 class="logo">HomeExchange</h2></a>
    <ul class="nav-links">
        <li><a href="/habitation/search">Accommodations</a></li>
        <li><a href="#">Become a Host</a></li>
    </ul>
    <ul class="connexion-links">
        <li><a href="/login" class="login-btn">Login</a></li>
        <li><a href="/register" class="register-btn">Register Now</a></li>
    </ul>
</nav>
<div class="container">
    <h2 class="sub-title"></h2>
    <h2>${errorMsg}</h2>
    <div class="formulaire-login">
        <div class="left-img" style="background-image: url('images/inscription.png');">

        </div>
        <div class="right-form">
            <div style="text-align: center; height: 35%;">
                <h1 class="sub-title" style="font-weight: 800; font-size: 6.2vw; color: white;">Connexion</h1>
            </div>

            <div style="height: 75%; display: flex; align-items: center;justify-content: center">
                <form method="post">
                    <div class="form-items">
                        <div class="form-item">
                            <label for="FName">Prénom</label>
                            <input name="FName" id="FName" type="text" placeholder="name" required>
                        </div>
                        <div class="form-item">
                            <label for="LName">Nom de famille</label>
                            <input name="LName" id="LName" type="text" placeholder="lastname" required>
                        </div>
                    </div>
                    <div class="form-items">
                        <div class="form-item">
                            <label for="Password">Mot de passe</label>
                            <input name="Password" id="Password" type="text" placeholder="Password" required>
                        </div>
                        <div class="form-item">
                            <label for="Email">Email</label>
                            <input name="Email" id="Email" type="text" placeholder="Email" required>
                        </div>
                    </div>
                    <div class="form-items">
                        <div class="form-item">
                            <label for="Username">Pseudo</label>
                            <input name="Username" id="Username" type="text" placeholder="Username" required>
                        </div>
                        <div class="form-item">
                            <label for="Dob">Date de naissance</label>
                            <input name="Dob" id="Dob" type="date" placeholder="Dob" required>
                        </div>
                    </div>
                    <div class="form-items">
                        <div class="form-item">
                            <label for="Gender">Gender</label>
                            <select id="Gender" name="Gender"/>
                            <option value="Man">Homme</option>
                            <option value="Woman">Femme</option>
                            </select>
                        </div>
                        <div class="form-item">
                            <label for="Address">Adresse</label>
                            <input name="Address" id="Address" type="text" placeholder="Address" required>
                        </div>
                    </div>
                    <div class="form-items">
                        <div class="form-item">
                            <label for="City">Ville</label>
                            <input name="City" id="City" type="text" placeholder="City" required>
                        </div>
                        <div class="form-item">
                            <label for="Zip_Code">Date de naissance</label>
                            <input name="Zip_Code" id="Zip_Code" type="text" placeholder="Zip_Code" required>
                        </div>
                    </div>
                    <div class="form-items">
                        <div class="form-item">
                            <label for="Phone_Number">Numéro de téléphone</label>
                            <input name="Phone_Number" id="Phone_Number" type="text" placeholder="Phone_Number" required>
                        </div>
                        <div class="form-item">
                            <label for="Description">Description</label>
                            <input name="Description" id="Description" type="text" placeholder="Description" required>
                        </div>
                    </div>
                    <input type="submit" value="envoyer">
                </form>
            </div>
        </div>
    </div>
</div>
</body>