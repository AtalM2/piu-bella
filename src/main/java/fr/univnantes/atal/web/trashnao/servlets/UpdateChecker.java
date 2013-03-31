package fr.univnantes.atal.web.trashnao.servlets;

import fr.univnantes.atal.web.trashnao.app.Constants;
import fr.univnantes.atal.web.trashnao.model.DatasetInfo;
import fr.univnantes.atal.web.trashnao.persistence.PMF;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
import javax.mail.internet.AddressException;
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
        try (PrintWriter out = response.getWriter()) {
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
                    notifyUpdate();
                    datasetInfo.setLastUpdateDate(updated);
                    out.println(updated);
                    pm.makePersistent(datasetInfo);
                } else {
                    out.println("Pas besoin d'update");
                }
            } else {
                out.println("Pas de date d'update");
            }
        }
    }

    private void notifyUpdate() {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        try {
            Message msg = new MimeMessage(session);
            try {
                msg.setFrom(new InternetAddress(
                        Constants.MAILER_ADDRESS,
                        "Piu-bella update notifier"));
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(UpdateChecker.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
            for (String address : Constants.ADMIN_ADDRESSES) {
                msg.addRecipient(Message.RecipientType.TO,
                        new InternetAddress(address));
            }
            msg.setSubject("Trash collect dataset has been updated");
            msg.setText("Your action is (maybe) required.");
            Transport.send(msg);

        } catch (AddressException e) {
            Logger.getLogger(UpdateChecker.class.getName())
                    .log(Level.SEVERE, null, e);
        } catch (MessagingException e) {
            Logger.getLogger(UpdateChecker.class.getName())
                    .log(Level.SEVERE, null, e);
        }
    }
}