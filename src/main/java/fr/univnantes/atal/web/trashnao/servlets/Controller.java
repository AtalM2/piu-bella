/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univnantes.atal.web.trashnao.servlets;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import fr.univnantes.atal.web.trashnao.model.User;
import fr.univnantes.atal.web.trashnao.persistence.PMF;
import java.io.IOException;
import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mog
 */
public class Controller extends HttpServlet {

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
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getPathInfo();
        RequestDispatcher rd;
        ServletContext sc = getServletContext();
        HttpSession session = request.getSession(false);

        if (path.startsWith("/app")) {
            rd = sc.getNamedDispatcher("App");
        } else if (path.equals("/fetch")) {
            // to remove when the cron is ready, Fetch should not be publicly
            // accessible
            rd = sc.getNamedDispatcher("Fetch");
        } else if (path.equals(
                "/user")) {
            User user = new User();
            user.setGoogleId("abc");
            user.setRefreshToken("abcd");
            PersistenceManager pm = PMF.get().getPersistenceManager();
            try {
                pm.makePersistent(user);
            } finally {
                pm.close();
            }
            rd = sc.getNamedDispatcher(session == null ? "Index" : "App");
        } else {
            rd = sc.getNamedDispatcher(session == null ? "Index" : "App");
        }

        rd.forward(request, response);
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
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getNamedDispatcher("Index");
        rd.forward(request, response);
    }

    /**
     * Handles the HTTP
     * <code>PUT</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPut(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getNamedDispatcher("Index");
        rd.forward(request, response);
    }

    /**
     * Handles the HTTP
     * <code>DELETE</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doDelete(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getNamedDispatcher("Index");
        rd.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Controller servlet. Dispatches requests.";
    }
}
