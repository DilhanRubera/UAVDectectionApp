//Code based on the Practical 8 submission. Multiple new methods have been added.

public class DSAHeapEntry {
    double priority; //Stores the priority.
    Object value;    //Stores the value.

    //Constructor
    public DSAHeapEntry(double pPriority, Object pValue) {
        priority = pPriority;
        value = pValue;
    }

    //Accessor
    public double getPriority() {
        return priority;
    }

    public Object getValue() {
        return value;
    }

    //Mutators
    public void setPriority(int pPriority) {
        priority = pPriority;
    }

    public void setValue(Object pValue) {
        value = pValue;
    }

}
