package fr.univnantes.atal.web.trashnao.webservices;

import fr.univnantes.atal.web.trashnao.model.Notification;
import fr.univnantes.atal.web.trashnao.model.NotificationTransport;
import fr.univnantes.atal.web.trashnao.model.User;
import fr.univnantes.atal.web.trashnao.security.TokenVerifier;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NotificationService extends WebService {

    @Override
    protected void get(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        User user = TokenVerifier.getUser(request);
        if (user != null) {
            Map<String, Object> data = new HashMap<>();
            Set<Notification> notifications = user.getNotifications();
            data.put("status", "success");
            data.put("data", notifications);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println(mapper.writeValueAsString(data));
            }
        } else {
            error(request,
                    response,
                    "Couldn't identify the user thanks to the access_token.",
                    HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    @Override
    protected void post(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        String json = request.getParameter("data");
        if (json != null) {
            Map<String, Object> data = mapper.readValue(json, Map.class);
            String street;
            List<String> yellowRaw, blueRaw;
            Set<NotificationTransport> yellow = new HashSet<>(),
                    blue = new HashSet<>();
            if (data != null) {
                if (data.containsKey("street")) {
                    street = (String) data.get("street");
                } else {
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
                try (PrintWriter out = response.getWriter()) {
                    out.println(data);
                    out.println(yellow);
                    out.println(street);
                    out.println(blue);
                }
//            User user = TokenVerifier.getUser(request);
//            if (user != null) {
//                PersistenceManager pm = PMF.get().getPersistenceManager();
//                try {
//                    Address address = null;
//                    Query q = pm.newQuery(Address.class);
//                    q.setFilter("street == 'Allée Alain Gerbault'");
//                    try {
//                        List<Address> results =
//                                (List<Address>) q.execute();
//                        if (!results.isEmpty()) {
//                            address = results.get(0);
//                        } else {
//                        }
//                    } finally {
//                        q.closeAll();
//                    }
//
//                    if (address != null) {
//                        Notification notification = new Notification();
//                        notification.addNotificationOnBlueDay(NotificationTransport.XMPP);
//                        notification.addNotificationOnYellowDay(NotificationTransport.EMAIL);
//                        notification.setAddress(address);
//                        user.addNotification(notification);
//                        pm.makePersistent(user);
//                        try (PrintWriter out = response.getWriter()) {
//                            out.println("Email: " + user.getEmail());
//                            out.println("Address: " + address.getStreet());
//                        }
//                    } else {
//                        try (PrintWriter out = response.getWriter()) {
//                            out.println("address error.");
//                        }
//                    }
//                } finally {
//                    pm.close();
//                }
//            }
            }
        }
    }
}