package controller;

import javax.servlet.*;
import javax.servlet.http.*;

import model.Cliente_.Cliente;
import model.Cliente_.ClienteDAO;

import javax.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

//Aggiorna le info personali del cliente (Tranne la mail)
@WebServlet(name = "updateInfoCliente", value = "/updateInfoCliente")
public class updateInfoCliente extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nick = request.getParameter("input_nick");
        String tel = request.getParameter("input_tel");
        HttpSession ss = request.getSession();
        ClienteDAO cDAO = new ClienteDAO();
        Cliente cliente = (Cliente) ss.getAttribute("cliente");
        boolean success;

        if(nick==null || tel==null || ss==null || cliente==null)
            request.getRequestDispatcher("WEB-INF/error-page.jsp").forward(request, response);
        if(((Cliente)request.getSession().getAttribute("cliente")).isAdministrator())
            request.getRequestDispatcher("./WEB-INF/admin.jsp").forward(request, response);


        cliente.setNickname(nick);
        cliente.setTel(tel);

        success = cDAO.updateInfoPersonaliCliente(cliente);

        //Se l'aggiornamento è andato a buon fine setto l'utente anche nella sessione
        if(success){
            ss.setAttribute("queryUpdateInfoCliente", true);
            ss.setAttribute("cliente", cliente);
        }
        else ss.setAttribute("queryUpdateInfoCliente",false);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
