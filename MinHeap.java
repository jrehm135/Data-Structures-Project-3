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
  
    public final int BLOCK_SIZE = 8192;
    public final int RECORD_SIZE = 16;
    private static final int FRONT = 1;
  
    public MinHeap() 
    {
        //Initialize Heap to have 8 blocks
        this.maxsize = 8 * BLOCK_SIZE;
        this.size = 0; 
        Heap = new Data_Record[this.maxsize + 1]; 
        Heap[0] = new Data_Record(0, Integer.MIN_VALUE); 
    } 
  
    // Function to return the position of  
    // the parent for the node currently  
    // at pos 
    private int parent(int pos) 
    { 
        return pos / 2; 
    } 
  
    // Function to return the position of the  
    // left child for the node currently at pos 
    private int leftChild(int pos) 
    { 
        return (2 * pos); 
    } 
  
    // Function to return the position of  
    // the right child for the node currently  
    // at pos 
    private int rightChild(int pos) 
    { 
        return (2 * pos) + 1; 
    } 
  
    // Function that returns true if the passed  
    // node is a leaf node 
    private boolean isLeaf(int pos) 
    { 
        if (pos >= (size / 2) && pos <= size) { 
            return true; 
        } 
        return false; 
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
  
        // If the node is a non-leaf node and greater 
        // than any of its child 
        if (!isLeaf(pos)) { 
            if (Heap[pos].getValue() > Heap[leftChild(pos)].getValue() 
                || Heap[pos].getValue() > Heap[rightChild(pos)].getValue()) { 
  
                // Swap with the left child and heapify 
                // the left child 
                if (Heap[leftChild(pos)].getValue() < Heap[rightChild(pos)].getValue()) { 
                    swap(pos, leftChild(pos)); 
                    minHeapify(leftChild(pos)); 
                } 
  
                // Swap with the right child and heapify  
                // the right child 
                else { 
                    swap(pos, rightChild(pos)); 
                    minHeapify(rightChild(pos)); 
                } 
            } 
        } 
    } 
  
    // Function to insert a node into the heap 
    public void insert(Data_Record element) 
    { 
        Heap[++size] = element; 
        int current = size; 
  
        while (Heap[current].getValue() < Heap[parent(current)].getValue()) { 
            swap(current, parent(current)); 
            current = parent(current); 
        } 
    }
  
    // Function to build the min heap using  
    // the minHeapify 
    public void minHeap() 
    { 
        for (int pos = (size / 2); pos >= 1; pos--) { 
            minHeapify(pos); 
        } 
    } 
  
    // Function to remove and return the minimum 
    // element from the heap 
    public Data_Record remove() 
    { 
        Data_Record popped = Heap[FRONT]; 
        Heap[FRONT] = Heap[size--]; 
        minHeapify(FRONT); 
        return popped; 
    }
    
    //Used to return max heap size
    public int maxSize()
    {
        return this.maxsize;
    }
}