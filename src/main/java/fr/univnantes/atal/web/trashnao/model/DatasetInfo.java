package fr.univnantes.atal.web.trashnao.model;

import com.google.appengine.api.datastore.Key;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class DatasetInfo {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
    @Persistent
    private String lastUpdateDate;

    public DatasetInfo() {
        this.lastUpdateDate = "2012-12-15T00:00:00+01:00";
    }

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }
    
    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate; 
   }
}
