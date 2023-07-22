package controller;

import javax.servlet.*;
import javax.servlet.http.*;

import model.Prodotto;
import model.Archiviazione_.HDD_.Hdd;
import model.Archiviazione_.SDD_.Ssd;
import model.CASE_.Case;
import model.CATALOGO_.Catalogo;
import model.CPU_.Cpu;
import model.Cliente_.Cliente;
import model.DISSIPATORE_.Dissipatore;
import model.GPU_.Gpu;
import model.MOBO_.Mobo;
import model.PSU_.Psu;
import model.RAM_.Ram;

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

        if(cliente != null && cliente.isAdministrator()){
                path = "redirectToAdminPage";
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
    	doGet(request, response);

    }

    private String doSwCase(Prodotto p, HttpServletResponse response, String path) throws IOException {
        switch (p.getTipo()) {
            case "CPU":
            	return ProductWriter.writeForCatalog(p, path, "CPU");
            case "CASE":
            	return ProductWriter.writeForCatalog(p, path, "Case");
            case "DISSIPATORE":
            	return ProductWriter.writeForCatalog(p, path, "Dissipatore");
            case "PSU":
            	return ProductWriter.writeForCatalog(p, path, "PSU");
            case "MOBO":
            	return ProductWriter.writeForCatalog(p, path, "MOBO");
            case "RAM":
            	return ProductWriter.writeForCatalog(p, path, "RAM");
            case "HDD":
            	return ProductWriter.writeForCatalog(p, path, "HDD");
            case "SSD":
            	return ProductWriter.writeForCatalog(p, path, "SSD");
            case "GPU":
            	return ProductWriter.writeForCatalog(p, path, "GPU");
            default:
                return "Errore";
        }
    }
    
    

	abstract class ProductWriter {
        protected static String writeForCatalog(Prodotto p, String path, String category) throws IOException {
            String result =
                    "<div class=\"product-card\">\n" +
                    "<div class=\"product-tumb inner-padding\">\n" +
                    "<img src=\"" + p.getUrl() + "\" alt=\"\">\n" +
                    "</div>\n" +
                    "<div class=\"product-details\">\n" +
                    "<span class=\"product-catagory\">" + category + "</span>\n" +
                    "<h4><a href=\"" + path + "?Id=" + p.getID() + "&type=" + p.getTipo() + "\">" + p.getMarca() + " " + p.getModello() + "</a></h4>\n" +
                    "<p>" + p.getDescrizione() + "</p>\n" +
                    "<div class=\"product-bottom-details\">\n" +
                    "<div class=\"product-price\">" + p.getPrezzo() + "</div>\n" +
                    "<div class=\"product-links\">\n" +
                    "<a href=\"addCart?Id=" + p.getID() + "&quantity=" + 1 + "\"><i class=\"fa fa-shopping-cart\"></i></a>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "</div>";
            return result;
        }
    }

}
