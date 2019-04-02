
public class Data_Record {
    private long RecordID;
    private double value;
    
    public Data_Record(long RecordID, double value) {
        this.RecordID = RecordID;
        this.value = value;
    }
    
    public double getValue(){
        return this.value;
    }
    
    public long getID(){
        return this.RecordID;
    }
}
