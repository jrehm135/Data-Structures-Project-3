import junit.framework.TestCase;

/**
 * 
 */

/**
 * @author Josh
 *
 */
public class testHeap extends TestCase {

    MinHeap heap;
    
    protected void setUp() throws Exception {
        super.setUp();
        heap = new MinHeap();
    }
    
    public void testInserts(){
        Data_Record temp0 = new Data_Record(10, 10.0);
        heap.insert(temp0);
        Data_Record temp1 = new Data_Record(10, 9.0);
        heap.insert(temp1);
        Data_Record temp2 = new Data_Record(10, 8.0);
        heap.insert(temp2);
        Data_Record temp3 = new Data_Record(10, 7.0);
        heap.insert(temp3);
        Data_Record temp4 = new Data_Record(10, 6.0);
        heap.insert(temp4);
        Data_Record temp5 = new Data_Record(10, 5.0);
        heap.insert(temp5);
        heap.setMark(6);
        Data_Record temp6 = new Data_Record(10, 4.0);
        heap.runInsert(temp6);
        Data_Record temp7 = new Data_Record(10, 3.0);
        heap.runInsert(temp7);
        Data_Record temp8 = new Data_Record(10, 2.0);
        heap.runInsert(temp8);
        Data_Record temp9 = new Data_Record(10, 1.0);
        heap.runInsert(temp9);
        heap.setMark(4095);
        heap.minHeap();
    }

}
