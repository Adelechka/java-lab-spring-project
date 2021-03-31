<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Users</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <td>Имя Фамилия</td>
        <td>Login</td>
        <td></td>
    </tr>
    </thead>
    <tbody>
    <#list users as user>

        <tr>
            <td>${user.firstName} ${user.lastName}</td>
            <td>${user.login}</td>
            <td>${user.state}</td>
        </tr>


    </#list>
    </tbody>
</table>

<form method="post" action="/banAll">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <input type="submit" value="BAN">
</form>


</body>
</html>
