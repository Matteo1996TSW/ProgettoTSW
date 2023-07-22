package Controller;

import Model.Carrello_.Carrello;
import Model.Carrello_.CarrelloDAO;
import Model.CATALOGO_.Catalogo;
import Model.Cliente_.Cliente;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

//Elimina dal carrello un prodotto
@WebServlet(name = "removeCart", value = "/removeCart")
public class removeCart extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer idProdotto = Integer.parseInt(request.getParameter("idProdotto"));
        HttpSession session = request.getSession();
        Carrello carrelloSession = (Carrello) session.getAttribute("carrello");
        Catalogo catalogoSessione = (Catalogo) session.getAttribute("catalogo");
        Cliente cliente = (Cliente) session.getAttribute("cliente");

        if(idProdotto==null || session==null || carrelloSession==null || catalogoSessione==null)
            request.getRequestDispatcher("WEB-INF/error-page.jsp").forward(request, response);
        if(((Cliente)request.getSession().getAttribute("cliente"))!=null)
        if(((Cliente)request.getSession().getAttribute("cliente")).isAdministrator())
            request.getRequestDispatcher("./WEB-INF/admin.jsp").forward(request, response);

        //Rimuoviamo il prodotto dal carello di sessione e riaggiungiamo la quantità al catalogo di sessione
        int quantity = carrelloSession.removeProductByIdFromSession(idProdotto);
        catalogoSessione.updateQuantity(quantity, idProdotto);
        //Aggiorniamo dunque il catalogo della sessione
        session.setAttribute("catalogo", catalogoSessione);

        //Quindi rimuove il pezzo dal carrello
        if(cliente != null){
            CarrelloDAO.delCarrelloFromComporre(carrelloSession);
        }
        response.sendRedirect("carrello.jsp");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){

    }
}
