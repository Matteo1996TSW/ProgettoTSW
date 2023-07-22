package controller;

import javax.servlet.*;
import javax.servlet.http.*;

import model.PasswordEncrypter;
import model.Cliente_.Cliente;
import model.Cliente_.ClienteDAO;

import javax.servlet.annotation.*;

import java.io.IOException;
import java.sql.*;

@WebServlet(name = "Login", value = "/Login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mail = request.getParameter("Mail");
        String password = PasswordEncrypter.encryptThisString(request.getParameter("Password"));
        ClienteDAO CDAO = new ClienteDAO();

        Cliente c = null;
        c = CDAO.doRetrieveByMail(mail);
        //Se mail e password sono valide controlla che la coppia sia corretta con isCorrectLogin()
		if (mail == null || password == null || !CDAO.isCorrectLogin(mail, password)) {
		    request.setAttribute("loginErr", "Cliente non trovato o password errata");
		    //Rimanda alla pagina di login
		    request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		//Se la coppia è corretta, prendi il cliente dal DB e lo metti nella sessione
		else{
		    //Prendi Le info dal DB e crea l' oggetto cliente da tenere in sessione
		    HttpSession session = request.getSession();
		    //Setta come attributo della sessione il cliente
		    session.setAttribute("cliente", c);
		    if(c!=null && c.isAdministrator()) {
		            request.getRequestDispatcher("./WEB-INF/admin.jsp").forward(request, response);
		    }
		    else{
		        response.sendRedirect(".");
		    }
		}
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Prima di passare al doGet fai l'hash della password
        doGet(request, response);
    }
}
