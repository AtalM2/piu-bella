package fr.univnantes.atal.web.trashnao.app;

import java.util.Arrays;
import java.util.List;

public class Constants {

    public static final String CLIENT_ID =
            "311668897898.apps.googleusercontent.com";
    public static final String NOD_API_KEY = "AE3JKG5KADXPXVP";
    public static final String NOD_DATASET_INFO_URL =
            "http://data.nantes.fr/api/datastore_getdatasets/1.0/"
            + NOD_API_KEY + "/"
            + "?output=json&param[ids]=524";
    public static final String MAILER_ADDRESS =
            "piu.bella.nod@gmail.com ";
    public static final List<String> ADMIN_ADDRESSES =
            Arrays.asList("mogzor@gmail.com");
}
