<!DOCTYPE html>
<html>
<head>
<title>Insertar</title>
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/css/insertar.css" rel="stylesheet" >

</head>
<body>
    <div class="container">
        <div class="col-md-3"></div>
        <div class="col-md-4">
            <h1>Escribe un Mensaje mama${usuario.username}</h1>
            <form action="/insertar/" method="post" >
                <label>Mensaje:</label> <textarea name="titulo" type="text"></textarea>
                <button name="Insertar" id="insertar" type="submit">Enviar</button>
            </form>
        </div>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>
</html>