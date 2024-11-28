public class UnitTestHeap {
    public static void main(String[] args) {
        System.out.println("Welcome to DSAHeap TestHarness ");
        DSAHeap heap = new DSAHeap(100);

        //Testing adding entries to the heap .
        System.out.println("Testing add()\n");
        heap.add(1,"A");
        heap.add(2,"B");
        heap.add(3, "C");
        heap.add(4, "D");
        heap.add(5, "E");
        heap.add(6, "F");
        heap.add(7, "G");
        heap.add(8, "H");
        heap.add(9, "I");
        heap.add(10, "J");
        System.out.println("================================\n");

        //Testing displaying the heap.
        System.out.println("Testing displayHeap()\n");
        heap.displayHeap();
        System.out.println("================================\n");

        //Testing sorting the heap.
        System.out.println("Testing heapSort()\n");
        heap.heapSort();
        heap.displayHeap();
        System.out.println("================================\n");

        //Testing removing entries from the heap.
        System.out.println("Testing remove()\n");
        heap.remove();
        heap.remove();
        heap.remove();
        heap.remove();
        heap.remove();
        heap.displayHeap();
        System.out.println("================================\n");

        //Testing getting minimum priority value from heap.
        System.out.println("Testing getMin() . The minimum should be:B \n");
        DSAHeapEntry temp = heap.getMin();
        System.out.println("The minimum is "+ temp.getValue());
        System.out.println("================================\n");

        //Testing removing minimum priority value from heap.
        System.out.println("Testing extractMin(). The minimum should be :B \n");
        DSAHeapEntry temp2 = heap.extractMin();
        System.out.println("The minimum is "+ temp2.getValue());
        System.out.println("================================\n");

        //Testing displaying high risk areas.
        System.out.println("Testing displayHighRisk()\n");
        System.out.println("Here the risk value is considered as the priority. The high risk areas should be : F");
        heap.displayHighRisk();
        System.out.println("================================\n");

        System.out.println("Thank you for using DSAHeap TestHarness!!!");

    }
}
