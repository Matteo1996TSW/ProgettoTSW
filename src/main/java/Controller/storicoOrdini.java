package Controller;

import Model.Cliente_.Cliente;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;

//Reindirizza alla jsp dello storico ordini (protetta)
@WebServlet(name = "storicoOrdini", value = "/storicoOrdini")
public class storicoOrdini extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(((Cliente)request.getSession().getAttribute("cliente"))!=null)
        if(((Cliente)request.getSession().getAttribute("cliente")).isAdministrator())
            request.getRequestDispatcher("./WEB-INF/admin.jsp").forward(request, response);
        request.getRequestDispatcher("WEB-INF/storicoOrdini.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
