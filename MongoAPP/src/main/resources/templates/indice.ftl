<!DOCTYPE html>
<html x lang="en">

<head>
    <link href="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css" rel="stylesheet">

</head>

<body>
    <#include "header.ftl">
    <h1>Mensajes</h1>

    <div class="col-md-4"></div>
    <div class="col-md-4">
        <h1>Escribe un Mensaje</h1>
        <form action="/crearMensaje" th:object="${mensaje}" method="post" >
            <label>Mensaje:</label> <input name="mensaje" type="text"></>
        <button name="Insertar" id="insertar" type="submit">Enviar</button>
        </form>
    </div>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Mensaje</th>
            <th>Autor</th>
            <th>Comentarios</th>
        </tr>
        </thead>
        <tbody>
        <#list mensajes as m>
        <tr>
            <td><a href="/comentario?id=${m._id}"> ${m.mensaje}</a></td>
            <td>${m.autor}</td>
            <td>${m.cantidad}</td>


        </tr>
        </#list>

        </tbody>
    </table>



<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="/webjars/jquery/3.1.0/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
</body>

</html>