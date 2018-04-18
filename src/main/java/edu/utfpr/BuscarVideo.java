package edu.utfpr;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "BuscarVideo", urlPatterns = {"/buscarvideo"})
public class BuscarVideo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        HttpSession session = request.getSession();
        if (session.getAttribute("logado") != null){
            writer.println("<!DOCTYPE HTML>");
            writer.println("<html>");
            writer.println("    <head>");
            writer.println("        <meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\" />");
            writer.println("        <title>Buscar V�deo</title>");
            writer.println("    </head>");
            writer.println("    <body>");
            writer.println("        <h1>Buscar v�deo:</h1>");
            writer.println("        <form action=\"buscarvideo\" method=\"POST\">");
            writer.println("            <input type=\"text\" name=\"arquivo\" value=\"\" required>");
            writer.println("            <input type=\"submit\" name=\"enviar\" value=\"submit\" />");
            writer.println("        </form>");
            if (request.getParameter("nome") != null){
            String video = request.getParameter("nome").toString();
                writer.println("<ul>");
                writer.println("<li>"+
                "<video width=\"640\" height=\"560\" controls>"+
                    "<source src=\"uploads/" + video + ".mp4\" type=\"video/mp4\">"+
                "</video></li>");
                writer.println("</ul>");
            } else if (request.getParameter("nome") != null && 
                        request.getParameter("nome").equals("naoencontrado")) {
                writer.println("<script>alert(\"V�deo n�o encontrado\");</script>");
            }
            writer.println("        <form action=\"listavideos\" method=\"GET\">");
            writer.println("            <input type=\"submit\" value=\"Listar v�deos\">");
            writer.println("        </form>");
            writer.println("        <form action=\"uploadvideo\" method=\"GET\">");
            writer.println("            <input type=\"submit\" value=\"Upload de v�deos\">");
            writer.println("        </form>");
            writer.println("    </body>");
            writer.println("</html>");
        } else {
            response.sendRedirect("login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nome_video = request.getParameter("arquivo").toString();
        ConexaoBD bd = new ConexaoBD();
        boolean salvo = bd.buscarVideo(nome_video);
        if(salvo){
            response.sendRedirect("buscarvideo?nome=" + nome_video);
        } else {
            response.sendRedirect("buscarvideo?nome=naoencontrado");
            response.getWriter().println("<script>alert(\"Video nao encontrado\");</script>");
        }
    }


}
