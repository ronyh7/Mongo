<!DOCTYPE html>
<html x lang="en">

<head>
    <link href="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css" rel="stylesheet">

</head>

<body>
    <#include "header.ftl">
    <h1>Palabra: ${palabra}</h1>

    <div class="col-md-4"></div>
    <div class="col-md-4">

    </div>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Mensajes</th>
        </tr>
        </thead>
        <tbody>
        <#list mensajes as m>
        <tr>
            <td><a href="/comentario?id=${m._id}">${m.mensaje}</a></td>
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