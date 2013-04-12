package fr.univnantes.atal.web.piubella.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * Model class for addresses.
 * 
 * Contains mainly the street and the days where the trash is collected on that
 * street.
 */
@PersistenceCapable
public class Address {
    
    /**
     * Set of collect days for the blue trash.
     */
    @Persistent
    private Set<CollectDay> blueDays;
    
    /**
     * Set of collect days for the yellow trash.
     */
    @Persistent
    private Set<CollectDay> yellowDays;
    
    /**
     * Street of the address.
     * 
     * Id in the datastore.
     */
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private String street;

    /**
     * Constructor.
     */
    public Address() {
        yellowDays = new HashSet<>();
        blueDays = new HashSet<>();
    }

    /**
     * Getter for the collect days of blue trash.
     * 
     * @return a set of CollectDay s.
     */
    public Set<CollectDay> getBlueDays() {
        return Collections.unmodifiableSet(blueDays);
    }

    /**
     * Setter fot the collect days of blue trash.
     * 
     * @param blueDays
     */
    public void setBlueDays(Set<CollectDay> blueDays) {
        this.blueDays = blueDays;
    }

    /**
     * Getter for the collect days of yellow trash.
     * 
     * @return a set of CollectDay s.
     */
    public Set<CollectDay> getYellowDays() {
        return Collections.unmodifiableSet(yellowDays);
    }

    /**
     * Setter for the collect days of yellow trash.
     * 
     * @param yellowDays
     */
    public void setYellowDays(Set<CollectDay> yellowDays) {
        this.yellowDays = yellowDays;
    }

    /**
     * Getter for the street.
     * 
     * @return the street of the address.
     */
    public String getStreet() {
        return street;
    }

    /**
     * Setter for the street.
     * 
     * @param street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Override to consider only the street.
     * 
     * @return a hash code for use in hash data structures.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.street);
        return hash;
    }

    /**
     * Override to consider only the street.
     * 
     * @param obj the object to compare this one to.
     * @return true if both objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Address other = (Address) obj;
        if (!Objects.equals(this.street, other.street)) {
            return false;
        }
        return true;
    }

}
