package fr.univnantes.atal.web.trashnao.servlets;

import au.com.bytecode.opencsv.CSVReader;
import fr.univnantes.atal.web.trashnao.model.Address;
import fr.univnantes.atal.web.trashnao.model.CollectDay;
import fr.univnantes.atal.web.trashnao.persistence.PMF;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Fetch extends HttpServlet {

    PersistenceManager pm = PMF.get().getPersistenceManager();

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Query q = pm.newQuery(Address.class);
        q.deletePersistentAll();
        URL url = new URL("http://data.nantes.fr/api/publication/"
                + "JOURS_COLLECTE_DECHETS_VDN/JOURS_COLLECTE_DECHETS_VDN_STBL/"
                + "content/?format=csv");
        CSVReader reader = new CSVReader(new InputStreamReader(url.openStream(), "UTF-8"));
        String[] nextLine;
        Collection<Address> addresses = new ArrayList<>();
        while ((nextLine = reader.readNext()) != null) {
            if (nextLine.length < 13) {
                System.err.println("Data import failed.");
                System.err.println("Couldn't find 13 fields in the dataset "
                        + "which means I don't know what to do.");
                return;
            } else {
                String blue = nextLine[10],
                        obsCollect = nextLine[12];
                if (!blue.isEmpty() && obsCollect.isEmpty()) {
                    String street = nextLine[1],
                            obsStreet = nextLine[9],
                            yellow = nextLine[11];
                    Collection<CollectDay> blueDays = new ArrayList<>(),
                            yellowDays = new ArrayList<>();
                    Boolean singleCollect = false;
                    if (yellow.isEmpty()) {
                        singleCollect = true;
                    }
                    if (blue.contains("lundi")) {
                        blueDays.add(CollectDay.MONDAY);
                    }
                    if (blue.contains("mardi")) {
                        blueDays.add(CollectDay.TUESDAY);
                    }
                    if (blue.contains("mercredi")) {
                        blueDays.add(CollectDay.WEDNESDAY);
                    } else if (blue.contains("merc_sem_impaires")) {
                        blueDays.add(CollectDay.WEDNESDAY_ODD);
                    } else if (blue.contains("merc_sem_paires")) {
                        blueDays.add(CollectDay.WEDNESDAY_EVEN);
                    }
                    if (blue.contains("jeudi")) {
                        blueDays.add(CollectDay.THURSDAY);
                    }
                    if (blue.contains("vendredi")) {
                        blueDays.add(CollectDay.FRIDAY);
                    }
                    if (blue.contains("samedi")) {
                        blueDays.add(CollectDay.SATURDAY);
                    }
                    if (blue.contains("dimanche")) {
                        blueDays.add(CollectDay.SUNDAY);
                    }
                    if (yellow.contains("lundi")) {
                        yellowDays.add(CollectDay.MONDAY);
                    }
                    if (yellow.contains("mardi")) {
                        yellowDays.add(CollectDay.TUESDAY);
                    }
                    if (yellow.contains("mercredi")) {
                        yellowDays.add(CollectDay.WEDNESDAY);
                    } else if (yellow.contains("merc_sem_impaires")) {
                        yellowDays.add(CollectDay.WEDNESDAY_ODD);
                    } else if (yellow.contains("merc_sem_paires")) {
                        yellowDays.add(CollectDay.WEDNESDAY_EVEN);
                    }
                    if (yellow.contains("jeudi")) {
                        yellowDays.add(CollectDay.THURSDAY);
                    }
                    if (yellow.contains("vendredi")) {
                        yellowDays.add(CollectDay.FRIDAY);
                    }
                    if (yellow.contains("samedi")) {
                        yellowDays.add(CollectDay.SATURDAY);
                    }
                    if (yellow.contains("dimanche")) {
                        yellowDays.add(CollectDay.SUNDAY);
                    }
                    Address address = new Address();
                    address.setSingleCollect(singleCollect);
                    address.setStreet(street + (obsStreet.isEmpty()
                            ? ""
                            : " " + obsStreet));
                    address.setBlueDays(blueDays);
                    address.setYellowDays(yellowDays);
                    addresses.add(address);

                }
            }
            if (addresses.size() > 100) {
                pm.makePersistentAll(addresses);
                addresses.clear();
            }
        }
    }
}
