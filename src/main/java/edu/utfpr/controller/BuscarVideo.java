package edu.utfpr.controller;

import edu.utfpr.model.bancodedados.VideoModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ResourceBundle;
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
        request.getRequestDispatcher("WEB-INF/view/buscarvideo.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nome_video = request.getParameter("arquivo").toString();
        VideoModel bd = new VideoModel();
        ResourceBundle bundle = (ResourceBundle) request.getAttribute("bundle");
        
        /*boolean salvo = bd.buscarVideo(nome_video);
        if(salvo){
            response.sendRedirect("buscarvideo?nome=" + nome_video);
        } else {
            String naoencontrado = "Video nao encontrado";
            request.setAttribute("naoencontrado", bundle.getString("mensagem_inexistente"));
            request.getRequestDispatcher("WEB-INF/view/buscarvideo.jsp").include(request, response);
        }
        */
    }


}
