
public class Avenger implements Comparable<Avenger> {
	private String heroAlias;
	private String heroName;
	private String performer;

	private int nameFreq;
	private int aliasFreq;
	private int performerFreq;
	private int mentionIndex;

	public Avenger(String alias, String name, String performer) {
		this.heroName = name;
		this.heroAlias = alias;
		this.performer = performer;
		
		nameFreq = 0;
		aliasFreq = 0;
		performerFreq = 0;
	}
	
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
	public String getAlias() {
		return heroAlias;
	}

	public String getName() {
		return heroName;
	}
	
	public String getPerformer () {
		return performer;
	}
	
	public int getNameFreq() {
		return nameFreq;
	}
	
	public int getAliasFreq() {
		return aliasFreq;
	}
	
	public int getPerformerFreq () {
		return performerFreq;
	}
	
	public void addNameFreq() {
		nameFreq++;
	}
	
	public void addAliasFreq() {
		aliasFreq++;
	}
	
	public void addPerformerFreq() {
		performerFreq++;
	}
	
	@Override
	public int compareTo(Avenger other) {
		if (other == null)
			return -1;
		return this.getAlias().compareTo(other.getAlias());
	}

	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;
		Avenger a = (Avenger) other;
		if (this.getAlias().equals(a.getAlias())) 
			return true;
		else
			return false;
	}

	public String toString () {
		return 	heroAlias + " aka " + heroName 
                + " performed by " + performer 
                + " mentioned " 
                + "(n: " + nameFreq 
                + " + a: " + aliasFreq 
                + " + p: " +  performerFreq 
                + ")" + " time(s)";  
	}

	public int getTotalFrequency() {
		return nameFreq + aliasFreq + performerFreq;
	}

	public int getMentionIndex() {
		return mentionIndex;
	}

	public void setMentionIndex(int mentionIndex) {
		this.mentionIndex = mentionIndex;
	}
}
