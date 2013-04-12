package fr.univnantes.atal.web.piubella.model;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.datanucleus.annotations.Unowned;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * Model class for notifications.
 * 
 * Entity storing an address, a user and how to notify him for blue and yellow
 * days.
 */
@PersistenceCapable
public class Notification {
    
    /**
     * Datastore id.
     */
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
    
    /**
     * Adress to be warned about.
     */
    @Persistent
    @Unowned
    private Address address;
    
    /**
     * User to warn.
     */
    @Persistent
    private User user;
    
    /**
     * Channel to use for yellow collect days.
     */
    @Persistent
    private Set<NotificationTransport> onYellowDay;
    
    /**
     * Channel to use for blue collect days.
     */
    @Persistent
    private Set<NotificationTransport> onBlueDay;

    /**
     * Constructor.
     */
    public Notification() {
        onBlueDay = new HashSet<>();
        onYellowDay = new HashSet<>();
    }

    /**
     * Setter for the user.
     * 
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }
    
    /**
     * Getter for the user to notify.
     * 
     * @return the user to notify
     */
    public User getUser() {
        return user;
    }

    /**
     * Getter for the address to be notified about.
     * 
     * @return the address to be notified about.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Setter for the address to be notified about.
     * 
     * @param address the new address to be notified about.
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Add method for the blue collect days channels.
     * 
     * @param transport
     */
    public void addNotificationOnBlueDay(NotificationTransport transport) {
        if (!onBlueDay.contains(transport)) {
            onBlueDay.add(transport);
        }
    }

    /**
     * Add method for the yellow collect days channels.
     * 
     * @param transport
     */
    public void addNotificationOnYellowDay(NotificationTransport transport) {
        if (!onYellowDay.contains(transport)) {
            onYellowDay.add(transport);
        }
    }
    
    /**
     * Method to remove all the channels on blue collect days.
     */
    public void removeAllNotificationsOnBlueDay() {
        onBlueDay.clear();
    }

    /**
     * Method to remove all the channels on yellow collect days.
     */
    public void removeAllNotificationsOnYellowDay() {
        onYellowDay.clear();
    }
    
    /**
     * Method to remove all the channels on collect days.
     */
    public void removeAllNotifications() {
        removeAllNotificationsOnBlueDay();
        removeAllNotificationsOnYellowDay();
    }
    
    /**
     * Getter for all the channels on blue collect days.
     * 
     * @return the set of channels for blue collect days.
     */
    public Set<NotificationTransport> getNotificationsOnBlueDay() {
        return Collections.unmodifiableSet(onBlueDay);
    }
    
    /**
     * Getter for all the channels on yellow collect days.
     * 
     * @return the set of channels for yellow collect days.
     */
    public Set<NotificationTransport> getNotificationsOnYellowDay() {
        return Collections.unmodifiableSet(onYellowDay);
    }

    /**
     * Getter for the datastore ID.
     * 
     * @return the datastore ID key.
     */
    public Key getKey() {
        return key;
    }
}
