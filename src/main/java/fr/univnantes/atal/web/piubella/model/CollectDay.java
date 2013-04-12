package fr.univnantes.atal.web.piubella.model;

/**
 * Enum to represent the different values possible of collect days.
 * 
 * Indexes are used to deliver a small file as dataset. They must therefore be
 * used to understand a call to the FetchJson servlet.
 */
public enum CollectDay {
    /**
     * Monday.
     * 
     * Index 0.
     */
    MONDAY,
    /**
     * Tuesday.
     * 
     * Index 1.
     */
    TUESDAY,
    /**
     * Wednesday.
     * 
     * Index 2.
     */
    WEDNESDAY,
    /**
     * Wednesday during odd weeks.
     * 
     * Index 3.
     */
    WEDNESDAY_ODD,
    /**
     * Wednesday during even weeks.
     * 
     * Index 4.
     */
    WEDNESDAY_EVEN,
    /**
     * Thursday.
     * 
     * Index 5.
     */
    THURSDAY,
    /**
     * Friday.
     * 
     * Index 6.
     */
    FRIDAY,
    /**
     * Saturday.
     * 
     * Index 7.
     */
    SATURDAY,
    /**
     * Sunday.
     * 
     * Index 8.
     */
    SUNDAY
}
