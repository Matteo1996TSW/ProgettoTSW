<%@ page import="Model.PasswordEncrypter" %>
<%@ page import="Model.Cliente_.Cliente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel = "stylesheet" type = "text/css" href = "css/style.css">
    <link rel = "stylesheet" type = "text/css" href = "css/login.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-git.js"></script>
   <script src="https://kit.fontawesome.com/e11ac5b74d.js" crossorigin="anonymous"></script>
    <script src="js/navbar.js"></script>
    <script src="js/common.js"></script>
    <script src="js/login.js"></script>
    <link rel="icon" type="image/x-icon" href="Images/favicon.ico">
    <title>Login</title>
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
    <!-- Viene visualizzato solo se è stato già fallito un login -->
    <%
        if (request.getAttribute("loginErr") != null) {
        out.println("<div class=\"alert\">");
        out.println("<span class=\"closebtn\" onclick=\"this.parentElement.style.display='none';\">&times;</span> ");
        out.println("<strong>Attenzione: </strong> Mail o password errata.");
        out.println("</div>");
        }
        %>
    <div class="login-container">
        <h1>Effettua l'accesso</h1>
        <form action="Login" method="post" onsubmit="return validateLogin()" class="flex-container">
            <input type="text" name="Mail" id="mail" placeholder="E-Mail" required/>
            <input type="password" name="Password" id="password" placeholder="Password" required/>
            <input type="submit" value="Login"/>
        </form>
        <p>Non hai ancora un account? <a href="CreazioneUtente.jsp" id="registration-link">Registrati</a></p>
    </div>
</div>
</body>
</html>
