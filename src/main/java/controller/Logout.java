package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;

//servlet chiamata per il logout
@WebServlet(name = "Logout", value = "/Logout")
public class Logout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession ss = request.getSession();
        //Invalido la vecchia sessione
        ss.invalidate();
        //Richiamo la HomeServlet perchè c'è bisogno che il nuovo catalogo sia riempito,
        // altrimenti dopo il logout non sarà più visionabile perchè viene cancellato dalla sessione
        response.sendRedirect(".");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
