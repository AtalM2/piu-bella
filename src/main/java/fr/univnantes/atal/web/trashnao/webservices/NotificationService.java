package fr.univnantes.atal.web.trashnao.webservices;

import fr.univnantes.atal.web.trashnao.model.Address;
import fr.univnantes.atal.web.trashnao.model.Notification;
import fr.univnantes.atal.web.trashnao.model.NotificationTransport;
import fr.univnantes.atal.web.trashnao.model.User;
import fr.univnantes.atal.web.trashnao.persistence.PMF;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NotificationService extends AuthWebService {

    @Override
    public void init() {
        allowed_methods =
            new ArrayList(Arrays.asList(false, false, false, false));
        authenticated_methods =
            new ArrayList(Arrays.asList(true, true, false, false));
    }
    
    @Override
    protected void auth_get(
            HttpServletRequest request,
            HttpServletResponse response,
            User user)
            throws ServletException, IOException {
        Map<String, Object> data = new HashMap<>();
        Set<Notification> notifications = user.getNotifications();
        data.put("status", "success");
        data.put("data", notifications);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println(mapper.writeValueAsString(data));
        }
    }

    @Override
    protected void auth_post(
            HttpServletRequest request,
            HttpServletResponse response,
            User user)
            throws ServletException, IOException {
        String json = request.getParameter("data");
        if (json != null) {
            Map<String, Object> data = mapper.readValue(json, Map.class);
            List<String> yellowRaw, blueRaw;
            Address address = null;
            Set<NotificationTransport> yellow = new HashSet<>(),
                    blue = new HashSet<>();
            if (data != null) {
                PersistenceManager pm = PMF.get().getPersistenceManager();
                try {
                    if (data.containsKey("street")) {
                        String street = (String) data.get("street");
                        Query q = pm.newQuery(Address.class);
                        q.setFilter("street == '" + street + "'");
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
                    }
                    if (address == null) {
                        error(request,
                                response,
                                "The street field must be present and set "
                                + "to a valid street contained in the NOD "
                                + "dataset.",
                                HttpServletResponse.SC_BAD_REQUEST);
                        return;

                    }
                    if (data.containsKey("yellow")) {
                        yellowRaw = (List<String>) data.get("yellow");
                        for (String transport : yellowRaw) {
                            try {
                                yellow.add(NotificationTransport.valueOf(transport));
                            } catch (IllegalArgumentException e) {
                                error(request,
                                        response,
                                        "The yellow field isn't correct. Should be "
                                        + "of the form [\"XMPP\", \"EMAIL\"].",
                                        HttpServletResponse.SC_BAD_REQUEST);
                                return;
                            }
                        }
                        if (data.containsKey("blue")) {
                            blueRaw = (List<String>) data.get("blue");
                            for (String transport : blueRaw) {
                                try {
                                    blue.add(NotificationTransport.valueOf(transport));
                                } catch (IllegalArgumentException e) {
                                    error(request,
                                            response,
                                            "The blue field isn't correct. Should be "
                                            + "of the form [\"XMPP\", \"EMAIL\"].",
                                            HttpServletResponse.SC_BAD_REQUEST);
                                    return;
                                }
                            }
                        }
                    }
                    Notification notification = new Notification(address);
                    for (NotificationTransport transport : yellow) {
                        notification.addNotificationOnYellowDay(transport);
                    }
                    for (NotificationTransport transport : blue) {
                        notification.addNotificationOnBlueDay(transport);
                    }
                    user.addNotification(notification);
                    pm.makePersistent(user);

                } finally {
                    pm.close();
                }
            }
        }
    }
}