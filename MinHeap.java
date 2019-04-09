/**
 * 
 * @author Quinton Miller
 * @author Josh Rehm
 * @version 4/8/2019
 * 
 *          This is an implementation of the heap used to sort
 *          the values being read in from the input file
 */

public class MinHeap { 
    private dataRecord[] heap; 
    private int size; 
    private int maxsize;
    private int markLoc;
  
    //Heap constants
    private final int numRecords = 512;
    private final int root = 0;
  
    /**
     *  This creates an empty heap with 8 blocks of memory
     */
    public MinHeap() 
    {
        //Initialize Heap to have 8 blocks
        this.maxsize = 8 * numRecords;
        this.markLoc = maxsize - 1;
        this.size = 0; 
        heap = new dataRecord[this.maxsize];
    }
    
    /**
     *  @param recArray An array of data records to be inserted
     *  
     *  This creates a heap with 8 blocks of memory and inserts
     *  the array passed to it
     */
    public MinHeap(dataRecord[] recArray) {
      //Initialize Heap to have 8 blocks
        this.maxsize = 8 * numRecords;
        this.size = maxsize; 
        this.markLoc = maxsize;
        heap = new dataRecord[this.maxsize];
        for (int i = 0; i < numRecords; i++) {
            insert(recArray[i]);
        }
    }
  
    /**
     *  @param pos The position of the current node
     *  @return The index of the parent of current position
     */
    private int parent(int pos) 
    { 
        return (pos - 1) / 2; 
    } 
  
    /**
     *  @param pos The position of the current node
     *  @return The index of the leftChild of current position
     */
    private int leftChild(int pos) 
    {
        return (2 * pos) + 1; 
    } 
  
    /**
     *  @param pos The position of the current node
     *  @return The index of the rightChild of current position
     */
    private int rightChild(int pos) 
    {
        return (2 * pos) + 2; 
    }
  
    /**
     *  @param fpos The position of one node to swap
     *  @param spos The position of other node to swap
     *  
     *  Swaps two nodes into the opposite positions
     */
    private void swap(int fpos, int spos) 
    { 
        dataRecord tmp; 
        tmp = heap[fpos]; 
        heap[fpos] = heap[spos]; 
        heap[spos] = tmp; 
    } 
  
    /**
     *  @param pos The starting node to begin heaping, usually root
     *  
     *  Recursively reheaps the heap if it is disordered
     */
    private void minHeapify(int pos) 
    {
        int minIndex;
        if (rightChild(pos) >= size) {
            if (leftChild(pos) >= size) {
                return;
            }
            else {
                minIndex = leftChild(pos);
            }
        }
        else {
            if (heap[leftChild(pos)].getValue()
                    > heap[rightChild(pos)].getValue()) {
                minIndex = rightChild(pos);
            }
            else {
                minIndex = leftChild(pos);
            }
        }
        if (heap[pos].getValue() > heap[minIndex].getValue()) {
            swap(pos, minIndex);
            minHeapify(minIndex);
        }
    } 
  
    /**
     *  @param element The data record to insert
     *  @return A boolean value indicating success
     *  
     *  Inserts a value into heap and sifts up according to heap logic
     */
    public boolean insert(dataRecord element) 
    { 
        if (size + 1 == maxsize) {
            return false;
        }
        heap[size++] = element;
        int current = size;
        
        siftUp(current - 1);
        return true;
    }
    
    /**
     *  @param element The data record to insert
     *  @return A boolean value indicating success
     *  
     *  Inserts a value into heap and sifts up according to heap logic,
     *  also takes into account the end of the heap and places elements
     *  at the end if it is less than the current run's minimum
     */
    public boolean runInsert(dataRecord element) 
    { 
        if (size + 1 == maxsize) {
            return false;
        }
        heap[size++] = element;
        int current = size;
        
        //If the element shouldn't be in the 
        if (element.getValue() < heap[root].getValue()) {
            markLoc--;
            return true;
        }
        swap(current, markLoc);

        siftUp(markLoc - 1);
        
        return true;
    }
    
    /**
     *  @param index The data record to insert
     *  
     *  Recursively sifts nodes up according to heap logic
     */
    private void siftUp(int index) {
        if (index != 0) {
            if (heap[parent(index)].getValue() > heap[index].getValue()) {
                swap(index, parent(index));
                siftUp(parent(index));
            }
        }
    }
  
    /**  
     *  Builds a min heap from the array
     */
    public void minHeap() 
    { 
        for (int pos = (size / 2) - 1; pos >= 0; pos--) { 
            minHeapify(pos); 
        } 
    } 
  
    /**
     *  @return The minimum element returned from the heap
     *  
     *  Removes a value from the heap, decrements size, and returns
     *  element
     */
    public dataRecord remove() 
    { 
        if (size == 0) {
            System.out.print("Heap is empty, cannot remove!");
            return null;
        }
        dataRecord popped = heap[root]; 
        heap[root] = heap[--size];
        minHeapify(root); 
        return popped; 
    }
    
    /**
     *  @return The size of the heap
     */
    public int getSize()
    {
        return this.size;
    }
    
    /**
     *  @return The mark position
     */
    public int getMark()
    {
        return this.markLoc;
    }
    
    /**
     *  @param newSize The new location to have the mark
     *  
     *  Sets the mark to a new location in the heap
     */
    public void setMark(int newSize)
    {
        this.markLoc = newSize;
    }
}