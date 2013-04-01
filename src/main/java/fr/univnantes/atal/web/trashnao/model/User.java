package fr.univnantes.atal.web.trashnao.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class User {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private String googleId;
    @Persistent
    private String email;
    @Persistent(mappedBy="user")
    private Set<Notification> notifications;

    public User() {
        notifications = new HashSet<>();
    }
    
    public User(String googleId, String email) {
        this.googleId = googleId;
        this.email = email;
        notifications = new HashSet<>(); 
   }

    public String getEmail() {
        return email;
    }

    public String getGoogleId() {
        return googleId;
    }
    
    public void addNotification(Notification notification) {
        notification.setUser(this);
        notifications.add(notification);
    }
    
    public Boolean removeNotification(String key) {
        Notification toRemove = null;
        for (Notification notification : notifications) {
            if (notification.getKey().equals(key)) {
                toRemove = notification;
            }
        }
        if (toRemove != null) {
            return notifications.remove(toRemove);
        } else {
            return false;
        }
    }
    
    public Set<Notification> getNotifications() {
        return Collections.unmodifiableSet(notifications);
    }
}
