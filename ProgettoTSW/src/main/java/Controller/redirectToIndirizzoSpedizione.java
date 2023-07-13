package Controller;

import Model.Cliente_.Cliente;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;

//Redirect a jsp indirizzoSpedizione (protetta)
@WebServlet(name = "redirectToIndirizzoSpedizione", value = "/redirectToIndirizzoSpedizione")
public class redirectToIndirizzoSpedizione extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(((Cliente)request.getSession().getAttribute("cliente"))!=null)
        if(!((Cliente)request.getSession().getAttribute("cliente")).isAdministrator())
        request.getRequestDispatcher("./WEB-INF/indirizzoSpedizione.jsp").forward(request, response);
        else
            request.getRequestDispatcher("./WEB-INF/admin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
