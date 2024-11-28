//Code based on the Practical 8 submission. Multiple new methods have been added.
//Below is an implementation of a Max Heap.
public class DSAHeap {
    private DSAHeapEntry[] heap; //Heap array with heap entries.
    private int count;           //Count of number of entries in the heap table.


    //Initializes heap with the max size.
    public DSAHeap(int maxSize) {
        heap = new DSAHeapEntry[maxSize];
    }

    //Accessor
    public int getHeapCount(){
        return count;
    }
    //Method to add an entry to heap.
    public void add(double priority, Object value) {
        {
            DSAHeapEntry entry = new DSAHeapEntry(priority, value);
            heap[count] = entry;
            trickleUp(heap, count);
            count++;
        }
    }

    //Method to remove an entry from the heap.
    public void remove() {
        heap[0] = heap[count - 1];
        count--;
        trickleDown(heap,0, count);
    }

    //Method to trickle up an entry in the tree.
    public DSAHeapEntry[] trickleUp(DSAHeapEntry[] heap, int curIdx){
        DSAHeapEntry temp;

        int parentIdx = (curIdx - 1) / 2;
        if (curIdx > 0){
            if (heap[curIdx].getPriority()> heap[parentIdx].getPriority()){
                temp =heap[parentIdx];
                heap[parentIdx] = heap[curIdx];
                heap[curIdx] = temp;
                trickleUp(heap, parentIdx);
            }
        }
        return heap;

    }

    //Method to trickle down an entry in the tree.
    public DSAHeapEntry[] trickleDown(DSAHeapEntry[] heap, int curIdx, int count )
    {
        int lChildIdx, rChildIdx, largeIdx;
        DSAHeapEntry temp;
        lChildIdx = curIdx * 2 + 1;
        rChildIdx = lChildIdx + 1;

        if (lChildIdx < count)
        {
            largeIdx = lChildIdx;

            if (rChildIdx < count ) {
                if (heap[lChildIdx].getPriority() < heap[rChildIdx].getPriority()) {
                    largeIdx = rChildIdx;
                }
            }
            if (heap[largeIdx].getPriority()>heap[curIdx].getPriority())
            {
                temp = heap[curIdx];
                heap[curIdx] = heap[largeIdx];
                heap[largeIdx] = temp;
                trickleDown(heap, largeIdx, count);
            }
        }
        return heap;
    }

    //Method to heapify the heap.
    public void heapify( )
    {int ii;
        for( ii = count / 2 - 1;ii >= 0; ii-- )
        {
            trickleDown( heap, ii, count );
        }

    }

    //Method to sort the heap in ascending order.
    public void heapSort()
    {
        int ii;
        DSAHeapEntry temp;
        heapify();

        for( ii = count - 1; ii > 0; ii-- )
        {
            temp = heap[0];
            heap[0] = heap[ii];
            heap[ii] = temp;
            trickleDown( heap, 0, ii );
        }
    }

    //Method to display the heap.
    public void displayHeap(){
        System.out.println("Displaying heap");
        for (int i = 0; i < count; i++){

            System.out.println("Priority: "+ heap[i].getPriority()+ " Value: " + heap[i].getValue());
        }
    }

    //Method to get entry with minimum priority.
    public DSAHeapEntry getMin(){
        heapSort();
        DSAHeapEntry returnMin = heap[0];
        return returnMin;
    }

    //Method to extract the entry the minimum priority.
    //Used to extract the vertex with the minimum distance to the start vertex in Dijkstra's Algorithm.
    public DSAHeapEntry extractMin(){
        heapSort();
        DSAHeapEntry returnMin = heap[0];
        remove();
        return returnMin;
    }

    //Display the entries that are high risk.
    public void displayHighRisk() {
        System.out.println("Locations with a risk value of 6 and above are considered high risk areas. High risk areas are :");
        heapSort();

        for (int i = 0; i < count; i++) {
            if (heap[i].getPriority() >= 6) {
                System.out.println(heap[i].getValue());
            }
        }
    }
}
