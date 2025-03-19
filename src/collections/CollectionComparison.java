package collections;

import java.util.*;

public class CollectionComparison {
    public static void main(String[] args) {
        CollectionComparison collectionComparison = new CollectionComparison();
        collectionComparison.compareInsertSearchDelete();
        collectionComparison.compareSorting();
    }

    private void compareSorting() {
        List<String> intArrayList = new ArrayList<>();
        List<String> intLinkedList = new LinkedList<>();
        Set<String> intHashSet = new HashSet<>();
        Set<String> intTreeSet = new TreeSet<>();
        Set<String> intLinkedHashSet = new LinkedHashSet<>();
        Map<String, String> hashMap = new HashMap<>();
        Map<String, String> treeMap = new TreeMap<>();
        Map<String, String> linkedHashMap = new LinkedHashMap<>();

        for (int i=0;i<10;i++) {
            intArrayList.add(i + " Banana");
            intLinkedList.add(i + " Banana");
            intHashSet.add(i + " Banana");
            intTreeSet.add(i + " Banana");
            intLinkedHashSet.add(i + " Banana");
            hashMap.put(i + " Banana", i + " Banana");
            treeMap.put(i + " Banana", i + " Banana");
            linkedHashMap.put(i + " Banana", i + " Banana");
        }

        System.out.printf("%-30s %s\n", intArrayList.getClass(), intArrayList); //ordered collection
        System.out.printf("%-30s %s\n", intLinkedList.getClass(), intLinkedList); //ordered collection
        System.out.printf("%-30s %s\n", intHashSet.getClass(), intHashSet); //unordered collection hashes are what make the sorting work
        System.out.printf("%-30s %s\n", intTreeSet.getClass(), intTreeSet); //ordered due to being a BST
        System.out.printf("%-30s %s\n", intLinkedHashSet.getClass(), intLinkedHashSet); //ordered due to being a linked hash set
        System.out.printf("%-30s %s\n", hashMap.getClass(), hashMap); //unordered collection hashes are what make the sorting work
        System.out.printf("%-30s %s\n", treeMap.getClass(), treeMap); //ordered collection due to being a bst
        System.out.printf("%-30s %s\n", linkedHashMap.getClass(), linkedHashMap); //ordered collection doe to being a linked hash map
    }

    private void compareInsertSearchDelete() {
        List<String> arrayListStrings = this.arrayInsertion();
        List<String> linkedListStrings = this.linkedListInsertion();
        Set<String> linkedHashSet = this.linkedHashSetInsertion();
        Set<String> hashSet = this.hashSetInsertion();
        Set<String> treeSet = this.treeSetInsertion();
        Map<Integer, String> hashMap = this.hashMapInsertion();
        Map<Integer, String> treeMap = this.treeMapInsertion();
        this.searchIndex(arrayListStrings, 500);
        this.searchIndex(linkedListStrings, 500);
        this.searchIndex(linkedHashSet, 500);
        this.searchIndex(hashSet, 321);
        this.searchIndex(treeSet, 223);
        this.searchIndex(hashMap, 1000);
        this.searchIndex(treeMap, 5000);
        this.deleteAt(arrayListStrings, 999);
        this.deleteAt(linkedListStrings, 213);
        this.deleteAt(linkedHashSet, 123);
        this.deleteAt(hashSet, 245);
        this.deleteAt(treeSet, 545);
        this.deleteAt(hashMap, 1000);
        this.deleteAt(treeMap, 5999);
    }

    private Map<Integer, String> treeMapInsertion() {
        long currentTimeMillis = System.currentTimeMillis();
        Map<Integer, String> uuidCollection = new TreeMap<>();
        for (int i=0;i<10000000;i++) {
            uuidCollection.put(i, UUID.randomUUID().toString());
        }
        System.out.println("finished tree map insertion at " + (System.currentTimeMillis()-currentTimeMillis)/1000.0 + " seconds");
        return uuidCollection;
    }

    private void deleteAt(Map<Integer, String> map, int i) {
        long start = System.currentTimeMillis();
        map.remove(i);
        long end = System.currentTimeMillis();
        System.out.printf("Removal of %s element took %.8f seconds\n", map.getClass(), (end-start)/1000.0);
    }

    private void searchIndex(Map<Integer, String> map, int i) {
        long start = System.currentTimeMillis();
        map.get(i);
        long end = System.currentTimeMillis();
        System.out.printf("Fetching of %s element took %.8f seconds\n", map.getClass(), (end-start)/1000.0);
    }


    private void deleteAt(Set<String> set, int i) {
        long start = System.currentTimeMillis();
        set.remove(searchIndex(set, i));
        long end = System.currentTimeMillis();
        System.out.printf("Removal of %s element took %.8f seconds\n", set.getClass(), (end-start)/1000.0);
    }


    private void deleteAt(List<String> collection, int i) {
        long start = System.currentTimeMillis();
        collection.remove(i);
        long end = System.currentTimeMillis();
        System.out.printf("finished deletion on %s with %.8f seconds\n", collection.getClass(),
                (end-start)/1000.0);
    }

    private void searchIndex(List<String> collection, int i) {
        long start = System.currentTimeMillis();
        String s = collection.get(i);
        long end = System.currentTimeMillis();
        System.out.printf("finished search on %s with %.8f seconds\n", collection.getClass(),
                (end-start)/1000.0);
    }

    private List<String> linkedListInsertion() {
        long currentTimeMillis = System.currentTimeMillis();
        List<String> uuidCollection = new LinkedList<>();
        for (int i=0;i<10000000;i++) {
            uuidCollection.add(UUID.randomUUID().toString());
        }
        System.out.println("finished linked list insertion at " + (System.currentTimeMillis()-currentTimeMillis)/1000.0 + " seconds");
        return uuidCollection;
    }

    public List<String> arrayInsertion() {
        long currentTimeMillis = System.currentTimeMillis();
        List<String> uuidCollection = new ArrayList<>();
        for (int i=0;i<10000000;i++) {
            uuidCollection.add(UUID.randomUUID().toString());
        }
        System.out.println("finished array insertion at " + (System.currentTimeMillis()-currentTimeMillis)/1000.0 + " seconds");
        return uuidCollection;
    }


    private Set<String> treeSetInsertion() {
        long currentTimeMillis = System.currentTimeMillis();
        Set<String> uuidCollection = new TreeSet<>();
        for (int i=0;i<10000000;i++) {
            uuidCollection.add(UUID.randomUUID().toString());
        }
        System.out.println("finished Tree set insertion at " + (System.currentTimeMillis()-currentTimeMillis)/1000.0 + " seconds");
        return uuidCollection;
    }

    private Set<String> linkedHashSetInsertion() {
        long currentTimeMillis = System.currentTimeMillis();
        Set<String> uuidCollection = new LinkedHashSet<>();
        for (int i=0;i<10000000;i++) {
            uuidCollection.add(UUID.randomUUID().toString());
        }
        System.out.println("finished linked hash set insertion at " + (System.currentTimeMillis()-currentTimeMillis)/1000.0 + " seconds");
        return uuidCollection;
    }

    private Set<String> hashSetInsertion() {
        long currentTimeMillis = System.currentTimeMillis();
        Set<String> uuidCollection = new HashSet<>();
        for (int i=0;i<10000000;i++) {
            uuidCollection.add(UUID.randomUUID().toString());
        }
        System.out.println("finished hash set insertion at " + (System.currentTimeMillis()-currentTimeMillis)/1000.0 + " seconds");
        return uuidCollection;
    }


    private Map<Integer, String> hashMapInsertion() {
        long currentTimeMillis = System.currentTimeMillis();
        Map<Integer, String> uuidCollection = new HashMap<>();
        for (int i=0;i<10000000;i++) {
            uuidCollection.put(i, UUID.randomUUID().toString());
        }
        System.out.println("finished hash map insertion at " + (System.currentTimeMillis()-currentTimeMillis)/1000.0 + " seconds");
        return uuidCollection;
    }

    //Sets are unordered, this might produce a unwanted result
    private String searchIndex(Set<String> set, int i) {
        long start = System.currentTimeMillis();
        int index = 0;
        for (String s : set) {
            if ((index++)==i) {
                long end = System.currentTimeMillis();
                System.out.printf("Found an index! Finished search of %s in %.8f seconds\n", set.getClass(), (end-start)/1000.0);
                return s;
            }
        }
        long end = System.currentTimeMillis();
        System.out.printf("Finished search of %s in %.8f seconds", set.getClass(), (end-start)/1000.0);
        return null;
    }
}
