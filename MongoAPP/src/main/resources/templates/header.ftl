<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css" rel="stylesheet">

    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>BLOG</title>

</head>
<body>
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <a href="/" class="navbar-brand">BLOG Here!</a>
            </div>
            <div>
                <ul class="nav navbar-nav">
                    <li><a href="/usuario">Usuarios</a></li>
                    <li><a href="/palabra">Palabras Frecuentes</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <#if usuario.username=="">
                        <li><a href="/login">Login</a></li>
                    <#else>
                        <li><a href="/login">Hola ${usuario.username}!</a></li>
                    </#if>
                </ul>
            </div>
        </div>
    </nav>
</body>