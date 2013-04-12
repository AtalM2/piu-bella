package fr.univnantes.atal.web.piubella.model;

import com.google.appengine.api.datastore.Key;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * Model class to store metadata about the dataset.
 * 
 * Contains the last update date.
 */
@PersistenceCapable
public class DatasetInfo {
    
    /**
     * ID for the datastore.
     */
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
    
    /**
     * String containing the last update date.
     * 
     * Since it's ISO, we can compare two dates as two strings.
     */
    @Persistent
    private String lastUpdateDate;

    /**
     * Constructor.
     */
    public DatasetInfo() {
        this.lastUpdateDate = "2012-12-15T00:00:00+01:00";
    }

    /**
     * Getter for the last update date known.
     * 
     * @return the last update date known.
     */
    public String getLastUpdateDate() {
        return lastUpdateDate;
    }
    
    /**
     * Setter for the last update date known.
     * 
     * @param lastUpdateDate
     */
    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate; 
   }
}
