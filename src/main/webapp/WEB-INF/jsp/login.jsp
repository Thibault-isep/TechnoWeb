<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Log In | HomeExchange</title>
    <link rel="stylesheet" href="css/login.css" />
    <link rel="stylesheet" href="css/home.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
</head>
<body>
<nav class="white">
    <a href="/"><h2 class="logo">HomeExchange</h2></a>
    <ul class="nav-links">
        <li><a href="/habitation/search">Accommodations</a></li>
        <li><a href="/add-habitation">Become a Host</a></li>
    </ul>
    <ul class="connexion-links">
        <li><a href="/login" class="login-btn">Login</a></li>
        <li><a href="/register" class="register-btn">Register Now</a></li>
    </ul>
</nav>
<div class="container">
    <h2 class="sub-title"></h2>
    <div class="formulaire-login">
        <div class="left-img" style="background-image: url('images/connexion.png');">

        </div>
        <div class="right-form">
            <div style="text-align: center; height: 35%;">
                <h1 class="sub-title" style="font-weight: 800; font-size: 6.2vw; color: white;">Connexion</h1>
                <h2>${errorMsg}</h2>
            </div>

            <div style="height: 75%; display: flex; align-items: center;justify-content: center">
                <form th:action="@{/process_register}" th:object="${user}"
                      method="post">
                    <div class="">
                        <div>
                            <div class="left-inner-addon input-container">
                                <i class="fa-solid fa-at"></i>
                                <input type="email"
                                       id="Email"
                                       name="Email"
                                       class="form-control"
                                       placeholder="Email"
                                       th:field="*{email}"
                                       style="padding: 10px;width: 750px"
                                       required
                                />
                            </div>
                        </div>
                        <div>
                            <div class="left-inner-addon input-container">
                                <i class="fa-solid fa-lock"></i>
                                <input name="Password"
                                       id="Password"
                                       class="form-control"
                                       placeholder="Password"
                                       type="password"
                                       th:field="*{password}"
                                       style="padding: 10px;width: 750px"
                                       required
                                       minlength="6"
                                       maxlength="30"
                                />
                            </div>
                        </div>
                        <div style="display: flex;flex-direction: row;align-items: center;">
                            <input type="checkbox" style="width:20px;height:20px;"><p style="color: white;margin-left: 10px;">Remember me</p>
                        </div>
                        <div>
                            <button type="submit" class="button" style="margin-top:10px;padding: 10px;width: 750px;background: white;border: none;">Sign Up</button>
                        </div>
                        <div style="text-align: center; margin-top: 40px;">
                            <h2 style="color: white">No account yet? <a href="/register" style="color: white;">Register</a></h2>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>