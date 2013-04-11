package fr.univnantes.atal.web.piubella.servlets;

import fr.univnantes.atal.web.piubella.model.Address;
import fr.univnantes.atal.web.piubella.model.CollectDay;
import fr.univnantes.atal.web.piubella.model.Notification;
import fr.univnantes.atal.web.piubella.persistence.PMF;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Notifier extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            final Calendar c = Calendar.getInstance();
            List<CollectDay> toTest = new ArrayList<>();
            int day = c.get(Calendar.DAY_OF_WEEK);
            switch (day) {
                case Calendar.MONDAY:
                    toTest.add(CollectDay.TUESDAY);
                    break;
                case Calendar.TUESDAY:
                    toTest.add(CollectDay.WEDNESDAY);
                    toTest.add(CollectDay.WEDNESDAY_EVEN);
                    toTest.add(CollectDay.WEDNESDAY_ODD);
                    break;
                case Calendar.WEDNESDAY:
                    toTest.add(CollectDay.THURSDAY);
                    break;
                case Calendar.THURSDAY:
                    toTest.add(CollectDay.FRIDAY);
                    break;
                case Calendar.FRIDAY:
                    toTest.add(CollectDay.SATURDAY);
                    break;
                case Calendar.SATURDAY:
                    toTest.add(CollectDay.SUNDAY);
                    break;
                case Calendar.SUNDAY:
                    toTest.add(CollectDay.MONDAY);
                    break;
            }
            out.println(toTest);
            PersistenceManager pm = PMF.get().getPersistenceManager();
            Query q = pm.newQuery(Notification.class);
            try {
                List<Notification> results =
                        (List<Notification>) q.execute();
                for (Notification notification : results) {
                    boolean blue = false, yellow = false;
                    Address address = notification.getAddress();
                    innerLoop:
                    for (CollectDay cd : toTest) {
                        if (address.getBlueDays().contains(cd)) {
                            blue = true;
                            break innerLoop;
                        }
                        if (address.getYellowDays().contains(cd)) {
                            yellow = true;
                            break innerLoop;
                        }
                    }
                    if (blue || yellow) {
                        out.println(notification);
                    }
                }
            } finally {
                pm.close();
            }
        }
    }
}
