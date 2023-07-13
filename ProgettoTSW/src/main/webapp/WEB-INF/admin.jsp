<%@ page import="Model.Cliente_.Cliente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <link rel = "stylesheet" type = "text/css" href = "css/style.css">
    <link rel = "stylesheet" type = "text/css" href = "css/admin.css">
    <script src="https://code.jquery.com/jquery-git.js"></script>
    <script src="js/admin.js"></script>
    <link rel="icon" type="image/x-icon" href="Images/favicon.ico">
</head>
<body>
<%
    Cliente c = (Cliente) session.getAttribute("cliente");
%>
<div class="header">
    <div class="flex-container topnav" id="topnav">
        <div class="flex-left-item logo">
            <a href="#"><img src="Images/PCBuilder-logo.png" id="header-logo"></a>
        </div>
        <a href="javascript:void(0);" class="right-buttons burger" onclick="dropDownBurger()">&#9776;</a>
        <div class="nav flex-right-item" id="nav-list">
            <ul class="flex-container">
                <li><a href="#" class="active" onclick="nuovo()">Nuovo Prodotto</a></li>
                <li><a href="#" onclick="aggiorna()">Modifica prodotto</a></li>
                <li class="empty-flex-field" id="emptyFlexField"></li>
                <li class="right-buttons"><a href="Logout" class="logout-link">LogOut</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="main flex-container">
    <div id="stampa" class="flex-container"><p>Premi un pulsante.</p></div>
    <iframe id="frameCatalogo" name="iframeCatalogo"
            style="display: none;       /*set to block if modify product is pressed */
            border: none;         /* Reset default border */
            height: 100vh;        /* Viewport-relative units */
            width: 100vw;">
    </iframe>
</div>
</body>
</html>
