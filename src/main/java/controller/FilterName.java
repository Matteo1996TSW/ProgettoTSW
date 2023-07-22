package controller;

import javax.servlet.*;
import javax.servlet.http.*;

import model.CATALOGO_.Catalogo;
import model.CATALOGO_.CatalogoDAO;
import model.Cliente_.Cliente;
import model.Prodotto;

import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

//Filtra il catalogo per marca o modello
@WebServlet(name = "FilterName", value = "/FilterName")
public class FilterName extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String text = request.getParameter("input_cerca");
        String scelta = request.getParameter("radio_scelta");
        HttpSession ss = request.getSession();

        if(ss==null)
            request.getRequestDispatcher("WEB-INF/error-page.jsp").forward(request, response);

        List<Prodotto> listaProdotti = null;
        CatalogoDAO cat = new CatalogoDAO();
        listaProdotti = cat.doRetriveAll();
        Catalogo catalogo =  new Catalogo();
        catalogo.setCatalogo(listaProdotti);
        
        Catalogo oldCatalogo = (Catalogo) ss.getAttribute("catalogo");
        
        if (oldCatalogo == null) oldCatalogo = catalogo;

        Catalogo c = null;

        if (scelta.equals("Marca")) {
            c = oldCatalogo.filterByMarca(text);

        } else if (scelta.equals("Modello")) {
            c = oldCatalogo.filterByModello(text);
        }

        //Ancora una volta non può essere null qui. Tuttavia tentiamo di coprire il caso di un errore catastrofico nel server
        assert c != null:"Il catalogo non dovrebbe essere null qui. Prova a ricaricare il sito";

        if (!c.isEmpty()) {
            ss.setAttribute("filterCatalog", c);
            ss.setAttribute("printCatalog", c);
            //Stampa il catalogo
            response.sendRedirect("AsincCatalogPrinter");
        } else {
            response.getWriter().println("<div class=\"error-message\">Nessun risultato trovato</div>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}


