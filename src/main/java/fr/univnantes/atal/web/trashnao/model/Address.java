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
    private Collection<Day> blueDays;
    
    @Persistent
    private Collection<Day> yellowDays;
    
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private String street;

    public Address(String street){
        this.street = street;
    }

    public Address(String street, Collection<Day> blueDays, Collection<Day> yellowDays){
        this.street = street;
        this.blueDays = blueDays;
        this.yellowDays = yellowDays;     
    }

    public Boolean getSingleCollect() {
        return singleCollect;
    }

    public void setSingleCollect(Boolean singleCollect) {
        this.singleCollect = singleCollect;
    }

    public Collection<Day> getBlueDays() {
        return Collections.unmodifiableCollection(blueDays);
    }

    public void setBlueDays(Collection<Day> blueDays) {
        this.blueDays = blueDays;
    }

    public Collection<Day> getYellowDays() {
        return Collections.unmodifiableCollection(yellowDays);
    }

    public void setYellowDays(Collection<Day> yellowDays) {
        this.yellowDays = yellowDays;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
