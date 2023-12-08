
public class Avenger implements Comparable<Avenger> {
	/**
     * The string fields defining the avenger, including the alias,
     *  the name, and the performer. 
     */
	
	private String heroAlias;
	private String heroName;
	private String performer;

	/**
     * The int fields for the avenger, taken from the input file including the frequencies of 
     * the name, the alias, and the performer, and the mention index. 
     */
	
	private int nameFreq;
	private int aliasFreq;
	private int performerFreq;
	private int mentionIndex; 

	/**
     * Constructs an Avenger with the specified alias, name, and performer.
     *
     * @param alias      The alias of the avenger.
     * @param name       The name of the avenger.
     * @param performer  The performer of the avenger.
     */
	public Avenger(String alias, String name, String performer) {
		this.heroName = name;
		this.heroAlias = alias;
		this.performer = performer;
		
		nameFreq = 0;
		aliasFreq = 0;
		performerFreq = 0;
	}
	
	 /**
     * Adds frequency to the avenger fields attribute (name, alias, or performer) based on the given word.
     *
     * @param word The word to add frequency for.
     * @return true if frequency is added successfully, otherwise return false.
     */
	public boolean addFrequency(String word) {
		if (heroAlias.equals(word))
			addAliasFreq();
		else if (heroName.equals(word))
			addNameFreq();
		else if (performer.equals(word))
			addPerformerFreq();
		else
			return false;
		return true;
	}

	// Getters and Setters
	
	/**
     * @return String - the alias of the avenger.
     */
	public String getAlias() {
		return heroAlias;
	}

	/**
     * @return String - the name of the avenger.
     */
	public String getName() {
		return heroName;
	}
	
	/**
     * @return String - the performer name of the avenger.
     */
	public String getPerformer () {
		return performer;
	}
	
	/**
     * @return int - the frequency of the avenger's name.
     */
	public int getNameFreq() {
		return nameFreq;
	}
	
	/**
     * @return int - the frequency of the avenger's alias.
     */
	public int getAliasFreq() {
		return aliasFreq;
	}
	
	/**
     * @return int - the frequency of the avenger's performer name.
     */
	public int getPerformerFreq () {
		return performerFreq;
	}
	
	/**
     * increments the name frequency of the avenger
     */
	public void addNameFreq() {
		nameFreq++;
	}
	
	/**
     * increments the alias frequency of the avenger
     */
	public void addAliasFreq() {
		aliasFreq++;
	}
	
	/**
     * increments the performer frequency of the avenger
     */
	public void addPerformerFreq() {
		performerFreq++;
	}
	
    /**
     * Compares a given avenger with another avenger based on their aliases.
     *
     * @param other The avenger being compared with.
     * @return A negative integer, zero, or a positive integer as this avenger is less than, equal to, or greater than the other.
     */
	public int compareTo(Avenger other) {
		if (other == null)
			return -1;
		return this.getAlias().compareTo(other.getAlias());
	}
	
	 /**
     * Indicates whether some other avenger object is "equal to" this avenger based on their aliases.
     *
     * @param other The object to compare with.
     * @return true if this avenger is equal to the specified object, otherwise false.
     */
	public boolean equals(Object other) {
		if (other == null)
			return false;
		Avenger a = (Avenger) other;
		if (this.getAlias().equals(a.getAlias())) 
			return true;
		else
			return false;
	}

	 /**
     * Returns string representation of the avenger.
     * @return String avenger fields in string format.
     */
	public String toString () {
		return 	heroAlias + " aka " + heroName 
                + " performed by " + performer 
                + " mentioned " 
                + "(n: " + nameFreq 
                + " + a: " + aliasFreq 
                + " + p: " +  performerFreq 
                + ")" + " time(s)";  
	}
	
	 /**
     * Gets the total frequency of mention frequencies for the avenger.
     *
     * @return int - The total frequency of mentions.
     */
	public int getTotalFrequency() {
		return nameFreq + aliasFreq + performerFreq;
	}

	 /**
     * Gets the mention index of the specific avenger.
     *
     * @return int - the mention index.
     */
	public int getMentionIndex() {
		return mentionIndex;
	}

	/**
	 * sets the value of the mention index for the specific avenger. 
	 * @param givenMentionIndex 
	 */
	public void setMentionIndex(int givenMentionIndex) {
		this.mentionIndex = givenMentionIndex;
	}
}
