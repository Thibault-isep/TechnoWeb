<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Sign Up - CodeJava, barth</title>
    <link rel="stylesheet" href="css/login.css" />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</head>
<header></header>
<body>

<div class="formImage">

    <div id="imageLeft">
        <img src="/images/vu-anh-TiVPTYCG_3E-unsplash 11(1).png">
    </div>
    <div id="formulaire">
        <div>
            <h2>Connexion</h2>
            <h1></h1>
        </div>
        <form th:action="@{/process_register}" th:object="${user}"
              method="post">
            <div class="">
                <div>
                    <label for="Email">E-mail: </label>
                    <div>
                        <input id="Email" name="Email" placeholder="Email" type="email" th:field="*{email}" required />
                    </div>
                </div>
                <div>
                    <label for="Password">Mot de Passe: </label>
                    <div>
                        <input name="Password" id="Password" placeholder="Password" type="password" th:field="*{password}"
                               required minlength="6" maxlength="30"/>
                    </div>
                </div>
                <div>
                    <button type="submit" class="button">Sign Up</button>
                </div>
            </div>
        </form>
    </div>

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
</body>
</html>
