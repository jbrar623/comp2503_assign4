
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * COMP 2503 Fall 2023 Assignment 4
 * 
 * This program reads an input stream and keeps track of the frequency at
 * which an avenger is mentioned either by name or alias or performer's last name. The program uses a HashMap
 * for keeping track of the Avenger Objects, and TreeMaps for storing the data. 
 * 
 */

public class A4 {

	public String[][] avengerRoster = { { "captainamerica", "rogers", "evans" }, { "ironman", "stark", "downey" },
			{ "blackwidow", "romanoff", "johansson" }, { "hulk", "banner", "ruffalo" },
			{ "blackpanther", "tchalla", "boseman" }, { "thor", "odinson", "hemsworth" },
			{ "hawkeye", "barton", "renner" }, { "warmachine", "rhodes", "cheadle" },
			{ "spiderman", "parker", "holland" }, { "wintersoldier", "barnes", "stan" } };

	private int topN = 4;
	private int totalWordCount = 0;
	private int mentionIndex = 0;
	private Scanner input = new Scanner(System.in);
	
	/**
	 * Hashmap to store avenger obejcts using their aliases as keys
	 */
	private HashMap<String, Avenger> avengersMap = new HashMap<>();
	
	/**
	 * Treemaps storing avenger objects using other avenger objects as keys ordered based on 
	 * either natural ordering or the comparators given 
	 */
    private TreeMap<Avenger, Avenger> alphabeticalMap = new TreeMap<>();
    private TreeMap<Avenger, Avenger> mentionOrderMap = new TreeMap<>(new AvengerComparatorMentionOrder());
    private TreeMap<Avenger, Avenger> mostPopularAvengerMap = new TreeMap<>(new AvengerComparatorFreqDesc());
    private TreeMap<Avenger, Avenger> mostPopularPerformerMap = new TreeMap<>(new AvengerPerformerComparatorFreqDesc());
    
	public static void main(String[] args) {
		A4 a4 = new A4();
		a4.run();
	}

	/**
	 * Method to run the program that calls other methods to read input, create maps and produce output. 
	 */
	public void run() {
		readInput();
		createdOrderedTreeMaps();
		printResults();
	}

	/**
	 * Creates ordered TreeMaps using avengersMap and appropriate comparators.
	 */
	private void createdOrderedTreeMaps() {		
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
	 * reads the input stream and keeps track of how many times avengers are mentioned by
	 * alias. 
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
	            totalWordCount++;
	            Avenger newAvengerObject = createAvengerObject(word);
	            
	            if (newAvengerObject != null) {
	            String avengerKey= newAvengerObject.getAlias(); 

	            if (avengersMap.containsKey(avengerKey) ) {
	            	//avenger already exists in map
	    	      	newAvengerObject.addFrequency(word); 
	    	      	avengersMap.get(avengerKey).addFrequency(word);
	    	      	//increase frequency for existing avenger 
	    	      	}
	    	      	else {
	    	      	//add new avenger if it doesn't exist in the map using put, could also use putIfAbsent which checks again 
	    	    	newAvengerObject.addFrequency(word); 
	    	      	newAvengerObject.setMentionIndex(mentionIndex++);
	    	      	avengersMap.put(newAvengerObject.getAlias(), newAvengerObject);
	    	      	}
	            }
	        }
		}
	}
	
	/**
	 * Creates avenger objects 
	 * @param word - string being used to match to a possible avenger object
	 * @return an avenger object based on the string parameter
	 */
	private Avenger createAvengerObject(String word) {
		for (int i = 0; i < avengerRoster.length; i++) {
			if (avengerRoster[i][0].equals(word) || avengerRoster[i][1].equals(word)
					|| avengerRoster[i][2].equals(word)) {
				return new Avenger(avengerRoster[i][0], avengerRoster[i][1], avengerRoster[i][2]);
			}
		}
		return null;
	}
	
	/**
	 * Removes excess all non-alphabetical characters from a string 
	 * @param next - the word to clean 
	 * @retun ret - the cleaned word 
	 */
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
	 * prints the results of the avenger data collected based on the input 
	 */
	private void printResults() {
		System.out.println("Total number of words: " + totalWordCount + "\n");
		System.out.println("Number of Avengers Mentioned: " + mentionOrderMap.size()+ "\n");
		System.out.println();
	
		System.out.println();
	    System.out.println("All avengers in the order they appeared in the input stream:\n");
	    printAvengers(mentionOrderMap);
	    System.out.println();

	    System.out.println("Top " + topN + " most popular avengers:\n");
	    printPopularAvengers(mostPopularAvengerMap);
	    System.out.println();

	    System.out.println("Top " + topN + " most popular performers:\n");
	    printPopularAvengers(mostPopularPerformerMap);
	    System.out.println();

	    System.out.println("All mentioned avengers in alphabetical order:\n");
	    printAvengers(alphabeticalMap);
	    System.out.println();   
	}

	/**
     *  Iterates through the avengers in the given map in it's pre-existing order
     *  Prints the avengers in the given TreeMap in that order.
     * 
     * @param avengersToPrint The TreeMap containing avengers to be printed .
     */
	private void printAvengers(TreeMap<Avenger, Avenger> avengersToPrint) {
	    // Iterate through the avengers in the given map in it's pre-existing order
	    for (Avenger avenger : avengersToPrint.values()) {
	        System.out.println(avenger.toString() + "\n");
	    }
	}

	/**
     * Prints the top N most popular avengers in the given 
     * TreeMap by iterating through the given map. 
     * 
     * @param popularityMap The TreeMap containing ordered avengers.
     */
	private void printPopularAvengers(TreeMap<Avenger, Avenger> popularityMap) {
	    // Iterate through the top N most popular avengers
	    int count = 0;
	    for (Avenger avenger : popularityMap.values()) {
	        if (count < topN) {
	            System.out.println(avenger.toString() + "\n");
	            count++;
	        } else {
	            break; }
	    }
	}

}