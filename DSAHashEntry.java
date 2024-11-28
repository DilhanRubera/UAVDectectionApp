//Code based on the Practical 7 submission. Multiple new methods have been added.
public class DSAHashEntry {
    private String key;   //Key of a hash entry.
    private Object value;  //Object to be stored.
    private int state;     //Determines if an entry is present or not.


    //Constructors.
    public DSAHashEntry() {
        key = "";
        value = null;
        state = 0;

    }

    public DSAHashEntry(String inKey, Object inValue) {
        key = inKey;
        value = inValue;
        state=1;
    }

    //Accessors
    public String getKey()
    {
        return key;
    }

    public Object getValue()
    {
        return value;
    }

    public int getState()
    {
        return state;
    }

    //Mutators
    public void setKey(String inKey) {
        key = inKey;
    }
    public void setValue(Object inValue)
    {
        value = inValue;
    }
    public void setState()
    {
        state = 1;
    }
    public void clearState()
    {
        state = 0;
    }

}


