<!DOCTYPE html>
<html x lang="en" xmlns:th="http://www.thymeleaf.org" xmlns="http://www.w3.org/1999/html">

  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Sign in</title>

    <!-- Bootstrap core CSS -->
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="/static/css/signin.css" rel="stylesheet">


  </head>

  <body>
  <#include "header.ftl">
  <div class="col-md-3"></div>
  <div class="container col-md-4" align="center">
      <form class="form-signin" role="form" action="/login" method="post">
        <h2 class="form-signin-heading">The Blog</h2>
        <label for="inputEmail" class="sr-only">Nombre de usuario</label>
        <input type="text" name ="username" id="username" class="form-control" placeholder="Username" required autofocus>
        <label for="inputPassword" class="sr-only">Contraseña</label>
        <input type="password" name="password" id="password" class="form-control" placeholder="Password" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Iniciar Sesion</button>
      </form>

    </div> <!-- /container -->

  </body>
</html>
