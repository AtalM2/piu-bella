package fr.univnantes.atal.web.piubella.persistence;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

/**
 * Factory helper for Persistence Managers.
 * 
 * Singleton.
 */
public final class PMF {
    private static final PersistenceManagerFactory pmfInstance =
        JDOHelper.getPersistenceManagerFactory("transactions-optional");

    private PMF() {}

    /**
     * Getter of the singleton.
     * 
     * @return the instance of the PMF.
     */
    public static PersistenceManagerFactory get() {
        return pmfInstance;
    }
}