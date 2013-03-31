/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univnantes.atal.web.trashnao.servlets;

import fr.univnantes.atal.web.trashnao.model.Address;
import fr.univnantes.atal.web.trashnao.model.Notification;
import fr.univnantes.atal.web.trashnao.model.NotificationTransport;
import fr.univnantes.atal.web.trashnao.model.User;
import fr.univnantes.atal.web.trashnao.persistence.PMF;
import fr.univnantes.atal.web.trashnao.security.TokenVerifier;
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
public class TestNotifs extends HttpServlet {

    /**
     * Handler for the HTTP verb
     * <code>GET</code>
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
        User user = TokenVerifier.getUser(request);
        if (user != null) {
            PersistenceManager pm = PMF.get().getPersistenceManager();
            try {
                Address address = null;
                Query q = pm.newQuery(Address.class);
                q.setFilter("street == 'Allée Alain Gerbault'");
                try {
                    List<Address> results =
                            (List<Address>) q.execute();
                    if (!results.isEmpty()) {
                        address = results.get(0);
                    } else {
                    }
                } finally {
                    q.closeAll();
                }

                if (address != null) {
                    Notification notification = new Notification();
                    notification.addNotificationOnBlueDay(NotificationTransport.XMPP);
                    notification.addNotificationOnYellowDay(NotificationTransport.EMAIL);
                    notification.setAddress(address);
                    System.out.println(notification);
                    if (notification == null) {
                        
                    } else {
                        user.addNotification(notification);
                        pm.makePersistent(user);
                    }
                    try (PrintWriter out = response.getWriter()) {
                        out.println("Email: " + user.getEmail());
                        out.println("Address: " + address.getStreet());
                    }
                } else {
                    try (PrintWriter out = response.getWriter()) {
                        out.println("address error.");
                    }
                }
            } finally {
                pm.close();
            }
        } else {
            try (PrintWriter out = response.getWriter()) {
                out.println("user error.");
            }
        }
    }
}
