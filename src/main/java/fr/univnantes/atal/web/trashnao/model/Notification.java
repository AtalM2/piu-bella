package fr.univnantes.atal.web.trashnao.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Notification {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Address address;
    @Persistent
    private NotificationTransport transport;
    @Persistent
    private boolean notifyOnYellowDay;
    @Persistent
    private boolean notifyOnBlueDayblue;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public NotificationTransport getTransport() {
        return transport;
    }

    public void setTransport(NotificationTransport transport) {
        this.transport = transport;
    }

    public boolean isNotifyOnYellowDay() {
        return notifyOnYellowDay;
    }

    public void setNotifyOnYellowDay(boolean notifyOnYellowDay) {
        this.notifyOnYellowDay = notifyOnYellowDay;
    }

    public boolean isNotifyOnBlueDayblue() {
        return notifyOnBlueDayblue;
    }

    public void setNotifyOnBlueDayblue(boolean notifyOnBlueDayblue) {
        this.notifyOnBlueDayblue = notifyOnBlueDayblue;
    }
}
