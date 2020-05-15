package tpudlc.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.persistence.*;
import tpudlc.classes.Documento;
import tpudlc.gestores.GestorDocumentos;
import tpudlc.gestores.GestorPalabras;
import tpudlc.gestores.GestorPosteos;


@WebServlet(name = "Indexador", urlPatterns = {"/IndexarServlet"})
public class Indexador extends HttpServlet {
    
    
    @Inject private GestorDocumentos gestorDocumentos;
    @Inject private GestorPalabras gestorPalabras;
    @Inject private GestorPosteos gestorPosteos;
    
    
    private final String directorioDocumentos = "/home/facundo/repositorios/DocumentosTP1/";
    private final String archivoListaIndexados =  directorioDocumentos + "DirectorioListaIndexados/ListaIndexados.txt";
    
    
    private Integer visitas = 0;

    
    
    
    
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        File directorio = new File(directorioDocumentos);
        
        String[] nombresDocumentos = directorio.list();
        
        File[] documentos = directorio.listFiles();
        /*
        for(String nombreDocumento: nombresDocumentos){
            System.out.println(nombreDocumento);
        }*/
        
        for(File documento: documentos){
            if(documento.isDirectory()){
                System.out.println(documento.toString());
            }
        }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Indexador</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Indexador at " + request.getContextPath() + "</h1>");
            out.println("<h2>Indexando los documentos ubicados en el directorio: " + directorioDocumentos +"</h2>");
            out.println("<h3>Usted es el visitante número: " + visitas.toString() + "</h3>");
            /*
            List<Documento> documentos = gestorDocumentos.listarTodos();
            for (int i = 0; i < documentos.size(); i++){
                out.println("<h3>Nombre archivo:" + documentos.get(i).getNombreArchivo() +"</h3>");
            }*/
            visitas++;
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
