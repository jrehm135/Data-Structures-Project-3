import junit.framework.TestCase;

/**
 * 
 */

/**
 * @author Josh Rehm
 * @author Quinton Miller
 * @version 4/8/2019
 */
public class testHeap extends TestCase {

    //The heap to use
    private MinHeap heap;
    
    /**
     *  This function creates the heap for testing
     */
    protected void setUp() throws Exception {
        super.setUp();
        heap = new MinHeap();
    }
    
    /**
     *  This function creates the heap for testing
     */
    public void testInserts() {
        dataRecord temp0 = new dataRecord(10, 10.0);
        heap.insert(temp0);
        dataRecord temp1 = new dataRecord(10, 9.0);
        heap.insert(temp1);
        dataRecord temp2 = new dataRecord(10, 8.0);
        heap.insert(temp2);
        dataRecord temp3 = new dataRecord(10, 7.0);
        heap.insert(temp3);
        dataRecord temp4 = new dataRecord(10, 6.0);
        heap.insert(temp4);
        dataRecord temp5 = new dataRecord(10, 5.0);
        heap.insert(temp5);
        heap.setMark(8);
        dataRecord temp6 = new dataRecord(10, 4.0);
        heap.runInsert(temp6);
        dataRecord temp7 = new dataRecord(10, 3.0);
        heap.runInsert(temp7);
        dataRecord temp8 = new dataRecord(10, 2.0);
        heap.runInsert(temp8);
        dataRecord temp9 = new dataRecord(10, 1.0);
        heap.runInsert(temp9);
        heap.setMark(4095);
        heap.minHeap();
        assertEquals(heap.remove(), temp9);
        heap.remove();
        heap.remove();
    }

}
