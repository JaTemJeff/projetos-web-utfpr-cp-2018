package edu.utfpr.controller;

import com.google.gson.Gson;
import edu.utfpr.model.bancodedados.VideoModel;
import edu.utfpr.model.entidades.Video;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.spy.memcached.AddrUtil;
import net.spy.memcached.MemcachedClient;
import org.postgresql.util.PSQLException;

@WebServlet(urlPatterns="/videos")
public class Videos extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MemcachedClient client = new MemcachedClient(AddrUtil.getAddresses("127.0.0.1:11211"));
        VideoModel model = new VideoModel();
        if (request.getParameter("busca").equals("")){
            if (client.get("?") == null){
                List<Video> lista = model.listar(request.getParameter("busca"));
                response.setContentType("application/json");
                response.getWriter().print(new Gson().toJson(lista));
                client.add("?", 120, new Gson().toJson(lista));
            } else {
               response.getWriter().print(client.get("?"));
            }
        } else {
            if(client.get(request.getParameter("busca")) == null){
                List<Video> lista = model.listar(request.getParameter("busca"));
                response.setContentType("application/json");
                response.getWriter().print(new Gson().toJson(lista));
                client.set(request.getParameter("busca"), 120, new Gson().toJson(lista));
            } else {
                response.getWriter().print(client.get(request.getParameter("busca")));
            }
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            VideoModel model = new VideoModel();
            model.salvarVideo(request.getParameter("nome"));
            MemcachedClient client = new MemcachedClient(AddrUtil.getAddresses("127.0.0.1:11211"));
            client.flush();
        } catch (PSQLException ex) {
            Logger.getLogger(Videos.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
}
