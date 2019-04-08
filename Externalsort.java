import java.io.*;
import java.nio.ByteBuffer;

/**
 * @author Josh
 *
 */
public class Externalsort {

    private final static int BLOCK_SIZE = 8192;
    
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
        byte[] outputBuf = new byte[BLOCK_SIZE];
        ByteBuffer inBuf = ByteBuffer.wrap(inputBuf);
        ByteBuffer outBuf = ByteBuffer.wrap(inputBuf);
        
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
            
            //This should fill up the heap with 8 blocks of sorted data
            for(int i = 0; i < BLOCK_SIZE; i += 16) {
                long recordID = inBuf.getLong(i);
                double key = inBuf.getDouble(i + 8);
                Data_Record rec = new Data_Record(recordID, key);
                heap.insert(rec);
            }
        }
        
        //This section makes a run through the file without marking
        for(int i = 0; heap.getMark() > 0; i += 16) {
            //Heap has burned through an input block, read in new one
            if(i % BLOCK_SIZE == 0) {
                //Read in a block for heap
                try(InputStream binFile = new FileInputStream(binFileString);) 
                {
                    //This reads in one block from the input file
                    binFile.read(inputBuf);
                }
                catch(Exception e) {
                    System.out.print("Error: " + e);
                }
                
                //Output should occur at the same time, but if not,
                //move this section to a separate one
                try (FileOutputStream output = new FileOutputStream("runFile.bin", true)) {
                    output.write(outputBuf);
                }
                catch (Exception e) {
                    System.out.print("Error: " + e);
                }
            }
            
            //Remove from heap and place into output buffer
            Data_Record lowestVal = heap.remove();
            outBuf.putLong(i, lowestVal.getID());
            outBuf.putDouble(i + 8, lowestVal.getValue());
            
            //Insert new values from input buffer
            long recordID = inBuf.getLong(i);
            double key = inBuf.getDouble(i + 8);
            Data_Record rec = new Data_Record(recordID, key);
            heap.insert(rec);
        }
    }

}
