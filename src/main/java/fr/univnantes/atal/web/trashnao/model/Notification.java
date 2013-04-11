package fr.univnantes.atal.web.trashnao.model;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.datanucleus.annotations.Unowned;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Notification {
    
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
    @Persistent
    @Unowned
    private Address address;
    @Persistent
    private User user;
    @Persistent
    private Set<NotificationTransport> onYellowDay;
    @Persistent
    private Set<NotificationTransport> onBlueDay;

    public Notification() {
        onBlueDay = new HashSet<>();
        onYellowDay = new HashSet<>();
    }

    public Notification(Address address) {
        this.address = address;
        onBlueDay = new HashSet<>();
        onYellowDay = new HashSet<>();
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public User getUser() {
        return user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void addNotificationOnBlueDay(NotificationTransport transport) {
        if (!onBlueDay.contains(transport)) {
            onBlueDay.add(transport);
        }
    }

    public void addNotificationOnYellowDay(NotificationTransport transport) {
        if (!onYellowDay.contains(transport)) {
            onYellowDay.add(transport);
        }
    }
    
    public void removeNotificationOnBlueDay(NotificationTransport transport) {
        onBlueDay.remove(transport);
    }

    public void removeNotificationOnYellowDay(NotificationTransport transport) {
        onYellowDay.remove(transport);
    }
    
    public void removeAllNotificationsOnBlueDay() {
        onBlueDay.clear();
    }

    public void removeAllNotificationsOnYellowDay() {
        onYellowDay.clear();
    }
    
    public void removeAllNotifications() {
        removeAllNotificationsOnBlueDay();
        removeAllNotificationsOnYellowDay();
    }
    
    public Collection<NotificationTransport> getNotificationsOnBlueDay() {
        return Collections.unmodifiableCollection(onBlueDay);
    }
    
    public Collection<NotificationTransport> getNotificationsOnYellowDay() {
        return Collections.unmodifiableCollection(onYellowDay);
    }

    public Key getKey() {
        return key;
    }
}
