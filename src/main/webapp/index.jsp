<%@ page import="model.Cliente_.Cliente" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="model.CPU_.Cpu" %>
<%@ page import="model.Prodotto" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Home</title>
    <link rel = "stylesheet" type = "text/css" href = "css/style.css">
    <link rel = "stylesheet" type = "text/css" href = "css/index.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="js/navbar.js"></script>
    <link rel="icon" type="image/x-icon" href="Images/favicon.ico">
</head>
<body>
<%
    Cliente c = (Cliente) session.getAttribute("cliente");
%>
<div class="header">
    <div class="flex-container topnav" id ="topnav">
        <div class="flex-left-item logo">
            <a href="index.jsp"><img src="Images/PCBuilder-logo.png" id="header-logo" alt="errore"></a>
        </div>
        <a href="javascript:void(0);" class="right-buttons burger"  onclick="dropDownBurger()">&#9776;</a>
        <div class="nav flex-right-item" id="nav-list">
            <ul class="flex-container">
                <li><a href="index.jsp" class="active">Home</a></li>
                <li><a href="Catalogo.jsp">Catalogo</a></li>
                <li class="empty-flex-field" id="emptyFlexField"></li>
                <li class="right-buttons"><a href="carrello.jsp" class="carrello-link"><i class="fa-solid fa-cart-shopping"></i></a></li>
                <%
                    if(c!=null){
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
                %>
                <%
                    if(c==null){
                        out.println("<li class=\"right-buttons\"><a href=\"login.jsp\">Login</a></li>");
                    }
                %>
            </ul>
        </div>
    </div>
</div>
<div class="main flex-container">
    <%
        if(c != null){
            out.println("<div class=\"welcome-header\">" +
                    "<h1>Bentornato " + c.getNickname() + "</h1>" +
                    "</div>");
        }
        else{
            out.println("<div class=\"welcome-header\">" +
                    "<h1>Benvenuto su PCBuilder</h1><br>" +
                    "</div>");
        }
    %>
    <div class="wrapper flex-container">
        <div class="row flex-container">
            <div class="col left">
                <img src="Images/pc-image2.jpg" class="left-image" alt="errore">
            </div>
            <div class="col right">
                <p class="right-text">
                    PCBuilder offre un vastissimo assortimento di componentistica per PC desktop. I prodotti offerti da PCBuilder sono: <br>
                <ul>
                    <li>CPU</li>
                    <li>GPU</li>
                    <li>Schede madri</li>
                    <li>RAM</li>
                    <li>Alimentatori</li>
                    <li>Dissipatori</li>
                    <li>Case</li>
                    <li>Hard disk</li>
                    <li>SSD</li>
                </ul>
               
            </div>
        </div>
        <div class="row flex-container">
            <div class="col right">
                <p class="left-text">
                    Acquistando sul nostro store sarà assicurato sulla qualità dei prodotti, tutti con garanzia italiana per 2 anni.<br>
                    Se non hai ancora un account effettua subito la registrazione e inizia subito ad assemblare la tua build<br>
                    <a href="CreazioneUtente.jsp">Registrati</a>
                </p>
            </div>
            <div class="col left">
                <img src="Images/pc-image4.jpg" class="right-image" >
            </div>
        </div>
    </div>
</div>




</body>
</html>