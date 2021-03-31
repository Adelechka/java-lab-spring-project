<#ftl encoding="UTF-8"/>
<!doctype html>
<html lang="en">
<#import "spring.ftl" as spring />
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
            integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
            integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
            crossorigin="anonymous"></script>

    <meta charset="utf-8">
    <title>Registration</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.5/examples/checkout/">

    <!-- Bootstrap core CSS -->
    <link href="../assets/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>

    <link rel="stylesheet" href="/resources/style/style.css">
    <link rel="shortcut icon" href="/resources/img/icon.png" type="image/png">
</head>

<body class="bg-light">
<header>
    <nav class="navbar navbar-dark bg-dark justify-content-between">
        <a class="navbar-brand">Greennit</a>

        <div class="form-inline">
            <a href="?lang=ru" class="btn btn-light btn-lg" role="button" aria-pressed="true">RU</a>
            <a href="?lang=en" class="btn btn-light btn-lg" role="button" aria-pressed="true">EN</a>
        </div>
    </nav>
</header>
<div class="container">
    <div class="py-5 text-center">
        <!--
        <img class="d-block mx-auto mb-4" src="/resources/img/page_icon.png" width="85" height="85" alt="logo">
        -->
        <h3><@spring.message 'sign_in_page.login.title'/></h3>
    </div>

    <form class="registration text-center" method="post" action="/login">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <div class="form-group">
            <div class="form-group row">
                <label for="login"
                       class="col-sm-2 col-form-label text-reg"><@spring.message 'sign_in_page.login.form.login'/></label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" name="login" id="login"
                           placeholder=<@spring.message 'sign_in_page.login.form.login.placeholder'/>>
                </div>
            </div>

            <div class="form-group row">
                <label for="hashPassword"
                       class="col-sm-2 col-form-label text-reg"><@spring.message 'sign_in_page.login.form.password'/></label>
                <div class="col-sm-8">
                    <input type="password" class="form-control" name="hashPassword" id="hashPassword"
                           placeholder=<@spring.message 'sign_in_page.login.form.password.placeholder'/>>
                </div>
            </div>

            <div class="form-check">
                <label class="form-check-label" for="remember-me">
                    <input class="form-check-input" type="checkbox" name="remember-me"><@spring.message 'sign_in_page.login.form.remember-me'/>
                </label>
            </div>
        </div>
        <input type="submit" class="btn btn-success" value=<@spring.message 'sign_in_page.login.form.submit'/>>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="../assets/js/vendor/jquery.slim.min.js"><\/script>')</script>
<script src="../assets/dist/js/bootstrap.bundle.min.js"></script>
<script src="../checkout/form-validation.js"></script>
</body>
</html>
