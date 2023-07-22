<%@ page import="java.util.List" %>
<%@ page import="model.Cliente_.Cliente" %>
<%@ page import="model.Prodotto" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Carrello_.Carrello" %>
<%@ page import="model.CATALOGO_.Catalogo" %>
<%@ page import="model.CATALOGO_.CatalogoDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Carrello</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-git.js"></script>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="css/carrello.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://kit.fontawesome.com/e11ac5b74d.js" crossorigin="anonymous"></script>
    <script src="js/navbar.js"></script>
    <script src="js/carrello.js"></script>
    <link rel="icon" type="image/x-icon" href="Images/favicon.ico">
</head>
<body>
<%
    Cliente cliente = (Cliente) session.getAttribute("cliente");
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
                    if(cliente!=null){
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
                    if(cliente==null){
                        out.println("<li class=\"right-buttons\"><a href=\"login.jsp\">Login</a></li>");
                    }
                %>
            </ul>
        </div>
    </div>
</div>
<div class="main flex-container">
        <%
            Carrello carrello = (Carrello) session.getAttribute("carrello");
        	Catalogo catalogo = (Catalogo) session.getAttribute("catalogo");
            
            if(catalogo == null){
	        	List<Prodotto> listaProdotti = null;
	        	CatalogoDAO cat = new CatalogoDAO();
	        	listaProdotti = cat.doRetriveAll();
	        	catalogo =  new Catalogo();
	        	catalogo.setCatalogo(listaProdotti);
	        }

            if (!carrello.isEmpty()) {
                List<Prodotto> carrelloList = carrello.getCarrello();
                out.println("<ul>" +
                                "<h1>Il tuo carrello</h1>");
                for (Prodotto prodotto : carrelloList) {
                    out.println("<li class=\"cart-list\">" +
                                "<div class=\"row flex-container\">\n" +
                                   // "<img src=\"" + prodotto.getUrl() + "\" alt=\"\">\n" +
                                    "<ul class=\"flex-item\">\n" +
                                        "<li><h3>"+ prodotto.getMarca() + " " + prodotto.getModello() +"</h3></li>\n" +
                                        "<li>\n" +
                                            "<div id=\"" + prodotto.getID() + "\"/>\n" +
                                                "<input type=\"number\" id=\"quantOf" + prodotto.getID() + "\" name=\"quantity\" min=\"1\" value=\"" + prodotto.getQuantita() + "\" max=\"" + (catalogo.doRetriveById(prodotto.getID()).getQuantita() + carrello.doRetriveQuantitaProdottoById(prodotto.getID())) + "\">\n" +
                                                "<input type=\"button\" value=\"Aggiorna quantità\" id=\"submit\" class=\"submit\">" +
                                            "</div>" +
                                        "</li>" +
                                    "</ul>" +
                                    "<h3 class=\"flex-item\">" + prodotto.getPrezzo() * prodotto.getQuantita() + "</h3>" +
                                    "<form action=\"removeCart\" method=\"GET\" id=\"remove\">" +
                                        "<input type=\"hidden\" name=\"idProdotto\" id=\"idprodotto\" class=\"" + prodotto.getID() + "\" value=\"" + prodotto.getID() +"\">" +
                                        "<input type=\"submit\" value=\"Rimuovi\" id=\"submit\">" +
                                    "</form>" +
                                "</div>\n" +
                            "</li>");
                }

                if (cliente != null) {
                    out.println("<br>" +
                            "<div class=\"expire-cart-button\">" +
                                "<a href=\"redirectToIndirizzoSpedizione\"></i>Procedi all'ordine</a>" +
                            "</div></ul>");
                } else {
                    out.println("<br>" +
                            "<div class=\"login-needed-container\">" +
                                "<h2 class=\"login-needed\">Devi essere loggato per acquistare. Procedi al <a href=\"login.jsp\"> login </a></h2>" +
                            "</div>");
                }
            } else {
                out.println("<div class=\"empty-cart flex-container\"><h1>Il carrello è vuoto</h1></div>");
            }
        %>
</div>
</body>
</html>
