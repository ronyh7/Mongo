<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" xmlns="http://www.w3.org/1999/html">
<head>
<title>Insertar</title>
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/css/insertar.css" rel="stylesheet" >


</head>
<body>
    <#include "header.ftl">
    <div class="container">
        <div class="col-md-3"></div>
        <div class="col-md-5">
            <h1>Inserta un nuevo usuario</h1>
            <form th:action="@{/usuario/crear_usuario}" th:object="${usuario}" method="POST" >
                <label>Username:</label> <input name="username" type="text" required="true" /><br/>
                <label>Password:</label> <input name="password" type="password" required="true"/><br/>
                <label>Nombre:</label> <input name="nombre"  type="text" required="true"/><br/>
                <label>Apellido:</label> <input name="apellido"  type="text" required="true"/><br/>
                <button name="usuario" id="usuario" type="submit">Enviar</button>
            </form>
        </div>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>
</html>