import java.io.Serializable;
import java.util.Iterator;
//Code based on the Practical 4 submission. Multiple new methods have been added.
//Given below is an implementation of Doubly-linked double-ended linked list.
public class DSALinkedList implements Serializable {
    DSAListNode head;
    DSAListNode tail;
    int count= 0;

    //Constructor
    public DSALinkedList() {
        this.head = null;
        this.tail = null;
        this.count= 0;
    }

    //Accessor
    public int getCount(){
        return  count;
    }

    //Method to insert a value to the front.
    public void insertFirst(Object newValue) {
        DSAListNode newNd = new DSAListNode(newValue);
        if (isEmpty()) {
            head = newNd;
            tail=newNd;
            count+=1;
        } else {
            newNd.setNext(head);
            head.setPrev(newNd);
            head = newNd;
            count+=1;
        }

    }

    //Method to insert a value to back of the end.

    public void insertLast(Object newValue) {
        DSAListNode newNd = new DSAListNode(newValue);

        if (isEmpty()) {
            head = newNd;
            tail = newNd;
            count += 1;
        } else {

            tail.setNext(newNd);
            newNd.setPrev(tail);
            tail = newNd;
            count += 1;
        }

    }

    //Method to get the value at the front.
    public Object peekFirst() {
        Object nodeValue = null;
        if (isEmpty()) {
            return nodeValue;
        } else {
            nodeValue = head.getValue();
        }
        return nodeValue;
    }

    //Method to get the value at the end.
    public Object peekLast() {
        Object nodeValue = null;
        DSAListNode currNd;
        if (isEmpty()) {
            return nodeValue;
        } else {
           nodeValue = tail.getValue();
            return nodeValue;
        }
    }

    //Method to remove the first value.
    public Object removeFirst() {
        Object nodeValue = null;
        if (isEmpty()) {
            return nodeValue;
        }
        else if (head.getNext() == null) {

            nodeValue = head.getValue();
            head = null;
            tail = null;
            count-=1;
            return nodeValue;
        }
        else {
            nodeValue = head.getValue();
            head = head.getNext();
            head.setPrev(null);
            count-=1;
            return nodeValue;

        }
    }

    //Method to remove the value at the end.
    public  Object removeLast() {
        Object nodeValue = null;
        DSAListNode currNd;
        if (isEmpty()) {
            return nodeValue;
        } else if (head.getNext() == null) {
            //Use head = head.getNext(); instead of head = null;
            //Use tail= tail.getNext(); instead of tail = null;
            //use in both implementations
            nodeValue = head.getValue();
            head = null;
            tail = null;
            count -= 1;
        } else {
            nodeValue = tail.getValue();
            tail = tail.getPrev();
            tail.setNext(null);   //i think this is wrong
            count -= 1;
        }
        return nodeValue;
    }


    //Method to check if the list is empty.
    public boolean isEmpty() {
        boolean empty = false;
        if (head == null&&tail==null) {
            empty = true;
        }
        return empty;
    }

    //Method to iterate over the list.
    public void iterateOverList(DSALinkedList theList) {
        Object  c;
        Iterator iter = theList.iterator();
        while (iter.hasNext()) {
            c =  iter.next();
            System.out.println(c);
        }
    }

    //Method to check if the list contains a DSAGraphVertex object.
    public boolean containsVertex(DSAGraphVertex vertex2) {
           boolean found = false;
            DSAListNode currNd;
            if (isEmpty()) {
                return found;
            } else {
                currNd = head;
                while (currNd != null) {
                    if (currNd.getValue().equals(vertex2)) {
                        found = true;
                    }
                    currNd = currNd.getNext();
                }
            }
            return found;
    }

    //Method to display the values of a list.
    public void display() {
        DSAListNode currNd;
        if (isEmpty()) {
            System.out.println("List is empty");
        } else {
            currNd = head;
            while (currNd != null) {
                System.out.println(currNd.getValue());
                currNd = currNd.getNext();

            }
        }
    }

    //Method to remove a value from the list.
    public void remove(Object vertex) {

        if (isEmpty()) {
            System.out.println("List is empty");
            ;
        } else {
            DSAListNode currNd = head;

            while (currNd != null) {


                if (currNd.getValue().equals(vertex)) {
                    if (currNd.getNext() == null && currNd.getPrev() == null) {
                        head = null;
                        tail = null;
                    } else if (currNd.getPrev() == null) {
                        head = currNd.getNext();
                    } else if (currNd.getNext() == null) {
                        tail = currNd.getPrev();
                    } else {
                        DSAListNode prev = currNd.getPrev();
                        DSAListNode next = currNd.getNext();

                        prev.setNext(next);
                        next.setPrev(prev);
                    }
                }
                currNd = currNd.getNext();
            }
        }

    }



    private class DSAListNode implements Serializable {
        Object value;
        Object weight;
        DSAListNode next;
        DSAListNode prev;

        //Constructors
        public DSAListNode(Object inValue,Object inWeight) {

            this.value = inValue;
            this.weight = inWeight;
            this.next = null;
            this.prev =null;
        }
        public DSAListNode(Object inValue) {

            this.value = inValue;
            this.next = null;
            this.prev =null;
        }

        //Accessors
        public Object getValue() {
            return value;
        }
        public Object getWeight(){
            return weight;
        }
        public DSAListNode getNext() {
            return next;
        }
        public DSAListNode getPrev(){
            return prev;
        }

        //Mutators
        public void setValue(DSAListNode inValue) {
            value = inValue;
        }
        public void setWeight(Object inWeight){
            weight = inWeight;
        }
        public void setNext(DSAListNode newNext) {
            next = newNext;
        }
        public void setPrev(DSAListNode newPrev){
            prev= newPrev;
        }

    }

    //Iterator for DSALinkedList
    public Iterator iterator()  {
        return new DSALinkedList.MyLinkedListIterator(this);
    }

    private class MyLinkedListIterator implements Iterator {
        private DSALinkedList.DSAListNode iterNext;
        public MyLinkedListIterator(DSALinkedList theList) {
            iterNext = theList.head;
        }

        // Iterator interface implementation
        public boolean hasNext() { return (iterNext != null); }

        public Object next() {
            Object value;
            if (iterNext == null)
                value = null;
            else {
                value = iterNext.getValue();
                iterNext = iterNext.getNext();
            }
            return value;
        }

        public void remove() { throw new UnsupportedOperationException("Not supported"); }
    }
}

