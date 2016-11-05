<!DOCTYPE html>
<html x lang="en">

<head>
    <link href="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css" rel="stylesheet">

</head>

<body>
    <#include "header.ftl">
    <h1>Mensajes</h1>
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
            <td><a href="/crearComentario?id=${m._id}"> ${m.mensaje}</a></td>
            <td>${m.autor}</td>
            <#if m.comentarios?has_content>
                <#list m.comentarios as c>
                    <td>${c}</td>
                </#list>
            <#else>
                <td>0</td>
            </#if>

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