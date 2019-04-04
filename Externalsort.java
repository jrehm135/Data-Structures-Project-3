import java.io.*;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * @author Josh
 *
 */
public class Externalsort {

    private final static int BLOCK_SIZE = 8192;
    private final static int NUM_RECORDS = 512;
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        if(args.length < 1) {
            System.out.print("Must include input file");
            System.exit(-1);
        }
        
        String binFileString = args[0];
        
        //Allocate memory for heap, records, and byte array
        MinHeap heap = new MinHeap();
        byte[] inputBuf = new byte[BLOCK_SIZE];
        
        for(int j = 0; j < 8; j++) {
            //Read in first 8 blocks for heap
            try(InputStream binFile = new FileInputStream(binFileString);) 
            {
                //This reads in one block from the input file
                binFile.read(inputBuf);
            }
            catch(Exception e) {
                System.out.print("Error: " + e);
            }
            
            ByteBuffer buf = ByteBuffer.wrap(inputBuf);
            //This should fill up the heap with 8 blocks of sorted data
            for(int i = 0; i < BLOCK_SIZE; i += 16) {
                long recordID = buf.getLong(i);
                double key = buf.getDouble(i + 8);
                Data_Record rec = new Data_Record(recordID, key);
                heap.insert(rec);
            }
        }
        
        while(heap.)
    }

}
