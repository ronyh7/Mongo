<!DOCTYPE html>
<html x lang="en">

<head>
    <link href="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css" rel="stylesheet">

</head>

<body>
    <#include "header.ftl">
    <h1>Palabras mas usadas</h1>

    <div class="col-md-4"></div>
    <div class="col-md-4">

    </div>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Palabra</th>
            <th>Repeticiones</th>
        </tr>
        </thead>
        <tbody>
        <#list palabras as p>
        <tr>
            <td><a href="/palabra/palabraMensaje?palabra=${p._id}"> ${p._id}</a></td>
            <td>${p.value}</td>
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