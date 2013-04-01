package fr.univnantes.atal.web.trashnao.webservices;

import fr.univnantes.atal.web.trashnao.model.Notification;
import fr.univnantes.atal.web.trashnao.model.User;
import fr.univnantes.atal.web.trashnao.security.TokenVerifier;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
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
        User user = TokenVerifier.getUser(request);
        if (user != null) {
            
        } else {
            
        }
    }
}