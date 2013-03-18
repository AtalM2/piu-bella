package fr.univnantes.atal.web.trashnao.security;

import fr.univnantes.atal.web.trashnao.app.Constants;
import fr.univnantes.atal.web.trashnao.model.User;
import fr.univnantes.atal.web.trashnao.persistence.PMF;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;
import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class TokenVerifier {

    static private ObjectMapper mapper = new ObjectMapper();
    static private PersistenceManager pm = PMF.get().getPersistenceManager();

    static public User getUser(String accessToken) {
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
                if (userData.containsKey("error")
                        || !userData.get("audience")
                        .equals(Constants.clientId)) {
                    return null;
                } else {
                    String email = userData.get("email");
                    User user;
                    try {
                        user = pm.getObjectById(User.class, email);
                    } catch (JDOObjectNotFoundException ex) {
                        user = new User();
                        user.setEmail(email);
                        pm.makePersistent(user);
                    }
                    return user;
                }
            } catch (Exception ex) {
                return null;
            }
        }
    }
}
