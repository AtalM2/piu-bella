package fr.univnantes.atal.web.trashnao.model;

import java.util.Collection;
import java.util.Collections;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Address {

    @Persistent
    private Boolean singleCollect;
    
    @Persistent
    private Collection<CollectDay> blueDays;
    
    @Persistent
    private Collection<CollectDay> yellowDays;
    
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private String street;

    public Boolean getSingleCollect() {
        return singleCollect;
    }

    public void setSingleCollect(Boolean singleCollect) {
        this.singleCollect = singleCollect;
    }

    public Collection<CollectDay> getBlueDays() {
        return Collections.unmodifiableCollection(blueDays);
    }

    public void setBlueDays(Collection<CollectDay> blueDays) {
        this.blueDays = blueDays;
    }

    public Collection<CollectDay> getYellowDays() {
        return Collections.unmodifiableCollection(yellowDays);
    }

    public void setYellowDays(Collection<CollectDay> yellowDays) {
        this.yellowDays = yellowDays;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
