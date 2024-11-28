public class UnitTestLinkedList {
    public static void main(String[] args) {

        System.out.println("Welcome to DSALinkedList Test Harness.\nTesting methods of a Doubly-linked double-ended linkedList");

        //Testing inserting a value to the front of the list.
        System.out.println("Testing insertFirst");
        DSALinkedList test1 = new DSALinkedList();
        test1.insertFirst("A");
        test1.insertFirst("B");
        test1.insertFirst("C");
        if(test1.removeFirst()=="C"&& test1.removeLast()=="A"){
            System.out.println("Test Passed!!!");
        }
        else{
            System.out.println("TEST FAILED !!!");
        }
        System.out.println("=======================================\n");

        //Testing inserting a value to the end of the list.
        System.out.println("Testing insertLast");
        DSALinkedList test2 = new DSALinkedList();
        test2.insertLast("A");
        test2.insertLast("B");
        test2.insertLast("C");
        if(test2.removeFirst()=="A"&& test2.removeLast()=="C"){
            System.out.println("Test Passed!!!");
        }
        else{
            System.out.println("TEST FAILED !!!");
        }
        System.out.println("=======================================\n");

        //Testing removing a value from the front of the list.
        System.out.println("Testing removeFirst");
        DSALinkedList test3 = new DSALinkedList();
        test3.insertFirst("A");
        test3.insertFirst("B");
        test3.insertFirst("C");
        if(test3.removeFirst()=="C"){
            System.out.println("Test Passed!!!");
        }
        else{
            System.out.println("TEST FAILED !!!");
        }
        System.out.println("=======================================\n");

        //Testing removing a value from the end of the list.
        System.out.println("Testing removeLast");
        DSALinkedList test4 = new DSALinkedList();
        test4.insertLast("A");
        test4.insertLast("B");
        test4.insertLast("C");
        if(test4.removeLast()=="C"){
            System.out.println("Test Passed!!!");
        }
        else{
            System.out.println("TEST FAILED !!!");
        }
        System.out.println("=======================================\n");

        //Testing displaying the list.
        System.out.println("Testing display");
        DSALinkedList test5 = new DSALinkedList();
        test5.insertLast("A");
        test5.insertLast("B");
        test5.insertLast("C");
        test5.insertLast("D");
        test5.insertLast("E");
        test5.insertLast("F");
        test5.display();
        System.out.println("=======================================\n");

        //Testing returning the value at the front of the list without removing it.
        System.out.println("Testing peekFirst");
        if(test5.peekFirst()=="A") {
            System.out.println("Test Passed!!!");
        }
        else {
            System.out.println("TEST FAILED !!!");
        }
        System.out.println("=======================================\n");

        //Testing returning the value at the end of the list without removing it.
        System.out.println("Testing peekLast");
        if(test5.peekLast()=="F") {
            System.out.println("Test Passed!!!");
        }
        else {
            System.out.println("TEST FAILED !!!");
        }
        System.out.println("=======================================\n");

        //Testing removing a value from the list.
        System.out.println("Testing remove");
        test5.remove("A");
        if(test5.peekFirst()=="B") {
            System.out.println("Test Passed!!!");
        }
        else {
            System.out.println("TEST FAILED !!!");
        }
        System.out.println("=======================================\n");

        //Testing if a list contains a particular DSAGraphVertex object.
        System.out.println("Testing containsVertex");
        DSALinkedList test6 = new DSALinkedList();
        DSAGraphVertex vertex1 = new DSAGraphVertex("A");
        DSAGraphVertex vertex2 = new DSAGraphVertex("B");
        DSAGraphVertex vertex3 = new DSAGraphVertex("C");
        test6.insertFirst(vertex1);
        test6.insertFirst(vertex2);
        test6.insertFirst(vertex3);
        if(test6.containsVertex(vertex2)){
            System.out.println("Test Passed!!!");
        }
        else {
            System.out.println("TEST FAILED !!!");
        }
        System.out.println("=======================================\n");

        System.out.println("Thank you for using DSALinkedList Test harness");











    }
}
