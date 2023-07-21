package Controller;

import Model.Archiviazione_.HDD_.Hdd;
import Model.Archiviazione_.SDD_.Ssd;
import Model.CASE_.Case;
import Model.CATALOGO_.Catalogo;
import Model.CPU_.Cpu;
import Model.Cliente_.Cliente;
import Model.DISSIPATORE_.Dissipatore;
import Model.GPU_.Gpu;
import Model.MOBO_.Mobo;
import Model.PSU_.Psu;
import Model.Prodotto;
import Model.RAM_.Ram;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

//Stampa il catalogo. Viene usata per stampare i risultati delle query ajax al catalogo (marca, modello, prezzo)
@WebServlet(name = "AsincCatalogPrinter", value = "/AsincCatalogPrinter")
public class AsincCatalogPrinter extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Catalogo c = (Catalogo) request.getSession().getAttribute("printCatalog");
        Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
        String path = "info-pezzo.jsp";

        if(cliente != null){
            if(cliente.isAdministrator()){
                path = "redirectToAdminPage";
            }
        }

        String result = "";

        for(Prodotto p : c.getCatalogo()){
            result+=(doSwCase(p, response, path));
        }
        PrintWriter out = response.getWriter();
        out.write(result);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private String doSwCase(Prodotto p, HttpServletResponse response, String path) throws IOException {
        switch (p.getTipo()) {
            case "CPU":
                return writeCpuForCatalog(response, p, path);
            case "CASE":
                return writeCaseForCatalog(response, p, path);
            case "DISSIPATORE":
                return writeDissipatoreForCatalog(response, p, path);
            case "PSU":
                return writePsuForCatalog(response, p, path);
            case "MOBO":
                return writeMoboForCatalog(response, p, path);
            case "RAM":
                return writeRamForCatalog(response, p, path);
            case "HDD":
                return writeHddForCatalog(response, p, path);
            case "SSD":
                return writeSsdForCatalog(response, p, path);
            case "GPU":
                return writeGpuForCatalog(response, p, path);
            default:
                return "Errore";
        }
    }

    private String writePsuForCatalog(HttpServletResponse response, Prodotto p, String path) throws IOException {
        Psu psu = (Psu) p;
        //response.getWriter().println(
        String result =
                "<div class=\"product-card\">\n" +
                        "<div class=\"product-tumb inner-padding\">\n" +
                        "<img src=\"" + psu.getUrl() + "\" alt=\"\">\n" +
                        "</div>\n" +
                        "<div class=\"product-details\">\n" +
                        "<span class=\"product-catagory\">PSU</span>\n" +
                        "<h4><a href=\"" + path + "?Id=" + psu.getID() +"&type="+ p.getTipo() + "\">" + psu.getMarca() + " " + psu.getModello() + "</a></h4>\n" +
                        "<p>" + psu.getDescrizione() + "</p>\n" +
                        "<div class=\"product-bottom-details\">\n" +
                        "<div class=\"product-price\">" + psu.getPrezzo() + "</div>\n" +
                        "<div class=\"product-links\">\n" +
                        "<a href=\"addCart?Id=" + psu.getID() + "&quantity=" + 1 + "\"><i class=\"fa fa-shopping-cart\"></i></a>\n" +
                        "</div>\n" +
                        "</div>\n" +
                        "</div>\n" +
                        "</div>";
        //);
        return result;
    }
    private String writeDissipatoreForCatalog(HttpServletResponse response, Prodotto p, String path) throws IOException {
        Dissipatore dissipatore = (Dissipatore) p;
        String result =
                "<div class=\"product-card\">\n" +
                        "<div class=\"product-tumb inner-padding\">\n" +
                        "<img src=\"" + dissipatore.getUrl() + "\" alt=\"\">\n" +
                        "</div>\n" +
                        "<div class=\"product-details\">\n" +
                        "<span class=\"product-catagory\">Dissipatori</span>\n" +
                        "<h4><a href=\"" + path + "?Id=" + dissipatore.getID()+"&type="+ p.getTipo() + "\">" + dissipatore.getMarca() + " " + dissipatore.getModello() + "</a></h4>\n" +
                        "<p>" + dissipatore.getDescrizione() + "</p>\n" +
                        "<div class=\"product-bottom-details\">\n" +
                        "<div class=\"product-price\">" + dissipatore.getPrezzo() + "</div>\n" +
                        "<div class=\"product-links\">\n" +
                        "<a href=\"addCart?Id=" + p.getID() + "&quantity=" + 1 + "\"><i class=\"fa fa-shopping-cart\"></i></a>\n" +
                        "</div>\n" +
                        "</div>\n" +
                        "</div>\n" +
                        "</div>";
        return result;
    }
    private String writeCpuForCatalog(HttpServletResponse response, Prodotto p, String path) throws IOException {
        Cpu cpu = (Cpu) p;
        String result =
                "<div class=\"product-card\">\n" +
                        "<div class=\"product-tumb inner-padding\">\n" +
                        "<img src=\"" + cpu.getUrl() + "\" alt=\"\">\n" +
                        "</div>\n" +
                        "<div class=\"product-details\">\n" +
                        "<span class=\"product-catagory\">CPU</span>\n" +
                        "<h4><a href=\"" + path + "?Id=" + cpu.getID() +"&type="+ p.getTipo() + "\">" + cpu.getMarca() + " " + cpu.getModello() + "</a></h4>\n" +
                        "<p>" + cpu.getDescrizione() + "</p>\n" +
                        "<div class=\"product-bottom-details\">\n" +
                        "<div class=\"product-price\">" + cpu.getPrezzo() + "</div>\n" +
                        "<div class=\"product-links\">\n" +
                        "<a href=\"addCart?Id=" + cpu.getID() + "&quantity=" + 1 + "\"><i class=\"fa fa-shopping-cart\"></i></a>\n" +
                        "</div>\n" +
                        "</div>\n" +
                        "</div>\n" +
                        "</div>";
                        return result;
    }
    private String writeGpuForCatalog(HttpServletResponse response, Prodotto p, String path) throws IOException{
        Gpu gpu = (Gpu) p;
        String result = "<div class=\"product-card\">\n" +
                "<div class=\"product-tumb inner-padding\">\n" +
                "<img src=\"" + gpu.getUrl() + "\" alt=\"\">\n" +
                "</div>\n" +
                "<div class=\"product-details\">\n" +
                "<span class=\"product-catagory\">GPU</span>\n" +
                "<h4><a href=\"" + path + "?Id=" + gpu.getID()+ "&type="+ p.getTipo() +"\">" + gpu.getMarca() + " " + gpu.getModello() + "</a></h4>\n" +
                "<p>" + gpu.getDescrizione() + "</p>\n" +
                "<div class=\"product-bottom-details\">\n" +
                "<div class=\"product-price\">" + gpu.getPrezzo() + "</div>\n" +
                "<div class=\"product-links\">\n" +
                "<a href=\"addCart?Id=" + gpu.getID() + "&quantity=" + 1 + "\"><i class=\"fa fa-shopping-cart\"></i></a>\n" +
                "</div>\n" +
                "</div>\n" +
                "</div>\n" +
                "</div>";
                return result;
    }
    private String writeMoboForCatalog(HttpServletResponse response, Prodotto p, String path) throws IOException{
        Mobo mobo = (Mobo) p;
        String result =
                "<div class=\"product-card\">\n" +
                        "<div class=\"product-tumb inner-padding\">\n" +
                        "<img src=\"" + mobo.getUrl() + "\" alt=\"\">\n" +
                        "</div>\n" +
                        "<div class=\"product-details\">\n" +
                        "<span class=\"product-catagory\">Scheda madre</span>\n" +
                        "<h4><a href=\"" + path + "?Id=" + mobo.getID()+ "&type="+ p.getTipo() +"\">" + mobo.getMarca() + " " + mobo.getModello() + "</a></h4>\n" +
                        "<p>" + mobo.getDescrizione() + "</p>\n" +
                        "<div class=\"product-bottom-details\">\n" +
                        "<div class=\"product-price\">" + mobo.getPrezzo() + "</div>\n" +
                        "<div class=\"product-links\">\n" +
                        "<a href=\"addCart?Id=" + mobo.getID() + "&quantity=" + 1 + "\"><i class=\"fa fa-shopping-cart\"></i></a>\n" +
                        "</div>\n" +
                        "</div>\n" +
                        "</div>\n" +
                        "</div>";
        return result;
    }
    private String writeHddForCatalog(HttpServletResponse response, Prodotto p, String path) throws IOException{
        Hdd hdd = (Hdd) p;
        String result =
                "<div class=\"product-card\">\n" +
                        "<div class=\"product-tumb inner-padding\">\n" +
                        "<img src=\"" + hdd.getUrl() + "\" alt=\"\">\n" +
                        "</div>\n" +
                        "<div class=\"product-details\">\n" +
                        "<span class=\"product-catagory\">Hard disk</span>\n" +
                        "<h4><a href=\"" + path + "?Id=" + hdd.getID()+ "&type="+ p.getTipo() +"\">" + hdd.getMarca() + " " + hdd.getModello() + "</a></h4>\n" +
                        "<p>" + hdd.getDescrizione() + "</p>\n" +
                        "<div class=\"product-bottom-details\">\n" +
                        "<div class=\"product-price\">" + hdd.getPrezzo() + "</div>\n" +
                        "<div class=\"product-links\">\n" +
                        "<a href=\"addCart?Id=" + hdd.getID() + "&quantity=" + 1 + "\"><i class=\"fa fa-shopping-cart\"></i></a>\n" +
                        "</div>\n" +
                        "</div>\n" +
                        "</div>\n" +
                        "</div>";
        return result;
    }
    private String writeSsdForCatalog(HttpServletResponse response, Prodotto p, String path) throws IOException{
        Ssd ssd = (Ssd) p;
        String result = "<div class=\"product-card\">\n" +
                "<div class=\"product-tumb inner-padding\">\n" +
                "<img src=\"" + ssd.getUrl() + "\" alt=\"\">\n" +
                "</div>\n" +
                "<div class=\"product-details\">\n" +
                "<span class=\"product-catagory\">Solid state disk</span>\n" +
                "<h4><a href=\"" + path + "?Id=" + ssd.getID()+ "&type="+ p.getTipo() +"\">" + ssd.getMarca() + " " + ssd.getModello() + "</a></h4>\n" +
                "<p>" + ssd.getDescrizione() + "</p>\n" +
                "<div class=\"product-bottom-details\">\n" +
                "<div class=\"product-price\">" + ssd.getPrezzo() + "&euro;</div>\n" +
                "<div class=\"product-links\">\n" +
                "<a href=\"addCart?Id=" + ssd.getID() + "&quantity=" + 1 + "\"><i class=\"fa fa-shopping-cart\"></i></a>\n" +
                "</div>\n" +
                "</div>\n" +
                "</div>\n" +
                "</div>";
        return result;
    }
    private String writeRamForCatalog(HttpServletResponse response, Prodotto p, String path) throws IOException{
        Ram ram = (Ram) p;
        String result =
                "<div class=\"product-card\">\n" +
                        "<div class=\"product-tumb inner-padding\">\n" +
                        "<img src=\"" + ram.getUrl() + "\" alt=\"\">\n" +
                        "</div>\n" +
                        "<div class=\"product-details\">\n" +
                        "<span class=\"product-catagory\">RAM</span>\n" +
                        "<h4><a href=\"" + path + "?Id=" + ram.getID()+"&type="+ p.getTipo() + "\">" + ram.getMarca() + " " + ram.getModello() + "</a></h4>\n" +
                        "<p>" + ram.getDescrizione() + "</p>\n" +
                        "<div class=\"product-bottom-details\">\n" +
                        "<div class=\"product-price\">" + ram.getPrezzo() + "</div>\n" +
                        "<div class=\"product-links\">\n" +
                        "<a href=\"addCart?Id=" + ram.getID() + "&quantity=" + 1 + "\"><i class=\"fa fa-shopping-cart\"></i></a>\n" +
                        "</div>\n" +
                        "</div>\n" +
                        "</div>\n" +
                        "</div>";
        return result;
    }
    private String writeCaseForCatalog(HttpServletResponse response, Prodotto p, String path) throws IOException{
        Case case_ = (Case) p;
        String result =
                "<div class=\"product-card\">\n" +
                        "<div class=\"product-tumb inner-padding\">\n" +
                        "<img src=\"" + case_.getUrl() + "\" alt=\"\">\n" +
                        "</div>\n" +
                        "<div class=\"product-details\">\n" +
                        "<span class=\"product-catagory\">Case</span>\n" +
                        "<h4><a href=\"" + path + "?Id=" + case_.getID()+ "&type="+ p.getTipo() +"\">" + case_.getMarca() + " " + case_.getModello() + "</a></h4>\n" +
                        "<p>" + case_.getDescrizione() + "</p>\n" +
                        "<div class=\"product-bottom-details\">\n" +
                        "<div class=\"product-price\">" + case_.getPrezzo() + "</div>\n" +
                        "<div class=\"product-links\">\n" +
                        "<a href=\"addCart?Id=" + case_.getID() + "&quantity=" + 1 + "\"><i class=\"fa fa-shopping-cart\"></i></a>\n" +
                        "</div>\n" +
                        "</div>\n" +
                        "</div>\n" +
                        "</div>";
        return result;
    }
}
