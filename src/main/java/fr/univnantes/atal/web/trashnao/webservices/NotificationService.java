package fr.univnantes.atal.web.trashnao.webservices;

import fr.univnantes.atal.web.trashnao.model.User;
import fr.univnantes.atal.web.trashnao.model.Notification;
import fr.univnantes.atal.web.trashnao.model.CollectDay;
import fr.univnantes.atal.web.trashnao.model.Address;
import fr.univnantes.atal.web.trashnao.persistence.PMF;
import fr.univnantes.atal.web.trashnao.security.TokenVerifier;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;    
import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

public class NotificationService extends WebService {

    //PersistenceManager pm = PMF.get().getPersistenceManager();

    @Override
    protected void get(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        //User user = TokenVerifier.getUser(request);
        Map<String, ArrayList<Notification>> data = new HashMap<>();
        ArrayList<Notification> alerts = new ArrayList<Notification>();

        ArrayList<CollectDay> yellows = new ArrayList<CollectDay>();
        yellows.add(CollectDay.MONDAY);
        ArrayList<CollectDay> blues = new ArrayList<CollectDay>();
        blues.add(CollectDay.TUESDAY);
        Notification n1 = new Notification(new Address("rue perdue",yellows,blues));
        Notification n2 = new Notification(new Address("rue fdsfdsf",yellows,blues));

        alerts.add(n1);
        alerts.add(n2);

        //data.put("mesAlertes",alerts);
        request.setAttribute("mesAlertes", alerts);
        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/tpl/notificationService.jsp");
        rd.forward(request, response);
    }
}
