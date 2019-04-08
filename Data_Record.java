
public class Data_Record {
    private long RecordID;
    private double value;
    
    //Data record constructor, records are sorted by key value
    public Data_Record(long RecordID, double value) {
        this.RecordID = RecordID;
        this.value = value;
    }
    
    //getter and setter functions for all values
    public double getValue(){
        return this.value;
    }
    
    public long getID(){
        return this.RecordID;
    }
    
    public void setValue(double key){
        this.value = key;
    }
    
    public void setID(long id){
        this.RecordID = id;
    }
}
