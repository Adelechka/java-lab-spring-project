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

        .error {
            color: red;
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
        <h3><@spring.message 'sign_up_page.registration.title'/></h3>
        <p class="lead"><@spring.message 'sign_up_page.registration.subtitle'/></p>
    </div>

    <@spring.bind "registrationFormDto"/>
    <#if namesErrorMessage ??>
        <p class="text-center text-danger">  ${namesErrorMessage} </p>
    </#if>
    <form class="registration text-center" method="post" action="/registration">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <div class="form-group">
            <div class="text-right">
                <div class="form-group row">
                    <label for="firstName" class="col-sm-2 col-form-label text-reg"><@spring.message 'sign_up_page.registration.form.name'/></label>
                    <div class="col-sm-8">
                        <#assign namePlaceholder><@spring.message 'sign_up_page.registration.form.name.placeholder'/></#assign>
                        <@spring.formInput "registrationFormDto.firstName" "class='form-control' placeholder='" + namePlaceholder + "' id='firstName'"/>
                        <@spring.showErrors "<br>" "text-danger"/>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="lastName" class="col-sm-2 col-form-label text-reg"><@spring.message 'sign_up_page.registration.form.lastname'/></label>
                    <div class="col-sm-8">
                        <#assign lastNamePlaceholder><@spring.message 'sign_up_page.registration.form.lastname.placeholder'/></#assign>
                        <@spring.formInput "registrationFormDto.lastName" "class='form-control' placeholder='" + lastNamePlaceholder + "' id='lastName'"/>
                        <@spring.showErrors "<br>" "text-danger"/>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="age" class="col-sm-2 col-form-label text-reg"><@spring.message 'sign_up_page.registration.form.age'/></label>
                    <div class="col-sm-8">
                        <#assign agePlaceholder><@spring.message 'sign_up_page.registration.form.age.placeholder'/></#assign>
                        <@spring.formInput "registrationFormDto.age" "class='form-control' placeholder='" + agePlaceholder + "' id='age'"/>
                        <@spring.showErrors "<br>" "text-danger"/>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="login" class="col-sm-2 col-form-label text-reg"><@spring.message 'sign_up_page.registration.form.login'/></label>
                    <div class="col-sm-8">
                        <#assign loginPlaceholder><@spring.message 'sign_up_page.registration.form.login.placeholder'/></#assign>
                        <@spring.formInput "registrationFormDto.login" "class='form-control' placeholder='" + loginPlaceholder + "' id='login'"/>
                        <@spring.showErrors "<br>" "text-danger"/>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="lastName" class="col-sm-2 col-form-label text-reg"><@spring.message 'sign_up_page.registration.form.email'/></label>
                    <div class="col-sm-8">
                        <#assign emailPlaceholder><@spring.message 'sign_up_page.registration.form.email.placeholder'/></#assign>
                        <@spring.formInput "registrationFormDto.email" "class='form-control' placeholder='" + emailPlaceholder + "' id='email'"/>
                        <@spring.showErrors "<br>" "text-danger"/>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="hashPassword" class="col-sm-2 col-form-label text-reg "><@spring.message 'sign_up_page.registration.form.password'/></label>
                    <div class="col-sm-8">
                        <#assign passwordPlaceholder><@spring.message 'sign_up_page.registration.form.password.placeholder'/></#assign>
                        <@spring.formPasswordInput "registrationFormDto.hashPassword" "class='form-control' placeholder='" + passwordPlaceholder + "' id='hashPassword' type='password'"/>
                        <@spring.showErrors "<br>" "text-danger"/>
                    </div>
                </div>
            </div>
            <input type="submit" class="btn btn-success" value=<@spring.message 'sign_up_page.registration.form.submit'/>>
        </div>
        <a href="/login"><@spring.message 'sign_up_page.registration.form.have_account'/></a>
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
