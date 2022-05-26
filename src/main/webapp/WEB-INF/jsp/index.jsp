<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
    <link rel="stylesheet" href="css/home.css">
    <script type="text/javascript">
        const img = document.querySelector('.profile .img');
        const menu = document.querySelector('.profile_dropdown');
        img.addEventListener('click', function() {
            menu.classList.toggle('open');
        })
    </script>
</head>
<body>
<div class="header" style="background-image: linear-gradient(rgba(0,0,0,0.3),rgba(0,0,0,0.3)),url('images/header.png');">
    <nav class="white">
        <h2 class="logo">HomeExchange</h2>
        <ul class="nav-links">
            <li><a href="/habitation/search">Accommodations</a></li>
            <li><a href="#">Become a Host</a></li>
        </ul>
        <c:choose>
            <c:when test="${user.userId != null}">
                <label for="profile2" class="profile-dropdown">
                    <input type="checkbox" id="profile2">
                    <p>${user.username}</p>
                    <label for="profile2"><i class="fa-solid fa-bars"></i></label>
                    <ul>
                        <li><a href="/messaging"><i class="fa-solid fa-message"></i>Messages</a></li>
                        <li><a href=/infoscompte><i class="fa-solid fa-user"></i>Account</a></li>
                        <li><a href="#"><i class="fa-solid fa-gear"></i>Settings</a></li>
                        <li><a href="/logout"><i class="fa-solid fa-arrow-right-from-bracket"></i>Logout</a></li>
                    </ul>
                </label>
            </c:when>
            <c:otherwise>
                <ul class="nav-links">
                    <li><a href="/login" class="login-btn">Login</a></li>
                    <li><a href="/register" class="register-btn">Register Now</a></li>
                </ul>
            </c:otherwise>
        </c:choose>
    </nav>
    <div class="container">
        <h1>Find your stay with HomeExchange</h1>
        <div class="search-bar">
            <form action="/habitation/search" method="post">
                <div class="location-input">
                    <label>Location</label>
                    <input type="text" name="habitationSearch" placeholder="Where do you want to go?">
                </div>
                <div class="other-input">
                    <label>Check In</label>
                    <input type="date" placeholder="Add Date">
                </div>
                <div class="other-input">
                    <label>Check Out</label>
                    <input type="date" placeholder="Add Date">
                </div>
                <div class="other-input">
                    <label>Travelers</label>
                    <input type="number" placeholder="Add Travelers">
                </div>
                <button type="submit" name="action" value="search"><img src="icons/search.png"></button>
            </form>
        </div>
    </div>
</div>
<div class="container">
    <h2 class="sub-title">Discover the proposed destinations</h2>
    <div class="trending">
        <form action="/habitation/search" method="post">
            <input type="hidden" name="habitationSearch" value="paris">
            <div>
                <button type="submit" name="action" value="search" style="background: none;border: none;cursor: pointer;">
                    <img src="images/paris.png">
                </button>
                <h3>Paris</h3>
            </div>
        </form>
        <form action="/habitation/search" method="post">
            <input type="hidden" name="habitationSearch" value="bordeaux">
            <div>
                <button type="submit" name="action" value="search" style="background: none;border: none;cursor: pointer;">
                    <img src="images/bordeaux.png">
                </button>
                <h3>Bordeaux</h3>
            </div>
        </form>
        <form action="/habitation/search" method="post">
            <input type="hidden" name="habitationSearch" value="lyon">
            <div>
                <button type="submit" name="action" value="search" style="background: none;border: none;cursor: pointer;">
                    <img src="images/lyon.png">
                </button>
                <h3>Lyon</h3>
            </div>
        </form>
        <form action="/habitation/search" method="post">
            <input type="hidden" name="habitationSearch" value="marseille">
            <div>
                <button type="submit" name="action" value="search" style="background: none;border: none;cursor: pointer;">
                    <img src="images/marseille.jpg">
                </button>
                <h3>Marseille</h3>
            </div>
        </form>
    </div>
    <h2 class="sub-title">Go on holiday responsibly</h2>
    <div class="about">
        <img src="images/image_1.png">
        <div class="paragraphe">
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer vel egestas turpis. Pellentesque tellus elit, vehicula vel purus quis, viverra vestibulum lectus. Aenean dolor magna, rutrum id ex sit amet, scelerisque accumsan massa. Praesent vestibulum erat ac nulla dictum, vitae sollicitudin nibh tempus. Integer fringilla dolor justo, vitae rutrum sem vulputate vitae. Maecenas eget consequat enim. Curabitur volutpat eget nibh sed ornare. Praesent metus felis, mollis vitae aliquam vel, bibendum sed tortor. Phasellus pharetra erat risus, sed rutrum eros porta sit amet. Maecenas auctor leo id auctor sagittis. Nunc sodales mauris quis posuere porttitor. Curabitur euismod viverra erat, at faucibus turpis lobortis sit amet. In tincidunt porttitor vehicula.</p>
            <p>Sed posuere sem euismod malesuada suscipit. Etiam ac quam vitae sem pulvinar ornare in tincidunt enim. Sed ex lorem, ullamcorper sit amet nisi sed, laoreet pretium nulla. Suspendisse sed dictum orci, quis tincidunt ante. Proin auctor iaculis odio, non dignissim dolor sagittis ut. Vestibulum mollis sed turpis ac volutpat. Donec consectetur dolor ullamcorper, volutpat ex eu, ornare velit. Quisque pulvinar porttitor massa vel efficitur. Proin commodo cursus dui, sit amet dignissim est facilisis quis. Fusce iaculis diam congue tellus blandit interdum. Nullam feugiat ac leo quis tempus. Donec tincidunt lacinia ante, eget lobortis nulla sagittis ac. Nullam accumsan justo metus, quis tincidunt ipsum interdum sed. Quisque a commodo justo, ut imperdiet neque. In ultricies ipsum facilisis faucibus consectetur. Aenean sit amet eleifend diam.</p>
        </div>
    </div>
    <div class="cta" style="background-image: linear-gradient(to right, #3f2321, transparent), url('images/contact.png');">
        <h3>Questions about accommodation?</h3>
        <p>Do not hesitate to contact us !</p>
        <a href="#" class="cta-btn">Contact</a>
    </div>
</div>
</body>
</html>