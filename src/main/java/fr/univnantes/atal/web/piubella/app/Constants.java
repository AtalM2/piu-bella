package fr.univnantes.atal.web.piubella.app;

import java.util.Arrays;
import java.util.List;

/**
 * Class containing the constants used in our application.
 * 
 * Centralized to ease modifications.
 */
public class Constants {

    /**
     * ID in the google console API.
     * 
     * Required for OAuth.
     */
    public static final String CLIENT_ID =
            "311668897898.apps.googleusercontent.com";
    /**
     * Key in the google console API.
     * 
     * Required for OAuth.
     */
    public static final String NOD_API_KEY = "AE3JKG5KADXPXVP";
    /**
     * URL of a JSON metadata file about our dataset.
     * 
     * Used to check if the dataset has been updated.
     */
    public static final String NOD_DATASET_INFO_URL =
            "http://data.nantes.fr/api/datastore_getdatasets/1.0/"
            + NOD_API_KEY + "/"
            + "?output=json&param[ids]=524";
    /**
     * URL Of the CSV dataset.
     * 
     * Since this dataset is the ligther (200ko), it has been our format of
     * choice.
     */
    public static final String NOD_DATASET_CSV_URL =
            "http://data.nantes.fr/api/publication/JOURS_COLLECTE_DECHETS_VDN/"
            + "JOURS_COLLECTE_DECHETS_VDN_STBL/content/?format=csv";
    /**
     * Email address used to mail notifications.
     * 
     * Really exists.
     */
    public static final String MAILER_ADDRESS =
            "piu.bella.nod@gmail.com ";
    /**
     * Email address used to send XMPP stanzas.
     */
    public static final String CHATTER_ADDRESS =
            "piu-bella@appspot.com";
    /**
     * List of emails used to reach admins.
     */
    public static final List<String> ADMIN_ADDRESSES =
            Arrays.asList("mogzor@gmail.com");
}
