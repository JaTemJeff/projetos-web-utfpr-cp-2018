/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utfpr;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Aluno
 */
@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {

    public void doGet (HttpServletRequest req,
                       HttpServletResponse res) throws IOException {
        PrintWriter writer = res.getWriter();
        writer.println("<!DOCTYPE HTML>");
        writer.println("<html>");
        writer.println("    <head>");
        writer.println("        <meta http-equiv=\"content-type\"");
        writer.println("              content=\"text/html; charset=utf-8\"/>");
        writer.println("        <title>Login</title>");
        writer.println("    </head>");
        writer.println("    <body>");
        writer.println("        <h1>Login</h1>");
        writer.println("        <form action=\"login\" method=\"POST\">");
        writer.println("        <fieldset>");
        writer.println("            <legend>Login</legend>");
        writer.println("            <input type=\"text\" name=\"usuario\" value=\"\">");
        writer.println("            <input type=\"password\" name=\"senha\" value=\"\">");
        writer.println("            <input type=\"submit\" value=\"logar\">");
        writer.println("        </fieldset>");
        writer.println("        </form>");
        writer.println("    </body>");
        writer.println("</html>");
    }

    public void doPost (HttpServletRequest req,
                        HttpServletResponse res) throws IOException {
        if (req.getParameter("usuario").equals("admin") &&
            req.getParameter("senha").equals("admin")) {
            req.getSession().setAttribute("logado", new Boolean(true));
            req.getSession().setAttribute("usuario", "admin");
            res.getWriter().println("<h1>...</h1>");
            res.sendRedirect("uploadvideo");
        } else {
            res.setStatus(HttpServletResponse.SC_FORBIDDEN);
            res.getWriter().println("<h1>You shall not pass!!!</h1>");
        }
    }
}