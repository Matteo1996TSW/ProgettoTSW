<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.Carrello_.Carrello" %>
<%@ page import="Model.Carrello_.CarrelloDAO" %>
<%@ page import="Model.Cliente_.Cliente" %>
<%@ page import="java.sql.SQLException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Strorico ordini</title>
    <link rel = "stylesheet" type = "text/css" href = "css/style.css">
    <link rel = "stylesheet" type = "text/css" href = "css/storicoOrdini.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://kit.fontawesome.com/d757446473.js" crossorigin="anonymous"></script>
    <script src="js/navbar.js"></script>
    <link rel="icon" type="image/x-icon" href="Images/favicon.ico">
</head>
<body>
<%
    Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
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
    <div class="wrapper flex-container">
        <div class="info-container flex-container">
            <%
                ArrayList<Carrello> ordini;
                try {
                    assert cliente != null;//Evita null pointer
                    ordini = CarrelloDAO.doRetriveStorico(cliente.getMail());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                if(ordini == null)
                    out.println("<h1>Non hai ancora effettuato acquisti</h1>");
                else {
                    out.println("<h1>Storico Ordini</h1>");
                    for (Carrello c : ordini) {
                        out.println("<div class=\"carrello flex-container\">" +
                                        "<div class=\"inner-padding\">" +
                                            "<ul class=\"info-header flex-container\">" +
                                                "<li>Oggetto</li>" +
                                                "<li>Quantità</li>" +
                                                "<li>Prezzo</li>" +
                                                "<li>Subtotale</li>" +
                                                "<li>Totale</li>" +
                                            "</ul>");
                        for(int i = 0; i < c.getCarrello().size(); i++){
                            out.println("<div class=\"row flex-container\">" +
                                            "<ul class=\"info flex-container\">" +
                                                "<li>" + c.getCarrello().get(i).getMarca() + " " + c.getCarrello().get(i).getModello() + "</li>" +
                                                "<li>" + c.getCarrello().get(i).getQuantita() + "</li>" +
                                                "<li>" + c.getCarrello().get(i).getPrezzo() + "</li>" +
                                                "<li>" + c.getCarrello().get(i).getPrezzo()*c.getCarrello().get(i).getQuantita() + "</li>" +
                                            "</ul>" +
                                        "</div>");
                        }
                        out.println(    "<br><h3>Totale: " + c.calculateTotal() + "</h3>" +
                                    "</div>" +
                                "</div>");
                    }
                }
            %>
        </div>
    </div>
</div>
</body>
</html>