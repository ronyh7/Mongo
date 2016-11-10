<!DOCTYPE html>
<html>
<head>
<title>Insertar</title>
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/css/insertar.css" rel="stylesheet" >

</head>
<body>
    <#include "header.ftl">
    <div class="container">
        <div class="col-md-3">
            <h1>Mensaje</h1>
            <pre>
            ${mensaje}
            </pre>
            Por:<strong>${autor}</strong>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Comentario</th>
                        <th>ID</th>
                        <th>Autor</th>
                    </tr>
                </thead>
                <tbody>
                <#if comentarios?has_content>
                    <#list comentarios as c>
                        <tr>
                            <td>${c.texto}</td>
                            <td>${c._id}</td>
                            <td>${c.autor}</td>
                        </tr>
                    </#list>
                </#if>
                </tbody>
            </table>
        </div>
        <div class="col-md-4">

            <form action="/comentario/crearComentario" method="post" >
                <label>Responder:</label> <input name="comentario" type="text"/>
                <button name="Insertar" id="insertar" type="submit">Enviar</button>
            </form>
        </div>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>
</html>