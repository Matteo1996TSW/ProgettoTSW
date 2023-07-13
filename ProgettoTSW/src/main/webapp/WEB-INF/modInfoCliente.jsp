<%@ page import="Model.Cliente_.Cliente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Info personali</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-git.js"></script>
    <link rel="icon" type="image/x-icon" href="Images/favicon.ico">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://kit.fontawesome.com/d757446473.js" crossorigin="anonymous"></script>
    <script src="js/common.js"></script>
    <script src="js/navbar.js"></script>
    <script src="js/modInfoCliente.js"></script>
    <link rel="stylesheet" type="text/css" href="css/modInfoCliente.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<%
    Cliente c = (Cliente) session.getAttribute("cliente");
%>
<div class="header">
    <div class="flex-container topnav" id ="topnav">
        <div class="flex-left-item logo">
            <a href="index.jsp"><img src="Images/PCBuilder-logo.png" id="header-logo"></a>
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
        Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
        out.println("<div class=\"wrapper\">" +
                        "<div class=\"field-container flex-container\">" +
                            "<h2>Dati utente</h2>" +
                            "<label for=\"nick\">NickName</label>" +
                            "<input type=\"text\" name=\"nick\" id=\"nick\" class=\"infoPers\" value=\""+ cliente.getNickname() +"\" >"+
                            "<label for=\"tel\">Telefono</label>" +
                            "<input type=\"text\" name=\"tel\" id=\"tel\" class=\"infoPers\" value=\""+ cliente.getTel() +"\" >"+
                            "<input type=\"submit\" value=\"Modifica info. personali\" id=\"submitModInfoCliente\">" +
                            "<br>" +
                            "<h2>Password</h2>" +
                            "<label for=\"password\">Password</label>" +
                            "<input type=\"password\" value=\"**********\" name=\"password\" id=\"password\" class=\"pass\" />" +
                            "<input type=\"submit\" value=\"Modifica password\" id=\"submitModPass\"/>" +
                            "<br>" +
                            "<h2>Indirizzo di spedizione</h2>" +
                            "<div id=\"modIndirizzo\" class=\"flex-container\">" +
                                "<label for=\"via\">Via</label>" +
                                "<input type=\"text\" name=\"via\" id=\"via\" class=\"infoSped\" value=\""+ cliente.getVia() +"\" >" +
                                "<label for=\"provincia\">Provincia</label>" +
                                "<input type=\"text\" name=\"provincia\" id=\"provincia\" class=\"infoSped\" value=\""+ cliente.getProvincia() +"\" >"+
                                "<label for=\"citta\">Città</label>" +
                                "<input type=\"text\" name=\"citta\" id=\"citta\" class=\"infoSped\" value=\""+ cliente.getCitta() +"\" >"+
                                "<label for=\"cap\">CAP</label>" +
                                "<input type=\"text\" name=\"cap\" id=\"cap\" class=\"infoSped\" value=\""+ cliente.getCap() +"\" >"+
                                "<input type=\"submit\" value=\"Modifica info. spedizione\" id=\"submitModInfoSped\">" +
                            "</div>" +
                        "</div>" +
                    "</div>"
        );
    %>
</div>
</body>
</html>
