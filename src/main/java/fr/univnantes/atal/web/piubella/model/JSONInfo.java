package fr.univnantes.atal.web.piubella.model;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.Key;
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
    private BlobKey blobKey = null;

    public BlobKey getBlobKey() {
        return blobKey;
    }
    
    public void setBlobKey(BlobKey blobKey) {
        this.blobKey = blobKey; 
   }
}
