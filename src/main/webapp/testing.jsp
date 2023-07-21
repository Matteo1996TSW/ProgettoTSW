<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.*" %>
<%@ page import="Model.CPU_.Cpu" %>
<%@ page import="Model.CPU_.CpuDAO" %>
<%@ page import="Model.Archiviazione_.HDD_.Hdd" %>
<%@ page import="Model.Archiviazione_.HDD_.HddDAO" %>
<%@ page import="Model.MOBO_.Mobo" %>
<%@ page import="Model.MOBO_.MoboDAO" %>
<%@ page import="Model.RAM_.Ram" %>
<%@ page import="Model.RAM_.RamDAO" %>
<%@ page import="Model.Archiviazione_.SDD_.SsdDAO" %>
<%@ page import="Model.Archiviazione_.SDD_.Ssd" %>
<%@ page import="Model.GPU_.Gpu" %>
<%@ page import="Model.GPU_.GpuDAO" %>
<%@ page import="Model.PSU_.PsuDAO" %>
<%@ page import="Model.PSU_.Psu" %>
<%@ page import="Model.CASE_.Case" %>
<%@ page import="Model.CASE_.CaseDAO" %>
<%@ page import="Model.DISSIPATORE_.DissipatoreDAO" %>
<%@ page import="Model.DISSIPATORE_.Dissipatore" %>
<%@ page import="Model.Carrello_.Carrello" %>
<%@ page import="Model.Carrello_.CarrelloDAO" %>
<%@ page import="Controller.HomeServlet" %>
<%@ page import="Model.Cliente_.Cliente" %>
<%@ page import="Model.CATALOGO_.Catalogo" %>
<%@ page import="java.nio.file.Files" %>
<%@ page import="java.nio.file.Paths" %>
<%@ page import="java.io.File" %>
<%@ page import="java.util.Base64" %>
<%@ page import="javax.imageio.ImageIO" %>
<%@ page import="java.awt.image.BufferedImage" %>
<%@ page import="java.io.ByteArrayOutputStream" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.io.UncheckedIOException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <link rel = "stylesheet" type = "text/css" href = "css/style.css">
</head>
<body>
<%
    HttpSession ss = request.getSession();
    Catalogo catalogo = null;
    catalogo = (Catalogo) ss.getAttribute("catalogo");

    for(Prodotto p : catalogo.getCatalogo()) {
        String imgUrl = p.getUrl()+"/2.jpg";
        out.println(" <img src="+imgUrl+" alt=\"\" width=\"500\" height=\"500\">");
    }

%>


</body>
</html>
