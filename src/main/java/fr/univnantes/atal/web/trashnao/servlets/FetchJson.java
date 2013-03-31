package fr.univnantes.atal.web.trashnao.servlets;

import au.com.bytecode.opencsv.CSVReader;
import fr.univnantes.atal.web.trashnao.model.Address;
import fr.univnantes.atal.web.trashnao.model.CollectDay;
import fr.univnantes.atal.web.trashnao.persistence.PMF;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLConnection;
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
        //System.setProperty("http.proxyHost", "cache.wifi.univ-nantes.fr");
        //System.setProperty("http.proxyPort", "3128");
        URL url = new URL("http://data.nantes.fr/api/publication/"
                + "JOURS_COLLECTE_DECHETS_VDN/JOURS_COLLECTE_DECHETS_VDN_STBL/"
                + "content/?format=json");
        //Collection<Address> addresses = new ArrayList<>();
        URLConnection yc = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                yc.getInputStream()));
        String inputLine;
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();
        while ((inputLine = in.readLine()) != null)
             pw.println(inputLine);
        in.close();
    }
}
