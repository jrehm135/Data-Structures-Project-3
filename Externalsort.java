// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

import java.io.*;
import java.nio.ByteBuffer;

/**
 * 
 * @author Quinton Miller
 * @author Josh Rehm
 * @version 4/8/2019
 * 
 *          This program is designed to take in an input file in binary
 *          that is too large to be read in to normal memory
 *          and place the initial 8 blocks into a heap, which will be 
 *          organized according to heap properties, with the minimum
 *          value at the root of the heap. From there, the heap will
 *          perform replacement sort, outputting the each of its runs
 *          to output files. From there, it will perform a merge of
 *          these files into a final, output file that is sorted
 */
public class Externalsort {

    //A constant value used to represent block size
    private final static int BLOCK_SIZE = 8192;
    
    /**
     * @param args
     * The main function does all the reading of files and placement
     * into the heap as well as input and output memory.
     */
    public static void main(String[] args) {
        if (args.length < 1) {
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
        
        try {
            RandomAccessFile binFile = new RandomAccessFile(binFileString, "r");
            for (int j = 0; j < 8; j++) {
                //Read in first 8 blocks for heap
                
                //This reads in one block from the input file
                binFile.read(inputBuf);
                
                //This should fill up the heap with 8 blocks of sorted data
                for (int i = 0; i < BLOCK_SIZE; i += 16) {
                    long recordID = inBuf.getLong(i);
                    double key = inBuf.getDouble(i + 8);
                    dataRecord rec = new dataRecord(recordID, key);
                    heap.insert(rec);
                }
            }
            binFile.close();
        }
        catch (Exception e) {
            System.out.print("Error: " + e);
        }
        
        //Heap missing last value from array, add it
        long lastID = inBuf.getLong(BLOCK_SIZE - 16);
        double lastKey = inBuf.getDouble(BLOCK_SIZE - 8);
        dataRecord lastRec = new dataRecord(lastID, lastKey);
        heap.insert(lastRec);
        
        //This section makes a run through the file without marking
        for (int i = 0; heap.getMark() > 0; i += 16) {
            //Heap has burned through an input block, read in new one
            if (i % BLOCK_SIZE == 0) {
                //Read in a block for heap
                try {
                    RandomAccessFile binFile = 
                            new RandomAccessFile(binFileString, "r");
                    if (i != 0) {
                        binFile.seek(i);
                    }
                    //This reads in one block from the input file
                    binFile.read(inputBuf);
                    binFile.close();
                }
                catch (Exception e) {
                    System.out.print("Error: " + e);
                }
                
                //Output should occur at the same time, but if not,
                //move this section to a separate one
                try (FileOutputStream output = 
                        new FileOutputStream("runFile.bin", true)) {
                    output.write(outputBuf);
                }
                catch (Exception e) {
                    System.out.print("Error: " + e);
                }
            }
            
            //Remove from heap and place into output buffer
            //Needs to be modded by block size to avoid out of bounds
            dataRecord lowestVal = heap.remove();
            outBuf.putLong(i % BLOCK_SIZE, lowestVal.getID());
            outBuf.putDouble((i + 8) % BLOCK_SIZE, lowestVal.getValue());
            
            //Insert new values from input buffer
            long recordID = inBuf.getLong(i % BLOCK_SIZE);
            double key = inBuf.getDouble((i + 8) % BLOCK_SIZE);
            dataRecord rec = new dataRecord(recordID, key);
            heap.runInsert(rec);
        }
    }

}
