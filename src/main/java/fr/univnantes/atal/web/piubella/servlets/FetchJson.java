package fr.univnantes.atal.web.piubella.servlets;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class FetchJson extends HttpServlet {

    //PersistenceManager pm = PMF.get().getPersistenceManager();
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
        URL url = new URL("http://data.nantes.fr/api/publication/"
                + "JOURS_COLLECTE_DECHETS_VDN/JOURS_COLLECTE_DECHETS_VDN_STBL/"
                + "content/?format=json");
        try (BufferedReader in = new BufferedReader(new InputStreamReader(
                     url.openStream(), "UTF-8"))) {
            String inputLine;
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter pw = response.getWriter();
            while ((inputLine = in.readLine()) != null) {
                pw.println(inputLine);
            }
        }
    }
}
