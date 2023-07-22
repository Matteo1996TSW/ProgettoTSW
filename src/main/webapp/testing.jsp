<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.*" %>
<%@ page import="model.CPU_.Cpu" %>
<%@ page import="model.CPU_.CpuDAO" %>
<%@ page import="model.Archiviazione_.HDD_.Hdd" %>
<%@ page import="model.Archiviazione_.HDD_.HddDAO" %>
<%@ page import="model.MOBO_.Mobo" %>
<%@ page import="model.MOBO_.MoboDAO" %>
<%@ page import="model.RAM_.Ram" %>
<%@ page import="model.RAM_.RamDAO" %>
<%@ page import="model.Archiviazione_.SDD_.SsdDAO" %>
<%@ page import="model.Archiviazione_.SDD_.Ssd" %>
<%@ page import="model.GPU_.Gpu" %>
<%@ page import="model.GPU_.GpuDAO" %>
<%@ page import="model.PSU_.PsuDAO" %>
<%@ page import="model.PSU_.Psu" %>
<%@ page import="model.CASE_.Case" %>
<%@ page import="model.CASE_.CaseDAO" %>
<%@ page import="model.DISSIPATORE_.DissipatoreDAO" %>
<%@ page import="model.DISSIPATORE_.Dissipatore" %>
<%@ page import="model.Carrello_.Carrello" %>
<%@ page import="model.Carrello_.CarrelloDAO" %>
<%@ page import="controller.HomeServlet" %>
<%@ page import="model.Cliente_.Cliente" %>
<%@ page import="model.CATALOGO_.Catalogo" %>
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
<html lang="it">
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
