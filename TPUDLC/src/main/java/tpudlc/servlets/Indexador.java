/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpudlc.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.persistence.*;
import tpudlc.classes.Documento;
import org.postgresql.Driver;

/**
 *
 * @author facundo
 */
@WebServlet(name = "Indexador", urlPatterns = {"/Indexar"})
public class Indexador extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String directorioDocumentos = "/home/facundo/repositorios/DocumentosTP1/";
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("tpudlc_persistencia");
                        //(EntityManagerFactory)getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();
        
        try{
            String idDoc = "ESTO ES UNA PRUEBA";
            
            em.getTransaction().begin();
            em.persist(new Documento(idDoc));
            em.getTransaction().commit();
        } finally {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            em.close();
        }
        
        
        
        //System.out.println("ID DEL DOCUMENTO: " + documento.getIdDocumento().toString());
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Indexador</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Indexador at " + request.getContextPath() + "</h1>");
            out.println("<h2>Indexando los documentos ubicados en el directorio: " + directorioDocumentos +"</h2>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
