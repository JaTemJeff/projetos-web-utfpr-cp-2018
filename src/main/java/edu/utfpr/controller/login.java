package edu.utfpr.controller;

import edu.utfpr.model.entidades.Usuario;
import edu.utfpr.model.bancodedados.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/login")
public class Login extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {        
        request.getRequestDispatcher("WEB-INF/view/login.jsp").include(request, response);
    }

     protected void doPost(HttpServletRequest request, HttpServletResponse response)  
                    throws ServletException, IOException {  
       
        Usuario u = new Usuario();
        UsuarioDAO uDAO = new UsuarioDAO();
         ResourceBundle bundle = (ResourceBundle) request.getAttribute("bundle");
        
        try {
            u.setEmail(request.getParameter("usuario"));
        } catch (Exception ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            u.setSenha(request.getParameter("senha"));
        } catch (Exception ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            uDAO.buscarUsuarioEmailSenha(u.getEmail(), u.getSenha());
            request.getSession().setAttribute("logado", new Boolean(true));
            request.getSession().setAttribute("usuario", u.getEmail());

            response.sendRedirect("index");
        } catch (Exception ex) {
            response.sendRedirect("login");            
        }
    }
}
