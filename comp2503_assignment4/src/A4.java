
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * COMP 2503 Fall 2023 Assignment 4
 * 
 * This program must read a input stream and keeps track of the frequency at
 * which an avenger is mentioned either by name or alias or performer's last name. The program must use HashMaps
 * for keeping track of the Avenger Objects, and it must use TreeMaps
 * for storing the data. 
 * 
 * @author Maryam Elahi
 * @date Fall 2023
 */

public class A4 {

	public String[][] avengerRoster = { { "captainamerica", "rogers", "evans" }, { "ironman", "stark", "downey" },
			{ "blackwidow", "romanoff", "johansson" }, { "hulk", "banner", "ruffalo" },
			{ "blackpanther", "tchalla", "boseman" }, { "thor", "odinson", "hemsworth" },
			{ "hawkeye", "barton", "renner" }, { "warmachine", "rhodes", "cheadle" },
			{ "spiderman", "parker", "holland" }, { "wintersoldier", "barnes", "stan" } };

	private int topN = 4;
	private int totalwordcount = 0;
	private int mentionIndex = 0;
	private Scanner input = new Scanner(System.in);
	

	/* TODO:
	 * Create the necessary hashMap and treeMap objects to keep track of the Avenger objects 
	 * Remember that a hashtable does not keep any inherent ordering for its contents.
	 * But for this assignment we want to be able to create the sorted lists of avenger objects.
	 * Use TreeMap objects (which are binary search trees, and hence have an
	 * ordering) for creating the following orders: alphabetical, mention order, most popular avenger, and most popular performer
	 * The alphabetical order TreeMap must be constructed with the natural order of the Avenger objects.
	 * The other three orderings must be created by passing the corresponding Comparators to the 
	 * TreeMap constructor. 
	 */
	
//	Comparator<Avenger> compMentionOrder = new AvengerComparatorMentionOrder();
//	Comparator<Avenger> compFreqDesc = new AvengerComparatorFreqDesc();
//	Comparator<Avenger> performerComp = new AvengerPerformerComparatorFreqDesc();
	
	
	private HashMap<String, Avenger> avengersMap = new HashMap<>();
	
    private TreeMap<Avenger, Avenger> alphabeticalMap = new TreeMap<>();
    
    private TreeMap<Avenger, Avenger> mentionOrderMap = new TreeMap<>(new AvengerComparatorMentionOrder());
    private TreeMap<Avenger, Avenger> mostPopularAvengerMap = new TreeMap<>(new AvengerComparatorFreqDesc());
    private TreeMap<Avenger, Avenger> mostPopularPerformerMap = new TreeMap<>(new AvengerPerformerComparatorFreqDesc());
    /*
     * error because the tree map constructor takes a comparator that compares the key and not the value, 
     * in this case the key is string, but my comparators are avenger, the problem is how to fix this???? 
     * solution is changing the order of the key and the mapped value types (k,v)???
     * - changing the order, or reversing the string and key pair makes my print method not work as i am using 
     * the avenger to string method which requires the maps to store avenger objects,  so the value in the treemap must be avengers 
     */
   
    
	
	
	public static void main(String[] args) {
		A4 a4 = new A4();
		a4.run();
	}

	public void run() {
		readInput();
		createdOrderedTreeMaps();
		printResults();
	}

	private void createdOrderedTreeMaps() {
		/* TODO:
		 * Create an iterator over the key set in the HashMap that keeps track of the avengers
		 * Add avenger objects to the treeMaps with different orderings.
		 * 
		 ** Hint: 
		 * Note that the HashMap and the TreeMap classes do not implement
		 * the Iterable interface at the top level, but they have
		 * methods that return Iterable objects, such as keySet() and entrySet().
		 * For example, you can create an iterator object over 
		 * the 'key set' of the HashMap and use the next() method in a loop
		 * to get each word object. 
		 */		
		
//		  Set<Avenger> avengersSet = avengersMap.keySet();
//	      Iterator<Avenger> iterator = avengersSet.iterator();
//
//	        while (iterator.hasNext()) {
//	            Avenger avengerKey = iterator.next();
//	            Avenger avenger = avengersMap.get(avengerKey);
//
//	            alphabeticalMap.put(avengerKey, avenger);
//	            mentionOrderMap.put(avengerKey, avenger);
//	            mostPopularAvengerMap.put(avengerKey, avenger);
//	            mostPopularPerformerMap.put(avengerKey, avenger);
//	        }
		
		// Create an iterator over the key set of avengersMap
	    Iterator<String> avengerIterator = avengersMap.keySet().iterator();

	    // Iterate using the iterator
	    while (avengerIterator.hasNext()) {
	        String avengerKey = avengerIterator.next();
	        Avenger avenger = avengersMap.get(avengerKey);

	        // Add avenger to alphabeticalMap (natural alphabetical ordering)
	        alphabeticalMap.put(avenger, avenger);

	        // Add avenger to mentionOrderMap using mention order comparator
	        mentionOrderMap.put(avenger, avenger);

	        // Add avenger to mostPopularAvengerMap using frequency comparator
	        mostPopularAvengerMap.put(avenger, avenger);

	        // Add avenger to mostPopularPerformerMap using performer comparator
	        mostPopularPerformerMap.put(avenger, avenger);
	    }
	}
	
	/**
	 * read the input stream and keep track how many times avengers are mentioned by
	 * alias or last name.
	 */
	private void readInput() {
		/*
		 * In a loop, while the scanner object has not reached end of stream, - read a
		 * word. - clean up the word - if the word is not empty, add the word count. -
		 * Check if the word is either an avenger alias or last name then - 
		 * Create a new avenger object with the corresponding alias and last name. - 
		 * if this avenger has already been mentioned, increase the corresponding frequency count for the object
		 * already in the hashMap. - 
		 * if this avenger has not been mentioned before, add the
		 * newly created avenger to the hashMap, remember to set the frequency, and 
		 * to keep track of the mention order
		 */
		while (input.hasNext()) {
	        String word = cleanWord(input.next());

	        if (word.length() > 0) {
	            totalwordcount++;
	            Avenger newAvengerObject = createAvengerObject(word);
	            
	            if (newAvengerObject != null) {
	            String avengerKey = newAvengerObject.getAlias(); 
	            
	            if (avengersMap.containsKey(avengerKey)) {
	            	//avenger already exists in map
	    	      	newAvengerObject.addFrequency(word); 
	    	      	//increase frequency for existing avenger 
	    	      	}
	    	      	else {
	    	      	avengersMap.put(newAvengerObject.getName(), newAvengerObject);
	    	      	//add new avenger if it doesn't exist in the map using put, could also use putIfAbsent which checks again 
	    	      	newAvengerObject.setMentionIndex(mentionIndex++);
	    	      	}
	            }
	        }
		}
	}
	
	private Avenger createAvengerObject(String word) {
		for (int i = 0; i < avengerRoster.length; i++) {
			if (avengerRoster[i][0].equals(word) || avengerRoster[i][1].equals(word)
					|| avengerRoster[i][2].equals(word)) {
				return new Avenger(avengerRoster[i][0], avengerRoster[i][1], avengerRoster[i][2]);
			}
		}
		return null;
	}
	
	private String cleanWord(String next) {
		// First, if there is an apostrophe, the substring
		// before the apostrophe is used and the rest is ignored.
		// Words are converted to all lowercase.
		// All other punctuation and numbers are skipped.
		String ret;
		int inx = next.indexOf('\'');
		if (inx != -1)
			ret = next.substring(0, inx).toLowerCase().trim().replaceAll("[^a-z]", "");
		else
			ret = next.toLowerCase().trim().replaceAll("[^a-z]", "");
		return ret;
	}


	/**
	 * print the results
	 */
	private void printResults() {
		/*
		 * Please first read the documentation for TreeMap to see how to 
		 * iterate over a TreeMap data structure in Java.
		 *  
		 * Hint for printing the required list of avenger objects:
		 * Note that the TreeMap class does not implement
		 * the Iterable interface at the top level, but it has
		 * methods that return Iterable objects.
		 * You must either create an iterator over the 'key set',
		 * or over the values 'collection' in the TreeMap.
		 * 
		 */
		System.out.println();
		System.out.println("Total number of words: " + totalwordcount);
		System.out.println("Number of Avengers Mentioned: " + mentionOrderMap.size());
		System.out.println();
		
	    System.out.println("All avengers in the order they appeared in the input stream:");
	    printAvengersInOrderOfAppearance();
	    System.out.println();

	    System.out.println("Top " + topN + " most popular avengers:");
	    printPopularAvengers(mostPopularAvengerMap);
	    System.out.println();

	    System.out.println("Top " + topN + " most popular performers:");
	    printPopularAvengers(mostPopularPerformerMap);
	    System.out.println();

	    System.out.println("All mentioned avengers in alphabetical order:");
	    printAvengersInOrder();
	    System.out.println();
	}

	private void printAvengersInOrderOfAppearance() {
	    // Iterate through the avengers in the mention order
	    for (Avenger avenger : mentionOrderMap.values()) {
	        System.out.println(avenger.toString());
	    }
	}

	private void printPopularAvengers(TreeMap<Avenger, Avenger> mostPopularAvengerMap) {
	    // Iterate through the top N most popular avengers
	    int count = 0;
	    for (Avenger avenger : mostPopularAvengerMap.values()) {
	        if (count < topN) {
	            System.out.println(avenger.toString());
	            count++;
	        } else {
	            break; }
	    }
	}

	private void printAvengersInOrder() {
	    // Iterate through the avengers in alphabetical order
	    for (Avenger avenger : alphabeticalMap.values()) {
	        System.out.println(avenger.toString());
	    }
	}
}