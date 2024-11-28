import static java.lang.Math.sqrt;

//Code based on the Practical 7 submission. Multiple new methods have been added.

public class DSAHashTable {
    DSAHashEntry[] hashArray;
    int count;
    int arraySize;
    double upperBoundLoadFactor= 0.75;
    int MAX_STEP = 3;

    //Initializes the hash table
    public void hashTable(int tableSize){
        int actualSize, ii;

        actualSize = findNextPrime(tableSize);

        hashArray = new DSAHashEntry [actualSize];

        for (ii=0; ii< actualSize; ii++)
        {
            hashArray[ii]= new DSAHashEntry();
            
        }
    }

    //Extract an entry from the hash table.
    public Object get (String inKey) {
        Object returnVal;
        int hashIdx = hash(inKey);
        int origIdx = hashIdx;
        boolean found = false;
        boolean giveUp = false;

        while (!found && !giveUp) {
            if (hashArray[hashIdx].getState() == 0) {
                giveUp = true;
            } else if (hashArray[hashIdx].getKey().equals(inKey)) {
                found = true;
            } else {
                hashIdx = (hashIdx + 1) % hashArray.length;
                if (hashIdx == origIdx) {
                    giveUp = true;
                }
            }
        }
        if (!found) {
            throw new IllegalArgumentException("Key not found");
        }
        returnVal = hashArray[hashIdx].getValue();

        return returnVal;

    }

    //Method to insert an entry to the hash table.
    public void put(String inKey, Object inValue) {

        int hashIdx;
        hashIdx = hash(inKey);

        //If load factor is larger than the upper bound, resize the hashtable.
        if(getLoadFactor() > upperBoundLoadFactor) {
            resize();
        }

        DSAHashEntry newEntry = new DSAHashEntry(inKey, inValue);
        if (hashArray[hashIdx].getKey() == "" && hashArray[hashIdx].getValue() == null && hashArray[hashIdx].getState() == 0) {
            hashArray[hashIdx] = newEntry;
        }
        else {
            int step = stepHash(Integer.parseInt(inKey));
            hashIdx = (hashIdx + step) % hashArray.length;

            if (hashArray[hashIdx].getKey() == "" && hashArray[hashIdx].getValue() == null && hashArray[hashIdx].getState() == 0) {
                hashArray[hashIdx] = newEntry;
            }
        }
        count++;
    }

    //Method to remove an entry from the hash table.
    public void remove(String inKey) {

        int hashIdx;
        int origIdx;

        hashIdx = hash(inKey);
        int step = stepHash(Integer.parseInt(inKey));

        boolean found = false;
        boolean giveUp = false;

        while (!found && !giveUp) {

            if (hashArray[hashIdx].getState() == 0) {
                giveUp = true;

            }
            else if (hashArray[hashIdx].getKey().equals(inKey) && hashArray[hashIdx].getState() == 1 && hashArray[hashIdx].getValue() != null) {

                hashArray[hashIdx].setKey("");
                hashArray[hashIdx].setValue(null);
                found = true;

            } else {
                hashIdx = (hashIdx + step) % hashArray.length;
                if (hashArray[hashIdx].getKey() == "" && hashArray[hashIdx].getValue() == null && hashArray[hashIdx].getState() == 0) {

                    giveUp = true;
                }
            }
        }
        count--;

        if(getLoadFactor() > upperBoundLoadFactor){
            resize();
        }
    }

    //Method to calculate and return the load factor.
    public double getLoadFactor() {
        double loadFactor;
        loadFactor = Double.valueOf(count) / Double.valueOf(hashArray.length);
        return loadFactor;
    }

    //Method used to resize the hash table
    public void resize() {
        int prevSize = hashArray.length;
        int prevCount = count;

        DSAHashEntry[] tempTable = hashArray;

       if(getLoadFactor() > upperBoundLoadFactor){
            arraySize = findNextPrime(prevSize*2);
        }
        else{
            arraySize = prevSize;
        }

        hashArray = new DSAHashEntry[arraySize];
        count = 0;

        for(int ii = 0; ii < arraySize; ii++)
        {
            hashArray[ii] = new DSAHashEntry();
        }

        for(int ii = 0; ii < prevSize; ii++)
        {
            if(tempTable[ii].getKey() !="" && tempTable[ii].getValue() != null)
            {
                put(tempTable[ii].getKey(), tempTable[ii].getValue());
            }
        }
    }

    //Method to find the next prime value from an int
    int findNextPrime(int startVal) {
        int prime = 0;
        double rootVal;
        int ii;
        boolean isPrime = false;


        if (startVal % 2 == 0) {
            prime = startVal - 1;
        } else {
            prime = startVal;
        }

        do {
            prime = prime + 2;
            ii = 3;
            isPrime = true;
            rootVal = sqrt(prime);

            do {
                if (prime % ii == 0) {
                    isPrime = false;
                } else {
                    ii = ii + 2;
                }
            }
            while (ii < rootVal && isPrime);
        }
        while (!isPrime);
        return prime;
    }

    //Hash function
    private int hash(String key)
    {
        int hashIdx = 0;
        for (int ii = 0; ii < key.length(); ii++) {
            hashIdx = (31 * hashIdx) + key.charAt(ii);
        }
        return Math.abs(hashIdx % hashArray.length);
    }

    //Second hash function
    private int stepHash(int key)
    {
       int hashStep = MAX_STEP - (key % MAX_STEP);
       return Math.abs(hashStep);
    }

    //Method to display the hashTable.
    public void displayHashTable(){
        for(int i=0; i<hashArray.length; i++){
            if(!(hashArray[i].getValue()==null)){
            System.out.println("Key " + hashArray[i].getKey());
        }
        }
    }

}
