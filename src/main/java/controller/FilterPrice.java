package controller;

import javax.servlet.*;
import javax.servlet.http.*;

import model.CATALOGO_.Catalogo;

import javax.servlet.annotation.*;

import java.io.IOException;

//Filtra il catalogo per prezzo
@WebServlet(name = "FilterPrice", value = "/FilterPrice")
public class FilterPrice extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer price = Integer.parseInt(request.getParameter("input_prezzo"));
        HttpSession ss = request.getSession();

        if(ss==null || price==null)
            request.getRequestDispatcher("WEB-INF/error-page.jsp").forward(request, response);

        //Se il catalogo è già stato filtrato per nome allora questo è il catalogo
        Catalogo filteredCatalog = (Catalogo) ss.getAttribute("filterCatalog");
        //Altrimenti se questo non è ancora successo utilizziamo l'intero catalogo
        if (filteredCatalog == null){
            filteredCatalog = (Catalogo) ss.getAttribute("catalogo");
        }

        //Tentiamo di coprire il caso di un errore catastrofico nel server
        assert filteredCatalog != null:"Il catalogo filtrato non dovrebbe essere null qui. Prova a ricaricare il sito";

        Catalogo c = filteredCatalog.filterByPrezzo(price);

        //Ancora una volta non può essere null qui. Tuttavia tentiamo di coprire il caso di un errore catastrofico nel server
        assert c != null:"Il catalogo non dovrebbe essere null qui. Prova a ricaricare il sito";

        ss.setAttribute("printCatalog", c);
        //Stampa il catalogo
        response.sendRedirect("AsincCatalogPrinter");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
