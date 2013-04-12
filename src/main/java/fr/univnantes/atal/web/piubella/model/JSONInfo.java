package fr.univnantes.atal.web.piubella.model;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.Key;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * Model class to store metadata about the dataset JSON blob.
 * 
 * Contains the key of the JSON dataset blob in the blobstore.
 */
@PersistenceCapable
public class JSONInfo {

    /**
     * ID for the datastore.
     */
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
    
    /**
     * Key of the JSON dataset blob in the blobstore.
     */
    @Persistent
    private BlobKey blobKey = null;

    /**
     * Getter for the JSON dataset blob key in the blobstore.
     * @return the JSON dataset blob key in the blobstore.
     */
    public BlobKey getBlobKey() {
        return blobKey;
    }
    
    /**
     * Setter for the JSON dataset blob key in the blobstore.
     * 
     * @param blobKey the new blob key to store.
     */
    public void setBlobKey(BlobKey blobKey) {
        this.blobKey = blobKey; 
   }
}
