/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univnantes.atal.web.piubella.servlets;

import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobInfoFactory;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.blobstore.BlobstoreServicePb;
import com.google.appengine.api.datastore.Text;
import com.google.appengine.api.files.AppEngineFile;
import com.google.appengine.api.files.FileService;
import com.google.appengine.api.files.FileServiceFactory;
import fr.univnantes.atal.web.piubella.model.JSONInfo;
import fr.univnantes.atal.web.piubella.persistence.PMF;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
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
public class RescueJSONInfo extends HttpServlet {

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
        PersistenceManager pm = PMF.get().getPersistenceManager();
        Query q = pm.newQuery(JSONInfo.class);
        try {
            JSONInfo jsonInfo;
            List<JSONInfo> results =
                    (List<JSONInfo>) q.execute();
            if (results.isEmpty()) {
                jsonInfo = new JSONInfo();
                pm.makePersistent(jsonInfo);
            } else {
                jsonInfo = results.get(0);
            }
            jsonInfo.setPath(new Text("/blobstore/writable:AD8BvuntOLUCY-DrlIqYuxg5_RmJWVyMPn01cqKYMgKIT8IZMf2niasYBhzTNWUTYw35c8NjpSHtS-AW6qdo5DdX84G3tvS9aaoXhNN-MPJkgBAGCcnrbWOhjOcEz38Y3LVNRIMS9R_MDmAToZkUYfNr5QauN9ireghRyHfUwLbT6SQb9QRzTGLIEgETorT35iSQGtYIHZemTpnCvWeKEUA0jf0RNhkH2dKkIM6bc5oTn_bxSlTqrzyY08iAtSxgMAaGVcf_d9ZXTsM7Dqmru83SiGD-wN6p3HJh5qTBTjOmzObek-j72uH330jKiHTVpg2b44Uc_TWtJ4lu4CCigWTPXAxRzBapeVqI20QS8xnEmOmh9YBB2lD9k6l1T4E97Ufxa0qsfvcLLAbK1Ad1BYJOe374WIW2Dsk3h6zZKQu6ozEyGRdpPM6WZUkrIEBy2MvXfBtFpyV_h8k_3LnAcChFxiyVZLVIe0GVlbXWjl2E_IlJctMKQilUAMFnCJcqVsV6S_IbvGEzq1fjKiGDX6Ph1P84IYG5Mhh3UUi2R8h9wvtzys1BnSXeGsB4TpyYD8aqhGghihHTV1OZMXeTuXAyXoTWlWRTtwSr6PSaHGxAoY8ZbIop_aVqvDN97lXYCSkxqp4J1sWe29gDj-igmgPJCyi3lqVXPxuJ-EVZ10NKbVDfZrErODjQX-uS3Uqiitj6PwwiDVSp5jBg_Q-Q_D-JZyKXFfXu-6d6-64PkSQrPTqKbSqh8ltDopSsU4K7b28T2M_LmX3HhsZEX1g1mvWfmaOEsz_zIxau4d1uPZrxO7sQHCaaYde68RZMonqSowFJTebSIXrLvjYnjrXdCl_PuTrHR2t09ajqu55k7xLKAu64ycCE-i4"));
        } finally {
            pm.close();
        }
        BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
        BlobInfoFactory bif = new BlobInfoFactory();
        Iterator<BlobInfo> it = bif.queryBlobInfos();
        while (it.hasNext()) {
            BlobInfo bi = it.next();
            try (PrintWriter out = response.getWriter()) {
                out.println(bi.getBlobKey());
            }
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
