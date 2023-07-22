package Controller;

import Model.Cliente_.Cliente;
import Model.Cliente_.ClienteDAO;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

//Modifica le info della spedizione dell'utente
@WebServlet(name = "modInfoSpedizione", value = "/modInfoSpedizione")
public class modInfoSpedizione extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String via = request.getParameter("input_via");
        String provincia = request.getParameter("input_provincia");
        String citta = request.getParameter("input_citta");
        Integer cap = Integer.parseInt(request.getParameter("input_cap"));
        HttpSession ss = request.getSession();
        Cliente cliente = (Cliente) ss.getAttribute("cliente");
        ClienteDAO cDAO = new ClienteDAO();
        boolean success;

        if(via==null || provincia==null || citta==null || cap==null || ss==null)
            request.getRequestDispatcher("WEB-INF/error-page.jsp").forward(request, response);
        if(((Cliente)request.getSession().getAttribute("cliente"))!=null)
        if(((Cliente)request.getSession().getAttribute("cliente")).isAdministrator())
            request.getRequestDispatcher("./WEB-INF/admin.jsp").forward(request, response);

        //Aggiorna le info
        assert cliente != null;
        cliente.setVia(via);
        cliente.setProvincia(provincia);
        cliente.setCitta(citta);
        cliente.setCap(cap);

        success = cDAO.updateInfoSpedizioneCliente(cliente);

        //Se l'aggiornamento ha avuto successo aggiorno le info nella sessione
        if(success){
            request.setAttribute("queryUpdateInfoCliente", true);
            ss.setAttribute("cliente", cliente);
        }
        else
            request.setAttribute("queryUpdateInfoCliente",false);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
