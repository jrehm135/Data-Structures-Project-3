/**
 * 
 * @author Quinton Miller
 * @author Josh Rehm
 * @version 4/8/2019
 * 
 *          This class is designed to hold the record information
 *          and to provide an interface for accessing and changing
 *          its values
 */

public class dataRecord {
    private long recordID;
    private double value;
    
    /**
     *  @param recordID The id for the record being created
     *  @param value The id for the record being created
     */
    public dataRecord(long recordID, double value) {
        this.recordID = recordID;
        this.value = value;
    }
    
    /**
     *  @return The value for the corresponding record
     */
    public double getValue() {
        return this.value;
    }
    
    /**
     *  @return The record id for the corresponding record
     */
    public long getID() {
        return this.recordID;
    }
    
    /**
     *  @param key The key to set current record's key to
     */
    public void setValue(double key) {
        this.value = key;
    }
    
    /**
     *  @param id The id to set current record's id to
     */
    public void setID(long id) {
        this.recordID = id;
    }
}
