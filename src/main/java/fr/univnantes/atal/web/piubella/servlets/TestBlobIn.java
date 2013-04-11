/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univnantes.atal.web.piubella.servlets;

import com.google.appengine.api.files.AppEngineFile;
import com.google.appengine.api.files.FileService;
import com.google.appengine.api.files.FileServiceFactory;
import com.google.appengine.api.files.FileWriteChannel;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.channels.Channels;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;

/**
 *
 * @author mog
 */
public class TestBlobIn extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FileService fileService = FileServiceFactory.getFileService();

        // Create a new Blob file with mime-type "text/plain"
        AppEngineFile file = fileService.createNewBlobFile("text/plain");

        // Open a channel to write to it
        boolean lock = true;
        FileWriteChannel writeChannel = fileService.openWriteChannel(file, lock);

        // Different standard Java ways of writing to the channel
        // are possible. Here we use a PrintWriter:
        try (OutputStream os = Channels.newOutputStream(writeChannel)) {
            JsonFactory f = new JsonFactory();
            JsonGenerator g = f.createJsonGenerator(os);
            g.writeStartObject();
            g.writeStringField("first", "Joe");
            g.writeEndObject();
            g.close();
        }

        writeChannel.closeFinally();

        try (PrintWriter out = response.getWriter()) {
            out.println(file.getFullPath());
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
