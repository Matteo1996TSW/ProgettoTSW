package Controller;

import Model.Carrello_.Carrello;
import Model.Cliente_.Cliente;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;
//Redireziona al carrello
@WebServlet(name = "CarrelloServlet", value = "/CarrelloServlet")
public class CarrelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Carrello carrello = (Carrello) session.getAttribute("carrello");

        if((Cliente)request.getSession().getAttribute("cliente")!=null)
            if(((Cliente)request.getSession().getAttribute("cliente")).isAdministrator())
                request.getRequestDispatcher("./WEB-INF/admin.jsp").forward(request, response);

        if(carrello!=null){
            response.sendRedirect("carrello.jsp");
        }else{
            request.getRequestDispatcher("WEB-INF/error-page.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
