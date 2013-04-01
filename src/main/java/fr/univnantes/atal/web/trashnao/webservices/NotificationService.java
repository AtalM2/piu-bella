package fr.univnantes.atal.web.trashnao.webservices;

import fr.univnantes.atal.web.trashnao.model.User;
import fr.univnantes.atal.web.trashnao.model.Notification;
import fr.univnantes.atal.web.trashnao.model.CollectDay;
import fr.univnantes.atal.web.trashnao.model.Address;
import fr.univnantes.atal.web.trashnao.persistence.PMF;
import fr.univnantes.atal.web.trashnao.security.TokenVerifier;
import java.io.IOException;
import java.io.PrintWriter;
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

        if(true){
        ArrayList<CollectDay> yellows = new ArrayList<CollectDay>();
        yellows.add(CollectDay.MONDAY);
        ArrayList<CollectDay> blues = new ArrayList<CollectDay>();
        blues.add(CollectDay.TUESDAY);
        Notification n1 = new Notification(new Address("rue perdue",yellows,blues));
        Notification n2 = new Notification(new Address("rue fdsfdsf",yellows,blues));

        alerts.add(n1);
        alerts.add(n2);

        data.put("mesAlertes",alerts);

        response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println(mapper.writeValueAsString(data));
            }
        } else {
            error(request,
                    response,
                    "Couldn't identify the user thanks to the access_token.");
        }
    }
}