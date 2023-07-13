package Controller;

import Model.Carrello_.Carrello;
import Model.Carrello_.CarrelloDAO;
import Model.CATALOGO_.Catalogo;
import Model.CATALOGO_.CatalogoDAO;
import Model.Cliente_.Cliente;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

//Servlet di iniziallizazione
@WebServlet(name = "HomeServlet", value = "/HomeServlet")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Take session
        HttpSession ss = request.getSession();
        //ss.setMaxInactiveInterval(10);
        //Take Attribute from session
        Cliente c = (Cliente) ss.getAttribute("cliente");
        //Create new instance
        CatalogoDAO serviceCatalogo = new CatalogoDAO();
        CarrelloDAO serviceCarrello = new CarrelloDAO();
        Catalogo catalogo = new Catalogo();

        try {
            catalogo.setCatalogo(serviceCatalogo.doRetriveAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //Check if session is new
        if (ss.isNew()) {
            //if the session is new we need to take all elements from database, set it in a catalogo, create a new empty carrello, and add catalogo and carrello as session Attribute
            Carrello carrello = new Carrello();
            ss.setAttribute("catalogo", catalogo);
            ss.setAttribute("carrello", carrello);
        }
        //The session isn't new
        else {
            //If the session isn't new we need to check if the Cliente is null or not to verify if he's logged in
            if (c != null) {
                //Cliente is logged
                //Take Carrello from session
                Carrello carrello = (Carrello) ss.getAttribute("carrello");
                //create a new Carrello where we put carrello from DataBase
                Carrello carrelloDB = null;
                try {
                    carrelloDB = serviceCarrello.doRetriveByMailCliente(c.getMail());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                };
                //If session Carrello isn't null we need to do join between session Carrello and DataBase Carrello
                if (carrello != null) {
                    //Do join
                    carrello.joinCarrelli(carrelloDB);
                    //Checks if the quantity required by the cart is less than the quantity avilable
                    try {
                        carrello.doCheckList(catalogo);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        //Update carrello in the DB
                        CarrelloDAO.delCarrelloFromComporre(carrello);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    //Use Catalogo to store the session Catalogo and update the quantity
                    //Sets the quantity in catalogo as the quantity in catalogo minus the quantity in carrello
                    catalogo.aggiornaQuantita(carrello);
                    //set session attribute catalogo and carrello
                    ss.setAttribute("catalogo", catalogo);
                    ss.setAttribute("carrello", carrello);
                } else {
                    //Create new Catalogo and use it to store the session Catalogo and update the quantity
                    catalogo.aggiornaQuantita(carrelloDB);
                    //set session attribute catalogo and carrello
                    ss.setAttribute("catalogo", catalogo);
                    ss.setAttribute("carrello", carrelloDB);
                }
            } else {
                //il cliente non è loggato
                Carrello carrelloSession = (Carrello) ss.getAttribute("carrello");
                ss.setAttribute("catalogo",catalogo);
                if (carrelloSession == null) {
                    Carrello carrello = new Carrello();
                    ss.setAttribute("carrello", carrello);
                }
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
