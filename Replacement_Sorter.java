import java.util.Queue;

public class Replacement_Sorter {
    private Data_Record[] input_buffer;
    private Data_Record[] output_buffer;
    private MinHeap heap;
    
    //Sorting constants
    private final int BLOCK_SIZE = 8192;
    private final int RECORD_SIZE = 16;
    
    //Initial setup of heap and read in of initial values
    public Replacement_Sorter() {
        input_buffer = new Data_Record[BLOCK_SIZE];
        output_buffer = new Data_Record[BLOCK_SIZE];
        heap = new MinHeap();
        for(int i = 0; i < heap.maxSize() - 1; i++) {
            //Read in initial binary values to the heap
        }
    }
    
    public void multiway() {
        
    }
    
    public void replacement() {
        
    }
}
