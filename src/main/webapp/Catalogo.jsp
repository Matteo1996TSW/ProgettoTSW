<%@ page import="java.util.List" %>
<%@ page import="model.Prodotto" %>
<%@ page import="model.CPU_.Cpu" %>
<%@ page import="model.CASE_.Case" %>
<%@ page import="model.DISSIPATORE_.Dissipatore" %>
<%@ page import="model.GPU_.Gpu" %>
<%@ page import="model.MOBO_.Mobo" %>
<%@ page import="model.PSU_.Psu" %>
<%@ page import="model.RAM_.Ram" %>
<%@ page import="model.Archiviazione_.HDD_.Hdd" %>
<%@ page import="model.Archiviazione_.SDD_.Ssd" %>
<%@ page import="model.CATALOGO_.Catalogo" %>
<%@ page import="model.CATALOGO_.CatalogoDAO" %>
<%@ page import="model.Cliente_.Cliente" %>
<%@ page import="model.Cliente_.ClienteDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Catalogo</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-git.js"></script>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="css/catalogo.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://kit.fontawesome.com/e11ac5b74d.js" crossorigin="anonymous"></script>
    <script src="js/navbar.js"></script>
    <script src="js/catalogo.js"></script>
    <script src="js/sliderFilter.js"></script>
    <%
        Cliente user = (Cliente) session.getAttribute("cliente");
        if(user != null && user.isAdministrator()){
            out.println("<style>" +
                    ".product-links{display:none}" +
                    "</style>");
        }
    %>
</head>
<body>
<%
	List<Prodotto> listaProdotti = null;
    CatalogoDAO cat = new CatalogoDAO();
    listaProdotti = cat.doRetriveAll();
    Catalogo catalogo =  new Catalogo();
    catalogo.setCatalogo(listaProdotti);

    List<Cpu> cpuList = (List<Cpu>) catalogo.doRetriveByType("CPU");
    List<Case> caseList = (List<Case>) catalogo.doRetriveByType("CASE");
    List<Dissipatore> dissipatoreList = (List<Dissipatore>) catalogo.doRetriveByType("DISSIPATORE");
    List<Gpu> gpuList = (List<Gpu>) catalogo.doRetriveByType("GPU");
    List<Mobo> moboList = (List<Mobo>) catalogo.doRetriveByType("MOBO");
    List<Psu> psuList = (List<Psu>) catalogo.doRetriveByType("PSU");
    List<Ram> ramList = (List<Ram>) catalogo.doRetriveByType("RAM");
    List<Hdd> hddList = (List<Hdd>) catalogo.doRetriveByType("HDD");
    List<Ssd> ssdList = (List<Ssd>) catalogo.doRetriveByType("SSD");
    
    
    System.out.println(caseList.toString());
    

    String path = "info-pezzo.jsp";
    boolean isAdministrator = false;

    //Se l'utente è amministratore dovrà reindirizzare a una pagina diversa al click sul prodotto
    //E non far vedere la navbar
    if (user != null) {
        if (!user.isAdministrator()) {
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
                    if (user != null) {
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
                    if (user == null) {
                        out.println("<li class=\"right-buttons\"><a href=\"login.jsp\">Login</a></li>");
                    }
                %>
            </ul>
        </div>
    </div>
</div>
<% } else {
    path = "redirectToAdminPage";
    isAdministrator = true;
}
} else {%>
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
                    if (user != null) {
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
                    if (user == null) {
                        out.println("<li class=\"right-buttons\"><a href=\"login.jsp\">Login</a></li>");
                    }
                %>
            </ul>
        </div>
    </div>
</div>
<%
    }
%>
<div class="main flex-container">
    <div class="filter-div inline flex-container slide-panel" id="filter-panel">
        <div class="open-filter-tab flex-container" id="open-filter-div">
            <button type="button" id="open-button">
                Filtri <i class="fa-solid fa-filter"></i>
            </button>
        </div>
        <div id="filter-container" class="hide">
            <div class="close-filter-tab flex-container">
                <button type="button" id="close-button">
                    <i class="fa fa-close"></i>
                </button>
            </div>
            <h3 class="filter-header">Filtri</h3>
            <form>
                <div class="filter-by-name">
                    <lable for="radMarca" class="form-control filter-lable">
                        <input type="radio" id="radMarca" name="choice" value="Marca" onclick="changePlaceholder()"
                               checked="checked">Marca
                    </lable>
                    <br>
                    <lable for="radModello" class="form-control filter-lable">
                        <input type="radio" id="radModello" name="choice" value="Modello" onclick="changePlaceholder()">Modello
                    </lable>
                    <br>
                    <input type="text" id="input_cerca" placeholder="Marca da cercare">
                </div>
                <div class="filter-by-value">
                    <div class="container">
                        <div class="slider">
                            <lable for="priceSlider" class="filter-lable">Prezzo</lable>
                            <br>
                            <output id="startRange"><%=catalogo.getMinPrice()%>
                            </output>
                            <input id="priceSlider" type="range"
                                   min=<%=catalogo.getMinPrice()%> max=<%=catalogo.getMaxPrice()%>
                                   value=<%=catalogo.getMaxPrice()%>>
                            <output id="current"><%=catalogo.getMaxPrice()%>
                            </output>
                            <output id="endRange"><%=catalogo.getMaxPrice()%>
                            </output>
                        </div>
                    </div>
                </div>
                <input type="reset" value="reset" id="reset-button">
            </form>
        </div>
    </div>
    <div class="catalogo-div inline flex-container" id="divCatalogo">
        <div class="flex-container product-slider" id="divCatalogo-inner">
            <%
                    for (Cpu cpu : cpuList) {
                    //Visto che la visione del catalogo è generalizzata dobbiamo poter
                    //mostrare all'amministratore anche i prodotti a 0 ma questo non deve succedere
                    //se invece è un utente normale.
                    boolean toShow = cpu.getQuantita() > 0 || isAdministrator;
                    if (toShow) {
                        out.println("<div class=\"product-card\">\n" +
                                "<div class=\"product-tumb inner-padding\">\n" +
                                "<img src=\"" + cpu.getUrl() + "\" alt=\"\">\n" +
                                "</div>\n" +
                                "<div class=\"product-details\">\n" +
                                "<span class=\"product-catagory\">CPU</span>\n" +
                                "<h4><a href=\"" + path + "?Id=" + cpu.getID() + "&type=CPU\">" + cpu.getMarca() + " " + cpu.getModello() + "</a></h4>\n" +
                                "<p>" + cpu.getDescrizione() + "</p>\n" +
                                "<div class=\"product-bottom-details\">\n" +
                                "<div class=\"product-price\">" + cpu.getPrezzo() + "</div>\n" +
                                "<div class=\"product-links\">\n" +
                                "<a href=\"addCart?Id=" + cpu.getID() + "&quantity=" + 1 + "\"><i class=\"fa fa-shopping-cart\"></i></a>\n" +
                                "</div>\n" +
                                "</div>\n" +
                                "</div>\n" +
                                "</div>");

                    }
                }
            %>
            <%
                for (Case case_ : caseList) {
                    boolean toShow = case_.getQuantita() > 0 || isAdministrator;
                    if (toShow) {
                         out.println("<div class=\"product-card\">\n" +
                                        "<div class=\"product-tumb  inner-padding\">\n" +
                                            "<img src=\"" + case_.getUrl() + "\" alt=\"\">\n" +
                                        "</div>\n" +
                                        "<div class=\"product-details\">\n" +
                                            "<span class=\"product-catagory\">Case</span>\n" +
                                            "<h4><a href=\"" + path + "?Id=" + case_.getID()+ "&type=Case\">" + case_.getMarca() + " " + case_.getModello() + "</a></h4>\n" +
                                            "<p>" + case_.getDescrizione() + "</p>\n" +
                                            "<div class=\"product-bottom-details\">\n" +
                                                "<div class=\"product-price\">" + case_.getPrezzo() + "</div>\n" +
                                                "<div class=\"product-links\">\n" +
                                                    "<a href=\"addCart?Id=" + case_.getID() + "&quantity=" + 1 + "\"><i class=\"fa fa-shopping-cart\"></i></a>\n" +
                                                "</div>\n" +
                                            "</div>\n" +
                                        "</div>\n" +
                                     "</div>");
                    }
                }
            %>
            <%
                for (Dissipatore dissipatore : dissipatoreList) {
                    boolean toShow = dissipatore.getQuantita() > 0 || isAdministrator;
                    if (toShow) {
                        out.println("<div class=\"product-card\">\n" +
                                        "<div class=\"product-tumb  inner-padding\">\n" +
                                            "<img src=\"" + dissipatore.getUrl() + "\" alt=\"\">\n" +
                                        "</div>\n" +
                                        "<div class=\"product-details\">\n" +
                                            "<span class=\"product-catagory\">Dissipatori</span>\n" +
                                            "<h4><a href=\"" + path + "?Id=" + dissipatore.getID()+ "&type=Dissipatore\">" + dissipatore.getMarca() + " " + dissipatore.getModello() + "</a></h4>\n" +
                                            "<p>" + dissipatore.getDescrizione() + "</p>\n" +
                                            "<div class=\"product-bottom-details\">\n" +
                                                "<div class=\"product-price\">" + dissipatore.getPrezzo() + "</div>\n" +
                                                "<div class=\"product-links\">\n" +
                                                    "<a href=\"addCart?Id=" + dissipatore.getID() + "&quantity=" + 1 + "\"><i class=\"fa fa-shopping-cart\"></i></a>\n" +
                                                "</div>\n" +
                                            "</div>\n" +
                                        "</div>\n" +
                                     "</div>");
                    }
                }
            %>
            <%
                for (Gpu gpu : gpuList) {
                    boolean toShow = gpu.getQuantita() > 0 || isAdministrator;
                    if (toShow) {
                        out.println("<div class=\"product-card  inner-padding\">\n" +
                                        "<div class=\"product-tumb\">\n" +
                                            "<img src=\"" + gpu.getUrl() + "\" alt=\"\">\n" +
                                        "</div>\n" +
                                        "<div class=\"product-details\">\n" +
                                            "<span class=\"product-catagory\">GPU</span>\n" +
                                            "<h4><a href=\"" + path + "?Id=" + gpu.getID()+ "&type=GPU\">" + gpu.getMarca() + " " + gpu.getModello() + "</a></h4>\n" +
                                            "<p>" + gpu.getDescrizione() + "</p>\n" +
                                            "<div class=\"product-bottom-details\">\n" +
                                                "<div class=\"product-price\">" + gpu.getPrezzo() + "</div>\n" +
                                                "<div class=\"product-links\">\n" +
                                                    "<a href=\"addCart?Id=" + gpu.getID() + "&quantity=" + 1 + "\"><i class=\"fa fa-shopping-cart\"></i></a>\n" +
                                                "</div>\n" +
                                            "</div>\n" +
                                        "</div>\n" +
                                     "</div>");
                    }
                }
            %>
            <%
                for (Mobo mobo : moboList) {
                    boolean toShow = mobo.getQuantita() > 0 || isAdministrator;
                    if (toShow) {
                        out.println("<div class=\"product-card\">\n" +
                                        "<div class=\"product-tumb  inner-padding\">\n" +
                                            "<img src=\"" + mobo.getUrl() + "\" alt=\"\">\n" +
                                        "</div>\n" +
                                        "<div class=\"product-details\">\n" +
                                            "<span class=\"product-catagory\">Scheda madre</span>\n" +
                                            "<h4><a href=\"" + path + "?Id=" + mobo.getID()+ "&type=Mobo\">" + mobo.getMarca() + " " + mobo.getModello() + "</a></h4>\n" +
                                            "<p>" + mobo.getDescrizione() + "</p>\n" +
                                            "<div class=\"product-bottom-details\">\n" +
                                                "<div class=\"product-price\">" + mobo.getPrezzo() + "</div>\n" +
                                                "<div class=\"product-links\">\n" +
                                                    "<a href=\"addCart?Id=" + mobo.getID() + "&quantity=" + 1 + "\"><i class=\"fa fa-shopping-cart\"></i></a>\n" +
                                                "</div>\n" +
                                            "</div>\n" +
                                        "</div>\n" +
                                     "</div>");
                    }
                }
            %>
            <%
                for (Psu psu : psuList) {
                    boolean toShow = psu.getQuantita() > 0 || isAdministrator;
                    if (toShow) {
                        out.println("<div class=\"product-card\">\n" +
                                        "<div class=\"product-tumb  inner-padding\">\n" +
                                            "<img src=\"" + psu.getUrl() + "\" alt=\"\">\n" +
                                        "</div>\n" +
                                        "<div class=\"product-details\">\n" +
                                            "<span class=\"product-catagory\">Power supply unit</span>\n" +
                                            "<h4><a href=\"" + path + "?Id=" + psu.getID()+ "&type=Psu\">" + psu.getMarca() + " " + psu.getModello() + "</a></h4>\n" +
                                            "<p>" + psu.getDescrizione() + "</p>\n" +
                                            "<div class=\"product-bottom-details\">\n" +
                                                "<div class=\"product-price\">" + psu.getPrezzo() + "</div>\n" +
                                                "<div class=\"product-links\">\n" +
                                                    "<a href=\"addCart?Id=" + psu.getID() + "&quantity=" + 1 + "\"><i class=\"fa fa-shopping-cart\"></i></a>\n" +
                                                "</div>\n" +
                                            "</div>\n" +
                                        "</div>\n" +
                                     "</div>");
                    }
                }
            %>
            <%
                for (Ram ram : ramList) {
                    boolean toShow = ram.getQuantita() > 0 || isAdministrator;
                    if (toShow) {
                        out.println("<div class=\"product-card\">\n" +
                                        "<div class=\"product-tumb  inner-padding\">\n" +
                                            "<img src=\"" + ram.getUrl() + "\" alt=\"\">\n" +
                                        "</div>\n" +
                                        "<div class=\"product-details\">\n" +
                                            "<span class=\"product-catagory\">RAM</span>\n" +
                                            "<h4><a href=\"" + path + "?Id=" + ram.getID()+ "&type=Ram\">" + ram.getMarca() + " " + ram.getModello() + "</a></h4>\n" +
                                            "<p>" + ram.getDescrizione() + "</p>\n" +
                                            "<div class=\"product-bottom-details\">\n" +
                                                "<div class=\"product-price\">" + ram.getPrezzo() + "</div>\n" +
                                                "<div class=\"product-links\">\n" +
                                                    "<a href=\"addCart?Id=" + ram.getID() + "&quantity=" + 1 + "\"><i class=\"fa fa-shopping-cart\"></i></a>\n" +
                                                "</div>\n" +
                                            "</div>\n" +
                                        "</div>\n" +
                                     "</div>");
                    }
                }
            %>
            <%
                for (Hdd hdd : hddList) {
                    boolean toShow = hdd.getQuantita() > 0 || isAdministrator;
                    if (toShow) {
                        out.println("<div class=\"product-card\">\n" +
                                        "<div class=\"product-tumb  inner-padding\">\n" +
                                            "<img src=\"" + hdd.getUrl() + "\" alt=\"\">\n" +
                                        "</div>\n" +
                                        "<div class=\"product-details\">\n" +
                                            "<span class=\"product-catagory\">Hard disk</span>\n" +
                                            "<h4><a href=\"" + path + "?Id=" + hdd.getID()+ "&type=Hdd\">" + hdd.getMarca() + " " + hdd.getModello() + "</a></h4>\n" +
                                            "<p>" + hdd.getDescrizione() + "</p>\n" +
                                            "<div class=\"product-bottom-details\">\n" +
                                                "<div class=\"product-price\">" + hdd.getPrezzo() + "</div>\n" +
                                                "<div class=\"product-links\">\n" +
                                                    "<a href=\"addCart?Id=" + hdd.getID() + "&quantity=" + 1 + "\"><i class=\"fa fa-shopping-cart\"></i></a>\n" +
                                                "</div>\n" +
                                            "</div>\n" +
                                        "</div>\n" +
                                     "</div>");
                    }
                }
            %>
            <%
                for (Ssd ssd : ssdList) {
                    boolean toShow = ssd.getQuantita() > 0 || isAdministrator;
                    if (toShow) {
                        out.println("<div class=\"product-card\">\n" +
                                        "<div class=\"product-tumb inner-padding\">\n" +
                                            "<img src=\"" + ssd.getUrl() + "\" alt=\"\">\n" +
                                        "</div>\n" +
                                        "<div class=\"product-details\">\n" +
                                            "<span class=\"product-catagory\">Solid state disk</span>\n" +
                                            "<h4><a href=\"" + path + "?Id=" + ssd.getID()+ "&type=Ssd\">" + ssd.getMarca() + " " + ssd.getModello() + "</a></h4>\n" +
                                            "<p>" + ssd.getDescrizione() + "</p>\n" +
                                            "<div class=\"product-bottom-details\">\n" +
                                                "<div class=\"product-price\">" + ssd.getPrezzo() + "&euro;</div>\n" +
                                                "<div class=\"product-links\">\n" +
                                                    "<a href=\"addCart?Id=" + ssd.getID() + "&quantity=" + 1 + "\"><i class=\"fa fa-shopping-cart\"></i></a>\n" +
                                                "</div>\n" +
                                            "</div>\n" +
                                        "</div>\n" +
                                     "</div>");
                    }
                }
            %>
        </div>
    </div>
</div>
</body>
</html>
