public class UnitTestHash {
    public static void main(String[] args) {
        System.out.println("Hash Table Test Harness");
        System.out.println("=======================");

        //Testing putting a entries to a hash table.
        System.out.println("Testing put");
        DSAHashTable newHashTable = new DSAHashTable();
        newHashTable.hashTable(100);

        newHashTable.put("1", "A");
        newHashTable.put("2", "B");
        newHashTable.put("3", "C");
        newHashTable.put("4", "D");
        newHashTable.put("5", "E");
        System.out.println("Testing put: passed");

        //Testing displaying a hash table.
        System.out.println("Testing displayHashTable");
        newHashTable.displayHashTable();

        //Testing getting a value from the hash table.
        System.out.println("-----------------------");
        System.out.println("Testing get");

        if(newHashTable.get("1")== "A" && newHashTable.get("2")== "B" && newHashTable.get("3")== "C" && newHashTable.get("4")== "D" && newHashTable.get("5")== "E") {
            System.out.println("Testing get: passed");
        }
        else {
            System.out.println("Testing get: FAILED");
        }
        System.out.println("-----------------------");

        //Testing removing a value from the hash table.
        System.out.println("Testing remove");
        try {
            newHashTable.remove("1");
            newHashTable.remove("2");
            newHashTable.remove("3");
            newHashTable.displayHashTable();
            System.out.println("Testing remove: passed");
        }
        catch (Exception e) {
            System.out.println("Testing remove: FAILED");
        }

        System.out.println("-----------------------");

    }
}
