package fr.univnantes.atal.web.trashnao.webservices;

import fr.univnantes.atal.web.trashnao.model.User;
import fr.univnantes.atal.web.trashnao.persistence.PMF;
import fr.univnantes.atal.web.trashnao.security.TokenVerifier;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserService extends WebService {
    
    PersistenceManager pm = PMF.get().getPersistenceManager();
    
    @Override
    protected void get(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        User user = TokenVerifier.getUser(request);
        Map<String, String> data = new HashMap<>();
        data.put("id", user.getGoogleId());
        data.put("email", user.getEmail());
    }

    @Override
    protected void post(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        error(request, response, "Unsupported method: POST");
    }

    @Override
    protected void put(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        error(request, response, "Unsupported method: PUT");
    }

    @Override
    protected void delete(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        User user = TokenVerifier.getUser(request);
        pm.deletePersistent(user);
    }
}
