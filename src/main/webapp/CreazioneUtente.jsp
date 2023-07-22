<%@ page import="model.Cliente_.Cliente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Creazione Utente</title>
    <script src="https://code.jquery.com/jquery-git.js"></script>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="css/creazioneUtente.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"> 
    <script src="https://kit.fontawesome.com/e11ac5b74d.js" crossorigin="anonymous"></script>
    <script src="js/navbar.js"></script>
    <script src="js/common.js"></script>
    <!-- <script src="js/creazioneUtente.js"></script>-->
    <link rel="icon" type="image/x-icon" href="Images/favicon.ico">
</head>
<body>
<%
    Cliente c = (Cliente) session.getAttribute("cliente");
%>
<div class="header">
    <div class="flex-container topnav" id="topnav">
        <div class="flex-left-item logo">
            <a href="index.jsp"><img src="Images/PCBuilder-logo.png" id="header-logo" alt="errore"></a>
        </div>
        <a href="javascript:void(0);" class="right-buttons burger" onclick="dropDownBurger()">&#9776;</a>
        <div class="nav flex-right-item" id="nav-list">
            <ul class="flex-container">
                <li><a href="index.jsp" class="active">Home</a></li>
                <li><a href="Catalogo.jsp">Catalogo</a></li>
                <li class="empty-flex-field" id="emptyFlexField"></li>
                <li class="right-buttons"><a href="carrello.jsp" class="carrello-link"><i
                        class="fa-solid fa-cart-shopping"></i></a></li>
                <%
                    if (c != null) {
                        out.println("<li class=\"right-buttons\">" +
                                "<div class=\"dropdown\">" +
                                "<button class=\"dropbtn\" onclick=\"dropdownMenu()\">" +
                                "<i class=\"fa-solid fa-circle-user\"></i>Profilo\n" +
                                "</button>" +
                                "<div class=\"dropdown-content\" id=\"myDropdown\">\n" +
                                "<a href=\"modInfoCliente\">Il mio profilo</a>\n" +
                                "<a href=\"storicoOrdini\">I miei ordini</a>\n" +
                                "<a href=\"Logout\" class=\"logout-link\">LogOut</a>\n" +
                                "</div>" +
                                "</div>" +
                                "</li>");
                    }

                    if (c == null) {
                        out.println("<li class=\"right-buttons\"><a href=\"login.jsp\">Login</a></li>");
                    }
                %>
            </ul>
        </div>
    </div>
</div>


<div class="main flex-container">

    <%
        if (request.getAttribute("register.error") != null) {
            out.println("<div class=\"alert\">");
            out.println("<span onclick=\"this.parentElement.style.display='none';\">&times;</span> ");
            out.println("<strong>Attenzione: </strong> Compila tutti i campi.");
            out.println("</div>");
        }
        if (request.getAttribute("creation.error") != null) {
            out.println("<div class=\"alert\">");
            out.println("<span onclick=\"this.parentElement.style.display='none';\">&times;</span> ");
            out.println("<strong>Attenzione: </strong> Mail già registrata");
            out.println("</div>");
        }
    %>
    <div class="registration-form-container flex-container">
        <h1>Creazione Utente</h1>
        <form action="NuovoCliente" method="POST" onsubmit="return validateForm()" class="flex-container">
            <input type="text" name="nikname" id="nikname" placeholder="Nickname" required/>
            <input type="password" name="password" id="password" placeholder="Password" required/>
            <input type="text" name="mail" id="mail" placeholder="Email" required/>
            <input type="text" name="telefono" id="tel" placeholder="Telefono" required/>
            <input type="text" name="cap" id="cap" placeholder="CAP" required/>
            <input type="text" name="provincia" id="provincia" placeholder="Provincia" required/>

            <input type="text" name="citta" id="citta" placeholder="Città" required/>
            <input type="text" name="via" id="via" placeholder="Via" required/>
            <input type="submit" value="Registrati">
        </form>
    </div>
</div>
</body>
</html>
