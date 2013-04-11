package fr.univnantes.atal.web.piubella.model;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class JSONInfo {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
    @Persistent
    private Text path = null;

    public Text getPath() {
        return path;
    }
    
    public void setPath(Text path) {
        this.path = path; 
   }
}
