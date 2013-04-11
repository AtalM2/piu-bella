package fr.univnantes.atal.web.piubella.webservices;

import fr.univnantes.atal.web.piubella.model.User;
import fr.univnantes.atal.web.piubella.persistence.PMF;
import fr.univnantes.atal.web.piubella.security.TokenVerifier;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserService extends WebService {

    @Override
    protected void get(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        User user = TokenVerifier.getUser(request);
        Map<String, String> data = new HashMap<>();
        if (user != null) {
            data.put("status", "success");
            data.put("id", user.getGoogleId());
            data.put("email", user.getEmail());
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
    protected void delete(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        User user = TokenVerifier.getUser(request);
        if (user != null) {
            pm.deletePersistent(user);
        } else {
            error(request,
                    response,
                    "Couldn't identify the user thanks to the access_token.",
                    HttpServletResponse.SC_UNAUTHORIZED);
        }
        pm.close();
    }
}
