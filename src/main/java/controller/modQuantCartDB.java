package controller;

import javax.servlet.*;
import javax.servlet.http.*;

import model.Prodotto;
import model.ProdottoDAO;
import model.CATALOGO_.Catalogo;
import model.Carrello_.Carrello;
import model.Carrello_.CarrelloDAO;
import model.Cliente_.Cliente;

import javax.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "modQuantCartDB", value = "/modQuantCartDB")
public class modQuantCartDB extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession ss = request.getSession();
        Integer quant = Integer.parseInt(request.getParameter("attr_newQuant"));
        Integer id = Integer.parseInt(request.getParameter("attr_id"));
        CarrelloDAO carrelloDAO = new CarrelloDAO();
        Carrello carrelloSessione = ((Carrello) ss.getAttribute("carrello"));
        Cliente cliente = (Cliente) ss.getAttribute("cliente");
        Catalogo catalogoSessione = (Catalogo) ss.getAttribute("catalogo");
        Integer oldQuant = Integer.parseInt(request.getParameter("attr_OldQuant")); //catalogoDB.getCatalogo().size() - catalogoSessione.getCatalogo().size();
        Prodotto p = null;

        if(id==null || quant==null || ss==null || oldQuant==null)
            request.getRequestDispatcher("WEB-INF/error-page.jsp").forward(request, response);
        
        if(((Cliente)request.getSession().getAttribute("cliente"))!=null &&
        		((Cliente)request.getSession().getAttribute("cliente")).isAdministrator())
            		request.getRequestDispatcher("./WEB-INF/admin.jsp").forward(request, response);

        //Aggiorna il catalogo nella sessione
        assert catalogoSessione != null;
        for (Prodotto pcart : catalogoSessione.getCatalogo()) {
            if (pcart.getID() == id)
                pcart.setQuantita((pcart.getQuantita() + oldQuant) - quant);
            ss.setAttribute("catalogo", catalogoSessione);
        }

            //Aggiorna il carrello della sessione
        assert carrelloSessione != null;
        carrelloSessione.setPrezzo(0);
            for (Prodotto pcarr : carrelloSessione.getCarrello()) {
                if (pcarr.getID() == id){
                    pcarr.setQuantita(quant);
                    p = pcarr;
                }
                carrelloSessione.setPrezzo(carrelloSessione.getPrezzo()+(pcarr.getPrezzo()* pcarr.getQuantita()));
            }
            ss.setAttribute("carrello", carrelloSessione);

            //Se il cliente è loggato aggiorna il carrello nel DB
            if (cliente != null) {
                carrelloDAO.modCart(carrelloSessione, p);
            }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGet(request,response);
    }
}
