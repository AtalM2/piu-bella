package fr.univnantes.atal.web.piubella.servlets;

import fr.univnantes.atal.web.piubella.app.Constants;
import fr.univnantes.atal.web.piubella.model.DatasetInfo;
import fr.univnantes.atal.web.piubella.persistence.PMF;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.map.ObjectMapper;

public class UpdateChecker extends HttpServlet {

    static private ObjectMapper mapper = new ObjectMapper();
    static private PersistenceManager pm = PMF.get().getPersistenceManager();

    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        URL url = new URL(Constants.NOD_DATASET_INFO_URL);

        Map<String, Object> json = mapper.readValue(
                new InputStreamReader(url.openStream(), "UTF-8"),
                Map.class);

        String updated = null;
        if (json.containsKey("opendata")) {
            Map<String, Object> openData =
                    (Map<String, Object>) json.get("opendata");
            if (openData.containsKey("answer")) {
                Map<String, Object> answer =
                        (Map<String, Object>) openData.get("answer");
                if (answer.containsKey("data")) {
                    Map<String, Object> data =
                            (Map<String, Object>) answer.get("data");
                    if (data.containsKey("dataset")) {
                        Map<String, Object> dataset =
                                (Map<String, Object>) data.get("dataset");
                        if (dataset.containsKey("updated")) {
                            updated = (String) dataset.get("updated");
                        }
                    }
                }

            }
        }
        if (updated != null) {
            DatasetInfo datasetInfo;
            Query q = pm.newQuery(DatasetInfo.class);
            try {
                List<DatasetInfo> results =
                        (List<DatasetInfo>) q.execute();
                if (!results.isEmpty()) {
                    datasetInfo = results.get(0);
                } else {
                    datasetInfo = new DatasetInfo();
                    pm.makePersistent(datasetInfo);
                }
            } finally {
                q.closeAll();
            }
            if (datasetInfo.getLastUpdateDate().compareTo(updated) < 0) {
                datasetInfo.setLastUpdateDate(updated);
                pm.makePersistent(datasetInfo);
                notifyUpdate();
            }
        }
    }

    private void notifyUpdate() {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(
                    Constants.MAILER_ADDRESS,
                    "Piu-bella update notifier"));
            for (String address : Constants.ADMIN_ADDRESSES) {
                msg.addRecipient(Message.RecipientType.TO,
                        new InternetAddress(address));
            }
            msg.setSubject("Trash collect dataset has been updated");
            msg.setText("Your action is (maybe) required.");
            Transport.send(msg);

        } catch (MessagingException | UnsupportedEncodingException e) {
            Logger.getLogger(UpdateChecker.class.getName())
                    .log(Level.SEVERE, null, e);
        }
    }
}
