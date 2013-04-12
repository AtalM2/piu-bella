package fr.univnantes.atal.web.piubella.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * Model for users.
 * 
 * Contains notifications to handle and some basic info.
 */
@PersistenceCapable
public class User {

    /**
     * Id of the user.
     */
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private String googleId;
    
    /**
     * Email of the user.
     */
    @Persistent
    private String email;
    
    /**
     * Set of notifications to handle.
     */
    @Persistent(mappedBy="user")
    private Set<Notification> notifications;

    /**
     * Constructor.
     */
    public User() {
        notifications = new HashSet<>();
    }
    
    /**
     * Constructor.
     * 
     * @param googleId
     * @param email
     */
    public User(String googleId, String email) {
        this.googleId = googleId;
        this.email = email;
        notifications = new HashSet<>(); 
   }

    /**
     * Getter for the email.
     * 
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * Getter for the Id.
     * 
     * @return
     */
    public String getGoogleId() {
        return googleId;
    }
    
    /**
     * Adder for the notifications.
     * 
     * @param notification
     */
    public void addNotification(Notification notification) {
        notification.setUser(this);
        notifications.add(notification);
    }
    
    /**
     * Getter for the notifications.
     * 
     * @return
     */
    public Set<Notification> getNotifications() {
        return Collections.unmodifiableSet(notifications);
    }
}
