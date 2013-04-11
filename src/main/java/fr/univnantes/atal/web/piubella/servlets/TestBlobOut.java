/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univnantes.atal.web.piubella.servlets;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.files.AppEngineFile;
import com.google.appengine.api.files.FileService;
import com.google.appengine.api.files.FileServiceFactory;
import com.google.appengine.api.files.FileWriteChannel;
import fr.univnantes.atal.web.piubella.model.JSONInfo;
import fr.univnantes.atal.web.piubella.persistence.PMF;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mog
 */
public class TestBlobOut extends HttpServlet {

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
            FileService fileService = FileServiceFactory.getFileService();
            AppEngineFile file = new AppEngineFile(jsonInfo.getPath().getValue());
            FileWriteChannel wc = fileService.openWriteChannel(file, true);
            wc.close();
            BlobKey bk = fileService.getBlobKey(file);
            blobstoreService.serve(bk, response);

        } finally {
            pm.close();
        }
    }
}
