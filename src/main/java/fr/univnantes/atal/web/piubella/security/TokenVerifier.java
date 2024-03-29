package fr.univnantes.atal.web.piubella.security;

import fr.univnantes.atal.web.piubella.app.Constants;
import fr.univnantes.atal.web.piubella.model.User;
import fr.univnantes.atal.web.piubella.persistence.PMF;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;
import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServletRequest;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/**
 * Security helper.
 * 
 * Checks if the request contains a field access_token that is set to a correct
 * OAuth token for this application.
 */
public class TokenVerifier {

    static private ObjectMapper mapper = new ObjectMapper();

    /**
     * Main method to authenticate a request.
     * 
     * @param request
     * @return a User if the token was correct. null otherwise.
     */
    static public User getUser(HttpServletRequest request) {
        String accessToken = request.getParameter("access_token");
        if (accessToken == null) {
            return null;
        } else {
            try {
                URL url = new URL(
                        "https://www.googleapis.com/oauth2/v1/tokeninfo"
                        + "?access_token=" + accessToken);

                Map<String, String> userData = mapper.readValue(
                        new InputStreamReader(url.openStream(), "UTF-8"),
                        new TypeReference<Map<String, String>>() {
                        });
                if (userData.get("audience") == null
                        || userData.containsKey("error")
                        || !userData.get("audience")
                        .equals(Constants.CLIENT_ID)) {
                    return null;
                } else {
                    String email = userData.get("email"),
                            userId = userData.get("user_id");
                    User user = null;
                    PersistenceManager pm = PMF.get().getPersistenceManager();
                    try {
                        user = pm.getObjectById(User.class, userId);
                    } catch (JDOObjectNotFoundException ex) {
                        user = new User(userId, email);
                        pm.makePersistent(user);
                    } finally {
                        pm.close();
                    }
                    return user;
                }
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
                return null;
            }
        }
    }
}
