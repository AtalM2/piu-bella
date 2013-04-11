package fr.univnantes.atal.web.piubella.servlets;

import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import fr.univnantes.atal.web.piubella.model.JSONInfo;
import fr.univnantes.atal.web.piubella.persistence.PMF;
import java.io.IOException;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FetchJson extends HttpServlet {

    /**
     * Processes requests for the HTTP
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
        PersistenceManager pm = PMF.get().getPersistenceManager();
        Query q = pm.newQuery(JSONInfo.class);
        try {
            JSONInfo jsonInfo;
            List<JSONInfo> results =
                    (List<JSONInfo>) q.execute();
            if (results.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                return;
            } else {
                jsonInfo = results.get(0);
            }

            BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
            response.setCharacterEncoding("UTF-8");
            blobstoreService.serve(jsonInfo.getBlobKey(), response);

        } finally {
            pm.close();
        }
    }
}
