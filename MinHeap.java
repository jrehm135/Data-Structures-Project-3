/**
 * 
 */

/**
 * @author Josh
 *
 */
public class MinHeap { 
    private Data_Record[] Heap; 
    private int size; 
    private int maxsize;
    private int markLoc;
  
    //Heap constants
    private final int NUM_RECORDS = 512;
    private final int ROOT = 0;
  
    public MinHeap() 
    {
        //Initialize Heap to have 8 blocks
        this.maxsize = 8 * NUM_RECORDS;
        this.markLoc = maxsize;
        this.size = 0; 
        Heap = new Data_Record[this.maxsize];
    }
    
    public MinHeap(Data_Record[] recArray) {
      //Initialize Heap to have 8 blocks
        this.maxsize = 8 * NUM_RECORDS;
        this.size = maxsize; 
        this.markLoc = maxsize;
        Heap = new Data_Record[this.maxsize];
        for(int i = 0; i < NUM_RECORDS; i++) {
            insert(recArray[i]);
        }
    }
  
    // Function to return the position of  
    // the parent for the node currently  
    // at pos 
    private int parent(int pos) 
    { 
        return (pos - 1) / 2; 
    } 
  
    // Function to return the position of the  
    // left child for the node currently at pos 
    private int leftChild(int pos) 
    {
        return (2 * pos) + 1; 
    } 
  
    // Function to return the position of  
    // the right child for the node currently  
    // at pos 
    private int rightChild(int pos) 
    {
        return (2 * pos) + 2; 
    }
  
    // Function to swap two nodes of the heap 
    private void swap(int fpos, int spos) 
    { 
        Data_Record tmp; 
        tmp = Heap[fpos]; 
        Heap[fpos] = Heap[spos]; 
        Heap[spos] = tmp; 
    } 
  
    // Function to heapify the node at pos 
    private void minHeapify(int pos) 
    {
        int minIndex;
        if(rightChild(pos) >= size) {
            if(leftChild(pos) >= size) {
                return;
            }
            else {
                minIndex = leftChild(pos);
            }
        }
        else {
            if(Heap[leftChild(pos)].getValue()
                    > Heap[rightChild(pos)].getValue()) {
                minIndex = rightChild(pos);
            }
            else {
                minIndex = leftChild(pos);
            }
        }
        if(Heap[pos].getValue() > Heap[minIndex].getValue()) {
            swap(pos, minIndex);
            minHeapify(minIndex);
        }
    } 
  
    // Function to insert a node into the heap 
    public boolean insert(Data_Record element) 
    { 
        if(size + 1 == maxsize) {
            return false;
        }
        Heap[size++] = element;
        int current = size;
        
        siftUp(current - 1);
        return true;
    }
    
    // Function to insert a node into the heap once runs have begun
    public boolean runInsert(Data_Record element) 
    { 
        if(size + 1 == maxsize) {
            return false;
        }
        Heap[size++] = element;
        int current = size;
        
        //If the element shouldn't be in the 
        if(element.getValue() < Heap[ROOT].getValue()) {
            markLoc--;
            return true;
        }
        swap(current, markLoc);

        siftUp(current - 1);
        
        return true;
    }
    
    private void siftUp(int index) {
        if(index != 0) {
            if(Heap[parent(index)].getValue() > Heap[index].getValue()) {
                swap(index, parent(index));
                siftUp(parent(index));
            }
        }
    }
  
    // Function to build the min heap using  
    // the minHeapify 
    public void minHeap() 
    { 
        for (int pos = (size / 2) - 1; pos >= 0; pos--) { 
            minHeapify(pos); 
        } 
    } 
  
    // Function to remove and return the minimum 
    // element from the heap 
    public Data_Record remove() 
    { 
        Data_Record popped = Heap[ROOT]; 
        Heap[ROOT] = Heap[size--]; 
        minHeapify(ROOT); 
        return popped; 
    }
    
    //Used to return heap size
    public int getSize()
    {
        return this.size;
    }
    
    //Used to return portion of the array taken up by heap
    public int getMark()
    {
        return this.markLoc;
    }
    
    //Used to set portion of the array in a heap
    public void setMark(int newSize)
    {
        this.markLoc = newSize;
    }
}