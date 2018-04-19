package edu.utfpr;

import edu.utfpr.entidadesedao.Usuario;
import edu.utfpr.entidadesedao.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {
    public void doGet (HttpServletRequest req,
                       HttpServletResponse res) throws IOException {
        PrintWriter writer = res.getWriter();                 
        if (req.getParameter("save") != null) {
            if (req.getParameter("save").equals("true")) {
                res.getWriter().println("<script>alert(\"Usuario cadastrado\");</script>");
            }
        }
        if (req.getParameter("existe") != null) {
            if (req.getParameter("existe").equals("false")) {
                res.getWriter().println("<script>alert(\"Usuario nao encontrado\");</script>");
            }
        }
        writer.println("<!DOCTYPE HTML>");
        writer.println("<html>");
        writer.println("    <head>");
        writer.println("        <meta http-equiv=\"content-type\"");
        writer.println("              content=\"text/html; charset=utf-8\"/>");
        writer.println("        <title>Login</title>");
        writer.println("        <link rel=\"stylesheet\" href=\"styles.css\">");
        writer.println("    </head>");
        writer.println("    <body>");
        writer.println("        <h1>Login</h1>");
        writer.println("        <form action=\"login\" method=\"POST\">");
        writer.println("            <label for=\"usuario\">Email:</label>");
        writer.println("            <input type=\"email\" name=\"usuario\" value=\"\" required>");
        writer.println("            <label for=\"senha\">Senha:</label>");
        writer.println("            <input type=\"password\" name=\"senha\" value=\"\" required>");
        writer.println("            <input type=\"submit\" value=\"logar\">");
        writer.println("        </form>");
        writer.println("        <form action=\"cadastro\" method=\"GET\">");
        writer.println("            <input type=\"submit\" value=\"Cadastro\">");
        writer.println("        </form>");
        writer.println("    </body>");
        writer.println("</html>");
    }

    public void doPost (HttpServletRequest req,
                        HttpServletResponse res) throws IOException {
        Usuario u = new Usuario();
        UsuarioDAO uDAO = new UsuarioDAO();
        u.setEmail(req.getParameter("usuario"));
        u.setSenha(req.getParameter("senha"));
        try {
            uDAO.buscarUsuarioEmailSenha(u.getEmail(), u.getSenha());
            req.getSession().setAttribute("logado", new Boolean(true));
            res.sendRedirect("uploadvideo");
        } catch (Exception ex) {
            res.sendRedirect("login?existe=false");            
        }
    }
}
